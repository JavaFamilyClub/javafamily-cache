package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import org.springframework.beans.factory.ObjectProvider;

//@Configuration(proxyBeanMethods = false, value = "cacheCustomizerConf")
public class CacheCustomizerConf {

    private final JavaFamilyCacheProperties cacheProperties;
    private final ObjectProvider<CachePropertiesCustomizer> cacheCustomizers;

    public CacheCustomizerConf(JavaFamilyCacheProperties cacheProperties,
                               ObjectProvider<CachePropertiesCustomizer> cacheCustomizers)
    {
        this.cacheProperties = cacheProperties;
        this.cacheCustomizers = cacheCustomizers;

        cacheCustomizers.orderedStream().forEach(customizer -> customizer.customize(cacheProperties));
    }


}
