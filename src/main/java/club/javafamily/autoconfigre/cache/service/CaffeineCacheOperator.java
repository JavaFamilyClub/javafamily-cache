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

      if(!isCacheNullValues() && (cache == null || cache.find() == null)) {
         deleteKey(key);
         return;
      }

      caffeine.put(key, cache);
   }

   @Override
   public boolean isCacheNullValues() {
      return cacheNullValues;
   }

   /**
    * 删除 key
    *
    * @param key key
    * @return 是否成功
    */
   @Override
   public Boolean deleteKey(String key) {
      caffeine.invalidate(key);

      return true;
   }

   /**
    * 构建完整 key
    *
    * @param key 用户 key
    * @return 系统 key
    */
   @Override
   public String buildKey(String key) {
      if (StringUtils.hasText(prefix)) {
         return prefix + key;
      }

      return key;
   }
}
