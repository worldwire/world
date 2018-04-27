package com.spring.worldwire.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by luxun on 2018/4/23.
 */
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置有失效时间的缓存
     * @param key 缓存key
     * @param value 缓存value
     * @param time 缓存的失效时间，单位是毫秒
     */
    public void set(String key, Object value, long time) {

        redisTemplate.opsForValue().set(key,value,time);

    }

    /**
     * 设置没有失效时间的缓存
     * @param key   缓存key
     * @param value 缓存value
     */
    public void set(String key, Object value) {

        redisTemplate.opsForValue().set(key,value);

    }

    public void deleteKey(String key){

        redisTemplate.delete(key);
    }

    /**
     * 根据key查询value值
     * @param key
     * @return
     */
    public Object getValueByKey(String key){
        if(isCacheExists(key)){
            return redisTemplate.opsForValue().get(key);
        }
        return null;
    }

    /**
     * 查询缓存是否过期
     * @param key
     * @return
     */
    public Boolean isCacheExists(String key){
        return redisTemplate.hasKey(key);
    }
}
