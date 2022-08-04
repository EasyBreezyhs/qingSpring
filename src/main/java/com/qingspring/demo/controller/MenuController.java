package com.qingspring.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.utils.JWT.PassToken;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qingspring.demo.service.IMenuService;
import com.qingspring.demo.entity.Menu;

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
@RequestMapping("/menu")

public class MenuController {


    @Resource
    private IMenuService menuService;



    //新增与更新
    @PostMapping("/insert")

    public Result insertOrUpdate(@RequestBody Menu menu){
        return Result.success(menuService.saveOrUpdate(menu));
        }

    //通过id删除
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(menuService.removeById(id));
        }

    //批量删除
    @PostMapping("/del/Batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(menuService.removeBatchByIds(ids));
        }


    //通过id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
            return Result.success(menuService.list());
        }

    //分页
    //查询所有
    //    父子级菜单
    @GetMapping
    @PassToken
    public Result findAll(
    @RequestParam(defaultValue = "") String name){

        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        if (!"".equals(name)){
        queryWrapper.like("name",name);
        }
//        queryWrapper.orderByDesc("id");
        List<Menu> node = menuService.getNode(queryWrapper);
        if (node==null){
            throw new ServiceException(ResponseEnum.ERROR);
        }
        return Result.success(node);
        }

 }

