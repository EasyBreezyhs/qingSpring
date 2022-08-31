package com.qingspring.demo.common;

public enum RedisKeyEnum {
    FILES_KEY("FILES_FRONT_ALL"),

    USER_THUMUP_KEY("USER_THUMUP_KEY"),
    COMMENT_THUMUP_KEY("COMMENT_THUMUP_KEY");


    private String key;

    RedisKeyEnum(String key) {
        this.key =key;
    }

    public String getKey(){
        return key;
    }

    public static String getThumupKey(String likedUserId, String likedPostId){
//        该用户点赞 :: 该评论
        String builder = likedUserId +
                "::" +
                likedPostId;
        return builder;
    }

}
