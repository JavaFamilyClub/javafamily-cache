package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jack Li
 * @date 2022/8/17 下午3:58
 * @description cache auto configure
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JavaFamilyCacheProperties.class)
@Import({
   CaffeineConfig.class,
   RedisConfig.class
})
public class JavaFamilyCacheAutoConfiguration {

}
