package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;

/**
 * cache customizer
 */
@FunctionalInterface
public interface CachePropertiesCustomizer {

    void customize(JavaFamilyCacheProperties properties);

}
