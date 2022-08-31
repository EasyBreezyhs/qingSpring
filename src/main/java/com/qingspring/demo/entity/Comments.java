package com.qingspring.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingspring.demo.entity.Vo.CommentVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <h3>qingspring</h3>
 * <p>Comments</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-27 17:06
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "t_comment")
public class Comments implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
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

    private List<String> likeUserIdList = Collections.emptyList();

    @Transient
    private List<CommentVo> children;

}
