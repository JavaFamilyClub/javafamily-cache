package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import club.javafamily.autoconfigre.cache.service.CacheOperator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.util.LambdaSafe;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CacheCustomizerBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

    private ListableBeanFactory beanFactory;
    private List<CachePropertiesCustomizer> customizers;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        Assert.isInstanceOf(ListableBeanFactory.class, beanFactory, "CacheCustomizerBeanPostProcessor can only be used with a ListableBeanFactory");
        this.beanFactory = (ListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(!(bean instanceof CacheOperator)) {
            this.postProcessBeforeInitialization(beanFactory.getBean(JavaFamilyCacheProperties.class));
        }

        return bean;
    }

    private void postProcessBeforeInitialization(JavaFamilyCacheProperties properties) {
        ((LambdaSafe.Callbacks<CachePropertiesCustomizer, JavaFamilyCacheProperties>) LambdaSafe.callbacks(CachePropertiesCustomizer.class, this.getCustomizers(), properties, new Object[0])
                .withLogger(CacheCustomizerBeanPostProcessor.class))
                .invoke((customizer) ->
                {
                    customizer.customize(properties);
                });
    }

    private Collection<CachePropertiesCustomizer> getCustomizers() {
        if (this.customizers == null) {
            this.customizers = new ArrayList<>(this.getCacheCustomizerBeans());
            this.customizers.sort(AnnotationAwareOrderComparator.INSTANCE);
            this.customizers = Collections.unmodifiableList(this.customizers);
        }

        return this.customizers;
    }

    private Collection<CachePropertiesCustomizer> getCacheCustomizerBeans() {
        return this.beanFactory.getBeansOfType(CachePropertiesCustomizer.class, false, false).values();
    }

}
