package com.levkip.fibpet.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FibonacciRepository {
    private static final String REDIS_KEY = "fibonacci";

    private RedisTemplate<String, Object> redisTemplate;

    public FibonacciRepository(@Autowired RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long get(Integer index) {
        return (Long) redisTemplate.opsForHash().get(REDIS_KEY, toKey(index));
    }

    public void put(int index, Long value) {
        redisTemplate.opsForHash().put(REDIS_KEY, toKey(index), value);
    }

    private String toKey(Object k) {
        return String.valueOf(k);
    }
}
