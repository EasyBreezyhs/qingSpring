package com.qingspring.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-02
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("menu表主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("menu名字")
    private String name;

    @ApiModelProperty("菜单路径")
    private String path;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("菜单描述")
    private String description;

    @ApiModelProperty("父级菜单")
    private Integer pid;

    private String pagePath;


    @TableField(exist = false)
    private List<Menu> children;




    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId()+this.getName()+this.getPath()+this.getIcon());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Menu){
            Menu m = (Menu) obj;
            return (m.getId().equals(id)&&
                    m.getName().equals(name)&&
                    m.getPath().equals(path)&&
                    m.getIcon().equals(icon)
                    );
        }
        return super.equals(obj);
    }
}
