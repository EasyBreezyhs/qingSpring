package com.qingspring.demo.service.Impl;

import com.qingspring.demo.entity.User;
import com.qingspring.demo.mapper.UserMapper;
import com.qingspring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        if (user.getId()==null){
           return userMapper.insert(user);
        }else {
           return userMapper.update(user);
        }
    }
}
