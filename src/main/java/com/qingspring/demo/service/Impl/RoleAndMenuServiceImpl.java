package com.qingspring.demo.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.entity.RoleAndMenu;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.mapper.RoleAndMenuMapper;
import com.qingspring.demo.service.IRoleAndMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <h3>qingspring</h3>
 * <p>RoleAndMenuService</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-04 16:08
 **/
@Service
public class RoleAndMenuServiceImpl extends ServiceImpl<RoleAndMenuMapper, RoleAndMenu> implements IRoleAndMenuService {
    @Resource
    RoleAndMenuMapper roleAndMenuMapper;

//    根据RoleId 找到关联的menuID
    @Override
    public List<Integer> getMenuId(Integer roleId) {
        return roleAndMenuMapper.getMenuIdByRoleId(roleId);
    }

//    两个操作以上使用事务管理，回滚
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveMenuByRoleId(Integer roleId, List<Integer> menuId) {

//        先删再增
        if (!roleAndMenuMapper.deleteByRoleId(roleId)){
            throw new ServiceException(ResponseEnum.ERROR);
        }

        for (Integer m : menuId) {
            RoleAndMenu roleAndMenu = new RoleAndMenu();
            roleAndMenu.setRoleId(roleId);
            roleAndMenu.setMenuId(m);
            roleAndMenuMapper.insert(roleAndMenu);
        }
        return true;
    }
}
