package com.qingspring.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qingspring.demo.entity.RoleAndMenu;

import java.util.List;

public interface IRoleAndMenuService extends IService<RoleAndMenu> {

    List<Integer> getMenuId(Integer roleId);

    Boolean saveMenuByRoleId(Integer roleId, List<Integer> menuId);
}
