package com.qingspring.demo.entity.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h3>qingspring</h3>
 * <p>FilesdbVo to Front</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-17 14:24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesdbVo {

    @ApiModelProperty("文件Id")
    private Integer id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件大小/kb")
    private Long size;

    @ApiModelProperty("文件地址")
    private String url;

    @ApiModelProperty("链接是否可用")
    private Boolean enable;


}
