package com.qingspring.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingspring.demo.common.ThumbUpStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * <h3>qingspring</h3>
 * <p>Thumupor:Like</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 13:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "t_thumbup")
public class ThumbupComment implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    private String id;

    //被点赞的用户的id
    private String likedUserId;

    //点赞的评论的id
    private String likedCommentId;

    //点赞的状态.默认未点赞
    private Integer status = ThumbUpStatusEnum.UNLIKE.getCode();

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date thumbDate;


}
