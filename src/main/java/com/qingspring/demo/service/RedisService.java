package com.qingspring.demo.service;


public interface RedisService {

//    存储缓存
    void set(String key, Object value,long seconds);
//  获取缓存
    Object get(String key);

    void setString(String key,String value);
    String getString(String key);

//    清空删除key
    boolean del(String key);

}
