package com.qingspring.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2022-08-15
 */
@Data
@TableName("sys_course")
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("课程名称")
    private String name;

    @ApiModelProperty("课程学分")
    private Integer score;

    @ApiModelProperty("上课时间")
    private String times;

    @ApiModelProperty("是否开课")
    private Boolean state;

    @ApiModelProperty("上课教师id")
    private Integer teacherId;

    @TableField(exist = false)
    private String teacherName;



}
