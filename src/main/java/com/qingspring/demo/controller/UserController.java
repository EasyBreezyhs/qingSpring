package com.qingspring.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.controller.DTO.UserDTO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

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
    public Result insertOrUpdate(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
        }

    //通过id删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
        }

    //批量删除
    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(userService.removeBatchByIds(ids));
        }

    //查询所有
    @GetMapping
    public Result findAll(){
        return Result.success(userService.list());
        }

    //通过id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(userService.list());
        }

     //分页
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String username,
                           @RequestParam(defaultValue = "") String email,
                           @RequestParam(defaultValue = "") String address){

        Page<User> page=new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if (!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)){
            queryWrapper.like("address",address);
        }

        return Result.success(userService.page(page,queryWrapper));
        }

    @GetMapping("/export")
    public Result exp(HttpServletResponse response) throws Exception {
        List<User> userList = userService.list();
            return Result.success(userService.exp(response,userList));
    }

    @PostMapping("/import")
    public Result imp(MultipartFile file) throws IOException {
        InputStream inputStream =file.getInputStream();
        ExcelReader reader =ExcelUtil.getReader(inputStream);
        List<User> userList =reader.readAll(User.class);

        return Result.success(userService.saveBatch(userList));
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(ResponseEnum.PARAMETER_NULL,null);
        }
        UserDTO dto = userService.login(userDTO);

        return new Result(ResponseEnum.SUCCESS,dto);
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String username = userDTO.getReusername();
        String password = userDTO.getRepassword();
        String repassword = userDTO.getReregpassword();
        if (!Objects.equals(password, repassword)){
            return Result.error(ResponseEnum.REGISTER_DIFFERENT,null);
        }
        if (StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(ResponseEnum.PARAMETER_NULL,null);
        }

        return new Result(ResponseEnum.SUCCESS,userService.register(userDTO));
    }





}

