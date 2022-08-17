package com.qingspring.demo.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.Course;
import com.qingspring.demo.mapper.CourseMapper;
import com.qingspring.demo.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Page<Course> findPage(Page<Course> coursePage, String name) {

        return courseMapper.findPage(coursePage,name);
    }

    @Transactional
    @Override
    public void findCourseBystudentId(Integer courseId, Integer userId) {
        courseMapper.delStuById(courseId,userId);
        courseMapper.addCourseById(courseId,userId);
    }
}
