package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.cache.CacheTarget;
import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import club.javafamily.autoconfigre.cache.service.CacheOperator;
import club.javafamily.autoconfigre.cache.service.CaffeineCacheOperator;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author Jack Li
 * @date 2022/8/17 下午5:18
 * @description
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ Caffeine.class })
@ConditionalOnMissingBean(CacheOperator.class)
@ConditionalOnProperty(name = "javafamily.cache.type", havingValue = "CAFFEINE", matchIfMissing = true)
public class CaffeineConfig {

   private final JavaFamilyCacheProperties cacheProperties;

   public CaffeineConfig(JavaFamilyCacheProperties cacheProperties) {
      this.cacheProperties = cacheProperties;
   }

   @Bean
   @ConditionalOnMissingBean
   public Cache<Object, CacheTarget> caffeine() {
      final JavaFamilyCacheProperties.Caffeine caffeineConf
         = cacheProperties.getCaffeine();

      final Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
         .initialCapacity(caffeineConf.getInitSize())
         .maximumSize(caffeineConf.getMaxSize());

      final Duration ttl = cacheProperties.getTimeToLive();

      if(ttl != null) {
         caffeine.expireAfterWrite(ttl);
      }

      if(caffeineConf.isWeakKeys()) {
         caffeine.weakKeys();
      }

      if(caffeineConf.isWeakValues()) {
         caffeine.weakValues();
      }

      if(caffeineConf.isSoftValues()) {
         caffeine.softValues();
      }

      if(caffeineConf.isRecordStats()) {
         caffeine.recordStats();
      }

      return caffeine.build();
   }

   @Bean
   @ConditionalOnMissingBean
   public CacheOperator cacheOperator(Cache<Object, CacheTarget> caffeine) {
      String prefix = cacheProperties.isUseKeyPrefix()
         ? cacheProperties.getKeyPrefix()
         : null;

      return new CaffeineCacheOperator(caffeine, prefix,
         cacheProperties.isCacheNullValues());
   }

}
