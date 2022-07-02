package com.qingspring.demo.mapper;

import com.qingspring.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**
* @author : EasyBreezyhs
* @date : 2022/07/02-14:32
*/
public interface UserMapper {

    List<User> findAll();

    int insert(User user);

    int update(User user);

    Integer deletUserById(Integer id);
}
