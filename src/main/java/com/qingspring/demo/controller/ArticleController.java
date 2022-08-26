package com.qingspring.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.entity.Vo.ArticleDetailVo;
import com.qingspring.demo.utils.JWT.PassToken;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qingspring.demo.service.IArticleService;
import com.qingspring.demo.entity.Article;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-19
 */
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Resource
    private IArticleService articleService;

    //新增与更新
    @PostMapping("/insert")
    public Result insertOrUpdate(@RequestBody Article article){
        return Result.success(articleService.saveOrUpdate(article));
        }

    //通过id删除
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(articleService.removeById(id));
        }

    //批量删除
    @PostMapping("/del/Batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(articleService.removeBatchByIds(ids));
        }

    //查询所有
    @PassToken
    @GetMapping
    public Result findAll(){
        return Result.success(articleService.list());
        }

    //通过id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
            return Result.success(articleService.list());
        }

    //分页
    @PassToken
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize,
    @RequestParam(defaultValue = "") String title,
    @RequestParam(defaultValue = "") String articleName){
        Page<Article>page = articleService.findPage(new Page<Article>(pageNum,pageSize),title,articleName);

        return Result.success(page);
        }


    @PassToken
    @GetMapping("/articleDetail/{id}")
    public Result findArticleDetailById(@PathVariable Integer id){

        ArticleDetailVo articleDetailVo = articleService.findArticleDetailById(id);

        return Result.success(articleDetailVo);
    }


 }

