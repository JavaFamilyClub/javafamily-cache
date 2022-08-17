package club.javafamily.autoconfigre.cache.service;

import club.javafamily.autoconfigre.cache.cache.CacheTarget;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.util.StringUtils;

/**
 * @author Jack Li
 * @date 2022/8/17 下午4:23
 * @description
 */
public class CaffeineCacheOperator implements CacheOperator {

   private final Cache<Object, CacheTarget> caffeine;
   private final String prefix;
   private final boolean cacheNullValues;

   public CaffeineCacheOperator(Cache<Object, CacheTarget> caffeine,
                                String prefix,
                                boolean cacheNullValues)
   {
      this.caffeine = caffeine;
      this.prefix = prefix;
      this.cacheNullValues = cacheNullValues;
   }

   @Override
   public CacheTarget get(String key) {
      if (StringUtils.hasText(prefix)) {
         key = prefix + key;
      }

      return caffeine.get(key, k -> null);
   }

   @Override
   public void set(String key, CacheTarget cache) {
      if (StringUtils.hasText(prefix)) {
         key = prefix + key;
      }

      caffeine.put(key, cache);
   }

   @Override
   public boolean isCacheNullValues() {
      return cacheNullValues;
   }
}
