package com.qingspring.demo.controller;

import com.qingspring.demo.entity.User;
import com.qingspring.demo.mapper.UserMapper;
import com.qingspring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author : EasyBreezyhs
* @date : 2022/07/02-14:27
*/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

     @GetMapping("/")
     public List<User> findUser(){
         return userMapper.findAll();
        }


    @PostMapping ("/insert")
    public Integer insertUser(@RequestBody User user){
         return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public Integer deleteUserById(@PathVariable Integer id){
         return userMapper.deletUserById(id);
    }


}
