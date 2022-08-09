package com.qingspring.demo.mapper;

import com.qingspring.demo.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-02
 */
public interface MenuMapper extends BaseMapper<Menu> {

    Menu getMenuByMenuId(@Param("menuId") Integer menuId);

    Menu getParentMenuByMenuId(@Param("menuId") Integer menuId);

    Integer getChildCountById(@Param("menuId") Integer menuId);
}
