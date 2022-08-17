package club.javafamily.autoconfigre.cache.service;

import club.javafamily.autoconfigre.cache.cache.CacheTarget;
import club.javafamily.autoconfigre.cache.cache.DefaultCacheTarget;
import org.springframework.lang.Nullable;

/**
 * @author Jack Li
 * @date 2022/8/17 下午4:00
 * @description
 */
public interface CacheOperator {

   /**
    * 获取缓存
    * @param key 缓存 key
    * @return Cache
    */
   CacheTarget get(String key);

   /**
    * 获取缓存的对象
    * @param key 缓存 key
    * @return Cache Value
    */
   default <T> T getValue(String key) {
      final CacheTarget cacheTarget = get(key);

      return cacheTarget != null ? cacheTarget.get() : null;
   }

   /**
    * 添加缓存
    * @param key 缓存 key
    * @param cache 缓存
    */
   default <T> void setValue(String key, @Nullable T cache) {
      set(key, new DefaultCacheTarget<>(cache));
   }

   /**
    * 添加缓存
    * @param key 缓存 key
    * @param cache 缓存
    */
   void set(String key, CacheTarget cache);

   /**
    * 是否缓存 null 值
    * @return bool
    */
   boolean isCacheNullValues();

   /**
    * 删除 key
    * @param key key
    * @return 是否成功
    */
   Boolean deleteKey(String key);
}
