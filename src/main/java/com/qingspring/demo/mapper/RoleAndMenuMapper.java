package com.qingspring.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingspring.demo.entity.RoleAndMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAndMenuMapper extends BaseMapper<RoleAndMenu> {


    List<Integer> getMenuIdByRoleId(@Param("roleId")Integer roleId);


    Boolean deleteByRoleId(@Param("roleId") Integer roleId);
}
