package com.qingspring.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.entity.Menu;
import com.qingspring.demo.mapper.MenuMapper;
import com.qingspring.demo.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getNode(QueryWrapper<Menu> menuQueryWrapper) {
        List<Menu> allList = list(menuQueryWrapper);
        List<Menu> parentNode = allList.stream().filter(m -> m.getPid()==null).collect(Collectors.toList());
        for (Menu menu : parentNode) {
            menu.setChildren(allList.stream().filter(m->menu.getId().equals(m.getPid())).collect(Collectors.toList()));

        }
        return parentNode;
    }
}
