package com.qingspring.demo.service;

import com.qingspring.demo.controller.DTO.ThumbCountDTO;
import com.qingspring.demo.entity.ThumbupComment;

import java.util.List;

public interface ThumbupRedisService {

//    点赞 状态为 1
    void saveThumup(String likeUserId,String likePostId);

//    取消点赞 状态为0
    void cancelThumup(String likeUserId,String likePostId);

//    删除点赞数据
    void deleteThumupRedis(String likeUserId,String likePostId);

//    查询记录
    Object getThumbById(String likeUserId, String likePostId);

//  点赞加一
    void incrementLikedCount(String likedUserId);

//    点赞减一
    void decrementLikedCount(String likedUserId);

//    获取Redis中所有点赞数据
    List<ThumbupComment> getLikedDataFromRedis();

//    获取Redis中存储的所有点赞数量
    List<ThumbCountDTO> getLikedCountFromRedis();

}
