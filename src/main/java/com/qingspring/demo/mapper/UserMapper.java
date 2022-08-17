package com.qingspring.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.Course;
import com.qingspring.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-07-07
 */

public interface UserMapper extends BaseMapper<User> {

//    用户总数
    String getCount();
//    用户所拥有的菜单id
    List<Integer> getMenuIdByUserId(@Param("userId") Integer userId);

    User getUserByname(@Param("username") String name);

    boolean modifyPassword(@Param("password") String newPassword,@Param("id") Integer id);

    Page<Course> findPage(Page<Course> page, String username, String email, String address);
}
