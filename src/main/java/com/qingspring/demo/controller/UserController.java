package com.qingspring.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.controller.DTO.UserDTO;
import com.qingspring.demo.entity.Vo.UserVo;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.util.List;

import com.qingspring.demo.service.IUserService;
import com.qingspring.demo.entity.User;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private IUserService userService;

    //新增与更新
    @PostMapping("/insert")
    public Boolean insertOrUpdate(@RequestBody User user){
        return userService.saveOrUpdate(user);
        }

    //通过id删除
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
        }

    //批量删除
    @PostMapping("/deleteBatch")
    public Boolean deleteBatch(@RequestBody List<Integer> ids){
        return userService.removeBatchByIds(ids);
        }

    //查询所有
    @GetMapping
    public List<User> findAll(){
        return userService.list();
        }

    //通过id查询
    @GetMapping("/{id}")
    public List<User> findOne(@PathVariable Integer id){
        return userService.list();
        }

     //分页
    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize){

        Page<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        return userService.page(page);
        }

    @GetMapping("/export")
    public Boolean exp(HttpServletResponse response) throws Exception {
        List<User> userList = userService.list();
            return userService.exp(response,userList);
    }

    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws IOException {
        InputStream inputStream =file.getInputStream();
        ExcelReader reader =ExcelUtil.getReader(inputStream);
        List<User> userList =reader.readAll(User.class);
        userService.saveBatch(userList);
        return true;
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(ResponseEnum.PARAMETER_NULL,null);
        }
        userService.login(userDTO);

        return new Result(ResponseEnum.SUCCESS,userDTO);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(ResponseEnum.PARAMETER_NULL,null);
        }

        return new Result(ResponseEnum.SUCCESS,userService.register(userDTO));
    }





}

