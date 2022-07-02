package com.qingspring.demo.entity;

import lombok.Data;

/**
* @author : EasyBreezyhs
* @date : 2022/07/02-14:26
*/
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String address;
    private String avatarUrl;
    private String role;

}
