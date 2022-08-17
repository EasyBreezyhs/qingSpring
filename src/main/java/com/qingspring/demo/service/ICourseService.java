package com.qingspring.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-15
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> coursePage, String name);

    void findCourseBystudentId(Integer courseId, Integer userId);
}
