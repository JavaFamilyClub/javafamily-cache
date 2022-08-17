package com.example.cachesimpledemo;

import club.javafamily.autoconfigre.cache.service.CacheOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CacheSimpleDemoApplicationTests {

    @Autowired
    private CacheOperator cacheOperator;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(cacheOperator);
    }

    @Test
    void testGetSet() {
        cacheOperator.setValue("key1", "v1");

        String v1 = cacheOperator.getValue("key1");

        Assertions.assertEquals(v1, "v1");
    }

    @Test
    void testGetSetNull() {
        cacheOperator.setValue("key1", null);

        String v1 = cacheOperator.getValue("key1");

        Assertions.assertEquals(v1, null);
    }

}
