package com.qingspring.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingspring.demo.entity.User;

import java.util.Map;

/**
* @author : EasyBreezyhs
* @date : 2022/07/05-19:55
*/
public interface UserService extends IService<User> {

    boolean insertOrupdate(User user);

//    Map<String, Object> pageUser(Integer pageNum, Integer pageSize);

}
