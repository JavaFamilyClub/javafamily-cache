package club.javafamily.autoconfigre.cache.cache;

import java.util.Optional;

/**
 * @author Jack Li
 * @date 2022/8/17 下午4:08
 * @description
 */
public interface CacheTarget {

   static final long serialVersionUID = 1L;

   /**
    * 获取内部缓存对象
    * @param <T> 对象类型
    * @return 缓存对象
    */
   <T> T get();

   /**
    * 带默认值获取
    * @param defaultValue 默认值
    * @param <T> 对象类型
    * @return 缓存对象
    */
   default <T> T getOrElse(T defaultValue) {
      final T obj = get();

      return obj == null ? defaultValue : obj;
   }

   /**
    * 带异常获取
    * @param <T> 对象类型
    * @return 缓存对象
    */
   default <T> T getOrElseThrow() {
      return Optional.<T>ofNullable(get()).orElseThrow(() ->
         new NullPointerException("缓存值不存在!"));
   }

}
