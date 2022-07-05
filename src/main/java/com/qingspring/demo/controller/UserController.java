package com.qingspring.demo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.mapper.UserMapper;
import com.qingspring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
         return userService.list();
        }


    @PostMapping ("/insert")
    public boolean insertUser(@RequestBody User user){
         return userService.insertOrupdate(user);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable Integer id){
         return userService.removeById(id);
    }

//    @GetMapping("/page")
//    public Map<String, Object> page(@RequestParam Integer pageNum,
//                                    @RequestParam Integer pageSize){
//
//         return userService.page(pageNum,pageSize);
//    }
//    mybatis-plus 的分页查询
@GetMapping("/page")
    public IPage<User> page(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                            @RequestParam(defaultValue="") String username,
                            @RequestParam(defaultValue = "") String email,
                            @RequestParam(defaultValue = "") String address){
        IPage<User> page =new Page<>(pageNum,pageSize);
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (!"".equals(username)){
        queryWrapper.like("username",username);
    }
    if (!"".equals(email)){
        queryWrapper.like("email",email);
    }

    if (!"".equals(address)){
        queryWrapper.like("address",address);
    }

    return userService.page(page,queryWrapper);
}

}
