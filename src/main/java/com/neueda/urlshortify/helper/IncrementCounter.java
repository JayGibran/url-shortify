package com.neueda.urlshortify.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

@Component
public class IncrementCounter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${redis.key}")
    private String redisKey;

    public Long provideCounter() {
        return new RedisAtomicLong(redisKey ,
                redisTemplate.getConnectionFactory()).incrementAndGet();
    }

}
