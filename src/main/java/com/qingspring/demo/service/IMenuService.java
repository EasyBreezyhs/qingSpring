package com.qingspring.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qingspring.demo.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-02
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getNode(QueryWrapper<Menu> menuQueryWrapper);
}
