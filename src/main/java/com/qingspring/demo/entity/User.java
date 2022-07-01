package com.qingspring.demo.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private String address;
    private String avatarUrl;
    private String role;

}
