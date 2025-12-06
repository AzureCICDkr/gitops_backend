package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value); // Key-Value 저장
    }

    public String findByKey(String key) {
        return (String) redisTemplate.opsForValue().get(key); // Key로 값 조회
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
