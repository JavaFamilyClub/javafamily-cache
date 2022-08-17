package club.javafamily.autoconfigre.cache.properties;

import club.javafamily.autoconfigre.cache.enums.CacheType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author Jack Li
 * @date 2022/8/17 下午3:58
 * @description
 */
@ConfigurationProperties(prefix = "javafamily.cache")
public class JavaFamilyCacheProperties {
   /**
    * Cache type. By default, auto-detected according to the environment.
    */
   private CacheType type;

   /**
    * Entry expiration. By default the entries never expire.
    */
   private Duration timeToLive;

   /**
    * Allow caching null values.
    */
   private boolean cacheNullValues = true;

   /**
    * Key prefix.
    */
   private String keyPrefix;

   /**
    * Whether to use the key prefix when writing to Redis.
    */
   private boolean useKeyPrefix = true;

   private final Caffeine caffeine = new Caffeine();

   public Duration getTimeToLive() {
      return this.timeToLive;
   }

   public void setTimeToLive(Duration timeToLive) {
      this.timeToLive = timeToLive;
   }

   public boolean isCacheNullValues() {
      return this.cacheNullValues;
   }

   public void setCacheNullValues(boolean cacheNullValues) {
      this.cacheNullValues = cacheNullValues;
   }

   public String getKeyPrefix() {
      return this.keyPrefix;
   }

   public void setKeyPrefix(String keyPrefix) {
      this.keyPrefix = keyPrefix;
   }

   public boolean isUseKeyPrefix() {
      return this.useKeyPrefix;
   }

   public void setUseKeyPrefix(boolean useKeyPrefix) {
      this.useKeyPrefix = useKeyPrefix;
   }

   public CacheType getType() {
      return type;
   }

   public void setType(CacheType type) {
      this.type = type;
   }

   public Caffeine getCaffeine() {
      return caffeine;
   }

   public static class Caffeine {

      /**
       * 初始化容量
       */
      private Integer initSize = 100;

      /**
       * 最大容量
       */
      private Long maxSize = 2000L;

      private boolean weakKeys;

      private boolean weakValues;

      private boolean softValues;

      private boolean recordStats;

      public Integer getInitSize() {
         return initSize;
      }

      public void setInitSize(Integer initSize) {
         this.initSize = initSize;
      }

      public Long getMaxSize() {
         return maxSize;
      }

      public void setMaxSize(Long maxSize) {
         this.maxSize = maxSize;
      }

      public boolean isWeakKeys() {
         return weakKeys;
      }

      public void setWeakKeys(boolean weakKeys) {
         this.weakKeys = weakKeys;
      }

      public boolean isWeakValues() {
         return weakValues;
      }

      public void setWeakValues(boolean weakValues) {
         this.weakValues = weakValues;
      }

      public boolean isSoftValues() {
         return softValues;
      }

      public void setSoftValues(boolean softValues) {
         this.softValues = softValues;
      }

      public boolean isRecordStats() {
         return recordStats;
      }

      public void setRecordStats(boolean recordStats) {
         this.recordStats = recordStats;
      }
   }
}
