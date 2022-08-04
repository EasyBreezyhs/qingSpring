package com.qingspring.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.service.IRoleAndMenuService;
import com.qingspring.demo.utils.JWT.PassToken;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qingspring.demo.service.IRoleService;
import com.qingspring.demo.entity.Role;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-02
 */
@RestController
@RequestMapping("/role")

public class RoleController {


    @Resource
    private IRoleService roleService;
    @Resource
    private IRoleAndMenuService roleAndMenuService;

    //新增与更新
    @PostMapping("/insert")
    public Result insertOrUpdate(@RequestBody Role role){
        return Result.success(roleService.saveOrUpdate(role));
        }

    //通过id删除
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(roleService.removeById(id));
        }

    //批量删除
    @PostMapping("/del/Batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(roleService.removeBatchByIds(ids));
        }

    //查询所有
    @GetMapping
    public Result findAll(){
        return Result.success(roleService.list());
        }

    //通过id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
            return Result.success(roleService.list());
        }

    //分页
    @GetMapping("/page")
    public Page<Role> findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize,
    @RequestParam(defaultValue = "") String name){

        Page<Role> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
        if (!"".equals(name)){
        queryWrapper.like("username",name);
        }
        //queryWrapper.orderByDesc("id");
        return roleService.page(page,queryWrapper);
        }

    @GetMapping("/roleMenu/{roleId}")
    public Result getMenuId(@PathVariable Integer roleId){
        List<Integer> menuId = roleAndMenuService.getMenuId(roleId);
        return Result.success(menuId);
    }

    @PostMapping("/roleMenu/{roleId}")
    public Result saveMenuId(@PathVariable Integer roleId,
                             @RequestBody List<Integer> menuId){
        boolean flag = roleAndMenuService.saveMenuByRoleId(roleId,menuId);
        if (!flag){
            return Result.error();
        }
        return Result.success();
    }


 }

