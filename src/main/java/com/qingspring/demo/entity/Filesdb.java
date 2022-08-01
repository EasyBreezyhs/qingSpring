package com.qingspring.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @since 2022-07-28
 */
@Getter
@Setter
@TableName("sys_file")
@ApiModel(value = "File对象", description = "")
public class Filesdb implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件大小/kb")
    private Long size;

    @ApiModelProperty("文件uuid")
    private String uuid;

    @ApiModelProperty("文件地址")
    private String url;

    @ApiModelProperty("md5")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String md5;

    @ApiModelProperty("是否删除")
    private Boolean isDelete;

    @ApiModelProperty("链接是否可用")
    private Boolean enable;




}
