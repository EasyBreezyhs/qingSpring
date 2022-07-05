package com.qingspring.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.mapper.UserMapper;
import com.qingspring.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean insertOrupdate(User user) {
//        if (user.getId()==null){
//           return userMapper.insert(user);
//        }else {
//           return userMapper.update(user);
//        }
        return saveOrUpdate(user);

    }

//    @Override
//    public Map<String, Object> pageUser(Integer pageNum, Integer pageSize) {
//        pageNum = (pageNum - 1)*pageSize;
//        Integer total = userMapper.selectTotal();
//        List<User> list = userMapper.findAllBypage(pageNum, pageSize);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data",list);
//        res.put("total",total);
//        return res;
//    }
}
