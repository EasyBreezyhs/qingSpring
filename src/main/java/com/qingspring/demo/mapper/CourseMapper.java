package com.qingspring.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-15
 */
public interface CourseMapper extends BaseMapper<Course> {

    Page<Course> findPage(Page<Course> coursePage, String name);

    void delStuById(@Param("courseId") Integer courseId,@Param("userId") Integer userId);

    void addCourseById(@Param("courseId") Integer courseId, @Param("userId") Integer userId);
}
