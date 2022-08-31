package com.qingspring.demo.entity.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <h3>qingspring</h3>
 * <p>Children</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-29 15:40
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo implements Serializable {
    private static final long serialVersionUID = 2L;

    private String id;
    private String articleId;
    private String content;
    private String userId;
    private String nickName;
    private String userAvatar;
    private String pid;
    private String originId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date publishdate;
    private Integer thumbup;

    private List<String> likeUserIdList;

    private String pnickName;
    private String pUserId;



}
