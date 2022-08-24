package club.javafamily.autoconfigre.cache.cache;

/**
 * @author Jack Li
 * @date 2022/8/17 下午4:19
 * @description
 */
public class DefaultCacheTarget<T> implements CacheTarget {

   private static final long serialVersionUID = 1L;
   private T value;

   public DefaultCacheTarget() {
   }

   public DefaultCacheTarget(T value) {
      this.value = value;
   }

   @Override
   public T get() {
      return value;
   }

}
