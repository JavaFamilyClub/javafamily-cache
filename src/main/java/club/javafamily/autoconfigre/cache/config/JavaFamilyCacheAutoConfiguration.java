package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

/**
 * @author Jack Li
 * @date 2022/8/17 下午3:58
 * @description cache auto configure
 */
@EnableConfigurationProperties(JavaFamilyCacheProperties.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@Import({
   CacheCustomizerConf.class,
   CaffeineConfig.class,
   RedisConfig.class
})
public class JavaFamilyCacheAutoConfiguration {

//    @Bean
//    public CacheCustomizerBeanPostProcessor cacheCustomizerBeanPostProcessor() {
//        return new CacheCustomizerBeanPostProcessor();
//    }

}
