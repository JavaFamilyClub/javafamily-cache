package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author Jack Li
 * @date 2022/8/17 下午3:58
 * @description cache auto configure
 */
@EnableConfigurationProperties(JavaFamilyCacheProperties.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@Import({
   CaffeineConfig.class,
   RedisConfig.class
})
public class JavaFamilyCacheAutoConfiguration {

    @Bean
    public CachePropertiesBeanPostProcessor cacheCustomizerBeanPostProcessor() {
        return new CachePropertiesBeanPostProcessor();
    }

}
