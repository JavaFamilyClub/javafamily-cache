package com.example.cacheredisdemo;

import club.javafamily.autoconfigre.cache.service.CacheOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class CacheTtlTests {

    @Autowired
    private CacheOperator cacheOperator;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(cacheOperator);
    }

    @Test
    void testGetSet() throws InterruptedException {
        // ttl 10s
        cacheOperator.setValue("key1", "v1");

        TimeUnit.SECONDS.sleep(5);

        String v1 = cacheOperator.getValue("key1");

        Assertions.assertNull(v1);
    }

}
