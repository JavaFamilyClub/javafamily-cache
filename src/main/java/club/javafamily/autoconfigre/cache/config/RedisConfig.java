package club.javafamily.autoconfigre.cache.config;

import club.javafamily.autoconfigre.cache.properties.JavaFamilyCacheProperties;
import club.javafamily.autoconfigre.cache.service.CacheOperator;
import club.javafamily.autoconfigre.cache.service.RedisCacheOperator;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author Jack Li
 * @date 2022/8/17 下午6:12
 * @description
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ RedisTemplate.class })
@ConditionalOnMissingBean(CacheOperator.class)
@ConditionalOnProperty(name = "javafamily.cache.type", havingValue = "REDIS")
public class RedisConfig {

   @Bean("redisTemplate")
   @ConditionalOnMissingBean
   public RedisTemplate<String, Serializable> redisTemplate(
      RedisConnectionFactory redisConnectionFactory)
   {
      RedisTemplate<String, Serializable> template = new RedisTemplate<>();
      template.setConnectionFactory(redisConnectionFactory);

      Jackson2JsonRedisSerializer<Serializable> jackson2JsonRedisSerializer
         = new Jackson2JsonRedisSerializer<>(Serializable.class);
      ObjectMapper om = new ObjectMapper();
      om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
      om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
      jackson2JsonRedisSerializer.setObjectMapper(om);

      StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
      // key using StringSerializer
      template.setKeySerializer(stringRedisSerializer);
      // key of hash using StringSerializer
      template.setHashKeySerializer(stringRedisSerializer);

      // value using jackson serializer
      template.setValueSerializer(jackson2JsonRedisSerializer);
      template.setHashValueSerializer(jackson2JsonRedisSerializer);

      template.afterPropertiesSet();

      return template;
   }

   @Bean
   @ConditionalOnMissingBean
   public CacheOperator cacheOperator(RedisTemplate redisTemplate,
                                      JavaFamilyCacheProperties cacheProperties)
   {
      String prefix = cacheProperties.isUseKeyPrefix()
         ? cacheProperties.getKeyPrefix()
         : null;

      return new RedisCacheOperator(redisTemplate, prefix,
         cacheProperties.isCacheNullValues(),
         cacheProperties.getTimeToLive());
   }
}
