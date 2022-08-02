package com.qingspring.demo.mapper;

import com.qingspring.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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


    String getCount();
}
