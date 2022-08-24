# javafamily-cache

> JavaFamily 缓存组件 SpringBoot Starter

[![Build](https://github.com/JavaFamilyClub/javafamily-cache/actions/workflows/maven-build.yml/badge.svg)](https://github.com/JavaFamilyClub/javafamily-cache/actions/workflows/maven-build.yml)
[![MavenPublishSnapshot](https://github.com/JavaFamilyClub/javafamily-cache/actions/workflows/maven-publish-snapshot.yml/badge.svg)](https://github.com/JavaFamilyClub/javafamily-cache/actions/workflows/maven-publish-snapshot.yml)
[![MavenPublishRelease](https://github.com/JavaFamilyClub/javafamily-cache/actions/workflows/maven-publish-release.yml/badge.svg)](https://github.com/JavaFamilyClub/javafamily-cache/actions/workflows/maven-publish-release.yml)


## 1. 引入依赖

* Maven Central Release

``` xml
<dependency>
   <groupId>club.javafamily</groupId>
   <artifactId>javafamily-cache</artifactId>
   <version>2.3.2-beta.2</version>
</dependency>
```

* Maven Central Snapshot

``` xml
   <!-- Snapshot 库需确保 snapshots 是被允许的 -->
   <repositories>
      <repository>
         <id>maven-central</id>
         <url>https://oss.sonatype.org/content/repositories/snapshots</url>
         <releases>
            <enabled>false</enabled>
            <updatePolicy>never</updatePolicy>
         </releases>
         <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
         </snapshots>
      </repository>
   </repositories>

   <dependencies>
      <dependency>
         <groupId>club.javafamily</groupId>
         <artifactId>javafamily-cache</artifactId>
         <version>2.3.2-SNAPSHOT</version>
      </dependency>
   </dependencies>
```

## 2. 配置

> 缓存组件底层目前提供 `caffeine` 及 `redis` 两种实现方式, 默认为 `caffeine` 实现, 可支持配置包括:

| 属性 | 类型 | 描述 | 默认值 |
 |   --   |   -   |   -----   |   --   |
| javafamily.cache.type                 | CacheType | Cache type. support caffeine/redis |   caffeine    |
| javafamily.cache.cache-null-values    | java.lang.Boolean                                  | Allow caching null values.                                                              | true  |
| javafamily.cache.key-prefix           | java.lang.String                                   | Key prefix.                                                                             |       |
| javafamily.cache.time-to-live         | java.time.Duration                                 | Entry expiration. By default the entries never expire.                                  |       |
| javafamily.cache.use-key-prefix       | java.lang.Boolean                                  | Whether to use the key prefix when writing to cache.                                    | true  |
| javafamily.cache.caffeine.spec        | java.lang.String                                   | The spec to use to create caches. |       |
| javafamily.cache.caffeine.initSize    | java.lang.Integer                                  | 初始化容量.                                                                                  | 100   |
| javafamily.cache.caffeine.maxSize     | java.lang.Long                                     | 最大容量.                                                                                   | 2000  |
| javafamily.cache.caffeine.weakKeys    | java.lang.Boolean                                  | key 是否启用软引用.                                                                            | false |
| javafamily.cache.caffeine.weakValues  | java.lang.Boolean                                  | value 是否启用软引用.                                                                          | false |
| javafamily.cache.caffeine.softValues  | java.lang.Boolean                                  | value 是否启用软引用.                                                                          | false |
| javafamily.cache.caffeine.recordStats | java.lang.Boolean                                  | 是否启用状态记录.                                                                               | false | 

## 3. 使用

> 通过注入 `CacheOperator` 对象进行缓存操作.

```java
@SpringBootTest
class CacheSimpleDemoApplicationTests {

    @Autowired
    private CacheOperator cacheOperator;

    @Test
    void testGetSet() {
        cacheOperator.setValue("key1", "v1");

        String v1 = cacheOperator.getValue("key1");

        Assertions.assertEquals(v1, "v1");
    }
}
```
## 4. 示例代码

* `Caffeine`: [Caffeine 示例代码](./examples/cache-simple-demo)
* `Redis`: [Redis 示例代码](./examples/cache-redis-demo)
