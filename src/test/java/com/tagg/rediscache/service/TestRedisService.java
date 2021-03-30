package com.tagg.rediscache.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRedisService {

    @Autowired
    private RedisService redisService;

    @Test
    public void testPutMaxFileRecord() {
        redisService.putMaxFileRecord("test-aaa005.csv", "15641");
    }

    @Test
    public void testRemoveKey() {
        redisService.removeKey("test-aaa001.csv");
    }
}
