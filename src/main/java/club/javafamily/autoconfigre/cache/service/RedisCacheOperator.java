package club.javafamily.autoconfigre.cache.service;

import club.javafamily.autoconfigre.cache.cache.CacheTarget;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * @author Jack Li
 * @date 2022/8/17 下午4:23
 * @description Redis 操作器
 */
public class RedisCacheOperator implements CacheOperator {

   private final RedisTemplate<String, CacheTarget> redisTemplate;
   private final String prefix;
   private final boolean cacheNullValues;
   private final Duration ttl;

   public RedisCacheOperator(RedisTemplate redisTemplate,
                             String prefix,
                             boolean cacheNullValues,
                             Duration ttl)
   {
      this.redisTemplate = redisTemplate;
      this.prefix = prefix;
      this.cacheNullValues = cacheNullValues;
      this.ttl = ttl;
   }

   @Override
   public CacheTarget get(String key) {
      if (StringUtils.hasText(prefix)) {
         key = prefix + key;
      }

      final ValueOperations<String, CacheTarget> opsForValue
         = redisTemplate.opsForValue();

      return opsForValue.get(key);
   }

   @Override
   public void set(String key, @NonNull CacheTarget cache) {
      if(!isCacheNullValues() && cache.get() == null) {
         return;
      }

      if (StringUtils.hasText(prefix)) {
         key = prefix + key;
      }

      final ValueOperations<String, CacheTarget> opsForValue
         = redisTemplate.opsForValue();

      if(ttl != null && !ttl.isNegative() && !ttl.isZero()) {
         opsForValue.set(key, cache, ttl);
      }
      else {
         opsForValue.set(key, cache);
      }
   }

   @Override
   public boolean isCacheNullValues() {
      return cacheNullValues;
   }
}
