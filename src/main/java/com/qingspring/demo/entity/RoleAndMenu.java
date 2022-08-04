package com.qingspring.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <h3>qingspring</h3>
 * <p>sys_role_menu</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-04 15:24
 **/

@Data
@TableName("sys_role_menu")
@ApiModel(value = "RoleAndMenu对象", description = "")
public class RoleAndMenu implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer roleId;

    private Integer menuId;

}
