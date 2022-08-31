package com.qingspring.demo.common;


import lombok.Getter;

@Getter
public enum ThumbUpStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞");

    private Integer code;

    private String msg;

    ThumbUpStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
