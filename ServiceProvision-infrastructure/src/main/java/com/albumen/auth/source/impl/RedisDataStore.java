package com.albumen.auth.source.impl;

import com.albumen.auth.source.IDataStore;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Albumen
 */
@Component
public class RedisDataStore implements IDataStore {

    @Resource
    private Map<String, String> redisTemplate;

    @Override
    public boolean put(String key, String value, long expire) {
        redisTemplate.put(key, value);
        return true;
    }

    @Override
    public boolean remove(String key) {
        redisTemplate.remove(key);
        return true;
    }

    @Override
    public boolean refresh(String key, long expire) {
        return true;
    }

    @Override
    public String get(String key) {
        return redisTemplate.get(key);
    }
}
