package com.qingspring.demo.service.Impl;

import com.qingspring.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <h3>qingspring</h3>
 * <p>Redis</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-17 13:53
 **/
@Service
public class RedisImpl implements RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;



    @Override
    public void set(String key, Object value, long seconds) {
//        second为过期时间，10 即为10秒后过期
        redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean del(String key) {

        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }
}
