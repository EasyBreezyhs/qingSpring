package com.qingspring.demo.service;

import com.qingspring.demo.entity.ThumbupComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * <h3>qingspring</h3>
 * <p>Thumbup</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 14:34
 **/
public interface ThumbupService {
//    保存一个
    ThumbupComment save(ThumbupComment thumb);

//    批量保存
    List<ThumbupComment> saveAll(List<ThumbupComment> thumbList);

//    点赞列表
    Page<ThumbupComment> getLikedListByLikedUserId(String likedUserId, int pageNum, int pageSize);

//    被点赞记录
    Page<ThumbupComment> getLikedListByLikedPostId(String likedCommentId, int pageNum, int pageSize);

//    通过被点赞人和点赞人id查询是否存在点赞记录
    ThumbupComment getByLikedUserIdAndLikedPostId(String likedUserId, String likedCommentId);

//    将Redis里的点赞数据存入数据库中
    void transLikedFromRedis2Mongo();

//    将Redis中的点赞数量数据存入数据库
    void transLikedCountFromRedis2Mongo();

//    联合查询
//    void thumbAndComment();

}
