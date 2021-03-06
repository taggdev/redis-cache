package com.tagg.rediscache.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Value("bulk-change:max-file-record")
    private String fileCountMaxKey;

    @Value("bulk-change:file-increment")
    private String fileCountIncrement;

    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void putMaxFileRecord(final String key, final String value) {

        redisTemplate.opsForHash().put(fileCountMaxKey, key, value);
    }

    public Long removeKey(final String key) {
        return redisTemplate.opsForHash().delete(fileCountMaxKey, key);
    }

    public void increment(final String key, final long value) {
        redisTemplate.opsForHash().increment(fileCountIncrement, key, value);
    }
}
