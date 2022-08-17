package com.qingspring.demo.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.controller.DTO.UserDTO;
import com.qingspring.demo.controller.DTO.UserPasswordDTO;
import com.qingspring.demo.entity.Course;
import com.qingspring.demo.entity.Menu;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.mapper.MenuMapper;
import com.qingspring.demo.mapper.UserMapper;
import com.qingspring.demo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingspring.demo.utils.JWT.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    @Autowired
    private MenuMapper menuMapper;



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
//            通过用户Id查找他所拥有的菜单
            List<Integer> menuIdList = userMapper.getMenuIdByUserId(user.getId());
            List<Menu> pMenuList = getPmenuList(menuIdList);


            //设置token
            String token = TokenUtils.getToken(user.getId().toString(),user.getPassword());
            //设置refreshToken
            String refreshToken = TokenUtils.getRefreshToken(user.getId().toString(),user.getPassword());

//            将属性从user复制给UerDTO
            BeanUtil.copyProperties(user,userDTO,true);
            userDTO.setMenuList(pMenuList);
            userDTO.setToken(token);
            userDTO.setRefreshToken(refreshToken);
            return userDTO;
        }else{
            throw new ServiceException(ResponseEnum.USERNAME_NOT_EXISTS);
        }

    }

    //    前端同一页面实现了登录和注册功能 需要区分 reusername 和 username 等
    @Override
    public UserDTO register(UserDTO userDTO) {
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
            List<Integer> menuIdList = userMapper.getMenuIdByUserId(user.getId());
            List<Menu> pmenuList = getPmenuList(menuIdList);

            //设置token
            String token = TokenUtils.getToken(user.getId().toString(),user.getPassword());
            //设置refreshToken
            String refreshToken = TokenUtils.getRefreshToken(user.getId().toString(),user.getPassword());

            BeanUtil.copyProperties(user,userDTO,true);
            userDTO.setMenuList(pmenuList);
            userDTO.setToken(token);
            userDTO.setRefreshToken(refreshToken);

        }else {
            throw new ServiceException(ResponseEnum.USER_EXISTS);
        }
        return userDTO;
    }

    @Override
    public String getCount() {
        String c = userMapper.getCount();
        return c;
    }

    @Override
    public UserDTO getUserByname(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user;
        try {
            user = getOne(queryWrapper);
        }catch (Exception e){
            throw  new ServiceException(ResponseEnum.ERROR);
        }
        if (user!=null){
//            通过用户Id查找他所拥有的菜单
            List<Integer> menuIdList = userMapper.getMenuIdByUserId(user.getId());
            List<Menu> pMenuList = getPmenuList(menuIdList);


            //设置token
            String token = TokenUtils.getToken(user.getId().toString(),user.getPassword());
            //设置refreshToken
            String refreshToken = TokenUtils.getRefreshToken(user.getId().toString(),user.getPassword());

//            将属性从user复制给UerDTO
            UserDTO userDTO = new UserDTO();
            BeanUtil.copyProperties(user,userDTO,true);
            userDTO.setMenuList(pMenuList);
            userDTO.setToken(token);
            userDTO.setRefreshToken(refreshToken);
            return userDTO;
        }else{
            throw new ServiceException(ResponseEnum.USERNAME_NOT_EXISTS);
        }
    }

    @Override
    public boolean modifyPassword(UserPasswordDTO userPasswordDTO) {

        if (!Objects.equals(userPasswordDTO.getNewPassword(), userPasswordDTO.getConfirmPassword())){
            throw new ServiceException(ResponseEnum.USER_NEWPASSWORD_DIFF);
        }
        String username = userPasswordDTO.getUsername();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User one = getOne(queryWrapper);
        if (one == null){
            throw new ServiceException(ResponseEnum.USER_NOT_EXISTS);
        } else if (!one.getPassword().equals(userPasswordDTO.getPassword())) {
            throw new ServiceException(ResponseEnum.USER_PASSWORD_DIFF);
        } else if (one.getPassword().equals(userPasswordDTO.getNewPassword())) {
            throw new ServiceException(ResponseEnum.USER_PASSWORD_SAME);
        }


        return userMapper.modifyPassword(userPasswordDTO.getNewPassword(),one.getId());
    }

    @Override
    public Page<Course> findPage(Page<Course> page, String username, String email, String address) {
        return userMapper.findPage(page,username,email,address);
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



    private List<Menu> getPmenuList(List<Integer> menuIdList){
        List<Menu> menuList = new ArrayList<Menu>();
//            通过menuId查找出菜单元素列表
        for (Integer m : menuIdList) {
            Menu menu = menuMapper.getMenuByMenuId(m);
            menuList.add(menu);
        }


        List<Menu> pMenuList = new ArrayList<Menu>();
//          通过menuId找到menuList中有父节点的菜单，将父菜单存入pmenuList
        for (Menu menu : menuList) {
            if (menu.getPid()!=null){
                Menu pmenu = menuMapper.getParentMenuByMenuId(menu.getId());
                if (!pMenuList.contains(pmenu)){
                    pMenuList.add(pmenu);
                }
            }else {
                if (!pMenuList.contains(menu)){
                    pMenuList.add(menu);
                }
            }
        }

//          建立关系树
        for (Menu menu : pMenuList) {
//
            if (menuMapper.getChildCountById(menu.getId())!=0){
                menu.setChildren(menuList.stream()
                        .filter(m->menu.getId().equals(m.getPid()))
                        .collect(Collectors.toList()));
            }

        }

        return pMenuList;

    }





    //            List<Menu> menuList = new ArrayList<Menu>();
////            通过menuId查找出菜单元素列表
//            for (Integer m : menuIdList) {
//                Menu menu = menuMapper.getMenuByMenuId(m);
//                menuList.add(menu);
//            }
//
//
//            List<Menu> pMenuList = new ArrayList<Menu>();
////          通过menuId找到menuList中有父节点的菜单，将父菜单存入pmenuList
//            for (Menu menu : menuList) {
//                if (menu.getPid()!=null){
//                    Menu pmenu = menuMapper.getParentMenuByMenuId(menu.getId());
//                    if (!pMenuList.contains(pmenu)){
//                        pMenuList.add(pmenu);
//                    }
//                }else {
//                    if (!pMenuList.contains(menu)){
//                        pMenuList.add(menu);
//                    }
//                }
//            }
//
////          建立关系树
//            for (Menu menu : pMenuList) {
////
//                if (menuMapper.getChildCountById(menu.getId())!=0){
//                    menu.setChildren(menuList.stream()
//                            .filter(m->menu.getId().equals(m.getPid()))
//                            .collect(Collectors.toList()));
//                }
//
//            }




}
