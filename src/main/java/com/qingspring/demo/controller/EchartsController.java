package com.qingspring.demo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.service.IUserService;
import com.qingspring.demo.utils.JWT.LoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <h3>qingspring</h3>
 * <p>Echarts</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-01 15:18
 **/
@RestController
@RequestMapping("/echarts")
@LoginToken
public class EchartsController {

    @Autowired
    private IUserService userService;

    @GetMapping("/quarter")
    public Result getQuarter(){
        List<User> list = userService.list();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (User user : list) {
            Date date = Date.from(user.getCreateTime().atZone(ZoneId.systemDefault()).toInstant());
            Quarter quarter = DateUtil.quarterEnum(date);
            switch (quarter){
                case Q1:q1++;break;
                case Q2:q2++;break;
                case Q3:q3++;break;
                case Q4:q4++;break;
                default:break;
            }
        }
        List<Integer> list1 = Arrays.asList(q1, q2, q3, q4);
        return Result.success(list1);
    }


    @GetMapping("/count")
    public Result getCount(){
        return Result.success(userService.getCount());
    }


}
