package com.qingspring.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.entity.User;
import com.qingspring.demo.service.IUserService;
import com.qingspring.demo.utils.JWT.PassToken;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qingspring.demo.service.ICourseService;
import com.qingspring.demo.entity.Course;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-15
 */
@RestController
@RequestMapping("/course")
public class CourseController {


    @Resource
    private ICourseService courseService;
    @Autowired
    private IUserService userService;

    //新增与更新
    @PostMapping("/insert")
    public Result insertOrUpdate(@RequestBody Course course){
        return Result.success(courseService.saveOrUpdate(course));
        }

    //通过id删除
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(courseService.removeById(id));
        }

    //批量删除
    @PostMapping("/del/Batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(courseService.removeBatchByIds(ids));
        }

    //查询所有
    @GetMapping
    public Result findAll(){
        return Result.success(courseService.list());
        }

    //通过id查询
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
            return Result.success(courseService.list());
        }

    //分页
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize,
    @RequestParam(defaultValue = "") String name){
        Page<Course> page = courseService.findPage(new Page<>(pageNum,pageSize),name);
        return Result.success(page);
    }


    @GetMapping("/role/{role}")
    public Result findUserByrole(@PathVariable String role){
            QueryWrapper<User> courseQueryWrapper = new QueryWrapper<>();
            courseQueryWrapper.eq("role",role);
//              少用myBatis自带的函数返回到前端，
//              减少不必要的信息传输
            List<User> list = userService.list(courseQueryWrapper);
            return Result.success(list);
    }

    @PostMapping("/update")
    public Result updateState(@RequestBody Course course){

        courseService.updateById(course);
        return Result.success();
    }


    @PostMapping("/studentCourse/{courseId}/{userId}")
    public Result findCourseBystudentId(@PathVariable Integer courseId,
                                        @PathVariable Integer userId){

        courseService.findCourseBystudentId(courseId,userId);

        return Result.success();
    }





 }

