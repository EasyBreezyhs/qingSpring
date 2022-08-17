package com.qingspring.demo.common;

public enum RedisKeyEnum {
    FILES_KEY("FILES_FRONT_ALL");


    private String key;

    RedisKeyEnum(String key) {
        this.key =key;
    }

    public String getKey(){
        return key;
    }

}
