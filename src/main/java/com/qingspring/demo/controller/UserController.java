package com.qingspring.demo.controller;

import com.qingspring.demo.entity.User;
import com.qingspring.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

     @GetMapping("/main")
     public List<User> findUser(){
        List<User> all = userMapper.findAll();
        return all;
        }

    @GetMapping("/")
    public String isOK(){
         String a = "ok";
         return a;
    }



}
