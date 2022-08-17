package club.javafamily.autoconfigre.cache.enums;

/**
 * @author Jack Li
 * @date 2022/8/17 下午4:36
 * @description
 */
public enum CacheType {
   /**
    * Redis backed caching.
    */
   REDIS,

   /**
    * Caffeine backed caching.
    */
   CAFFEINE,
}
