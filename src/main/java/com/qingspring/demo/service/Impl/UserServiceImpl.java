package com.qingspring.demo.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.controller.DTO.UserDTO;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.mapper.UserMapper;
import com.qingspring.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingspring.demo.utils.JWT.TokenUtils;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import net.sf.jsqlparser.expression.TryCastExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean exp(HttpServletResponse response,List<User> userList) throws Exception {

        ExcelWriter writer = ExcelUtil.getWriter(true);

        writer.write(userList,true);

        //设置浏览器响应格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;fileName="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
        return true;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        User user = userInfo(userDTO);
        if (user!=null){
            BeanUtil.copyProperties(user,userDTO,true);
            //设置token
            String token = TokenUtils.getToken(user.getId().toString(),user.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else{
            throw new ServiceException(ResponseEnum.USERNAME_NOT_EXISTS);
        }


    }

    //    前端同一页面实现了登录和注册功能 需要区分 reusername 和 username 等
    @Override
    public Boolean register(UserDTO userDTO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userDTO.getReusername());
        wrapper.eq("password",userDTO.getRepassword());
        User user;
        try {
            user = getOne(wrapper);
        }catch (Exception e){
            throw  new ServiceException(ResponseEnum.ERROR);
        }

        if (user == null){
            user = new User();
            userDTO.setUsername(userDTO.getReusername());
            userDTO.setPassword(userDTO.getRepassword());
            BeanUtil.copyProperties(userDTO,user);
            String s = IdUtil.fastSimpleUUID();
            String nikename = "用户"+s.substring(16,32);
            user.setNickname(nikename);
            save(user);
        }else {
            throw new ServiceException(ResponseEnum.USER_EXISTS);
        }
        return true;
    }

    @Override
    public String getCount() {
        String c = userMapper.getCount();
        return c;
    }

    private  User userInfo(UserDTO userDTO){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userDTO.getUsername());
        wrapper.eq("password",userDTO.getPassword());
        User user;
        try {
            user = getOne(wrapper);
        }catch (Exception e){
            throw  new ServiceException(ResponseEnum.ERROR);
        }
        return user;
    }


    //    @Override
//    public UserDTO findUserByusername(String username) {
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("username",username);
//
//        User one = getOne(wrapper);
//        if (one==null){
//            throw new ServiceException(ResponseEnum.TOKEN_USER_ERROE);
//        }
//        UserDTO userDTO = new UserDTO();
//        BeanUtil.copyProperties(one,userDTO,true);
//        return userDTO;
//    }



}
