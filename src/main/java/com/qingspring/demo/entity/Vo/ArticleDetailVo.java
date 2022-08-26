package com.qingspring.demo.entity.Vo;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingspring.demo.controller.DTO.UserDTO;
import com.qingspring.demo.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <h3>qingspring</h3>
 * <p>articleDetail</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-25 19:53
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo {

    @ApiModelProperty("文章id")
    private Integer id;

    @ApiModelProperty("作者id")
    private Integer userId;

    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("点赞")
    private Integer likes;

    @ApiModelProperty("文章标题")
    private String title;

    @Alias("发布时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private String articleName;

    private String userAvatar;

    @ApiModelProperty("该用户文章数")
    private Integer atricleCount;

    @ApiModelProperty("点赞总数")
    private Integer likeCount;



}
