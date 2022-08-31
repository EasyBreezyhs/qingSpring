package com.qingspring.demo.service.Impl;

import com.qingspring.demo.common.RedisKeyEnum;
import com.qingspring.demo.common.ThumbUpStatusEnum;
import com.qingspring.demo.controller.DTO.ThumbCountDTO;
import com.qingspring.demo.entity.ThumbupComment;
import com.qingspring.demo.service.ThumbupRedisService;
import me.ahoo.cosid.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <h3>qingspring</h3>
 * <p> ThumbupRedisService</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 15:16
 **/
@Service
public class ThumbupRedisServiceImpl implements ThumbupRedisService {

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public void saveThumup(String likeUserId, String likePostId) {
        String key = RedisKeyEnum.getThumupKey(likeUserId,likePostId);
        redisTemplate.opsForHash().put(RedisKeyEnum.USER_THUMUP_KEY.getKey(),key, ThumbUpStatusEnum.LIKE.getCode());


    }

    @Override
    public void cancelThumup(String likeUserId, String likePostId) {
        String key = RedisKeyEnum.getThumupKey(likeUserId,likePostId);
        redisTemplate.opsForHash().put(RedisKeyEnum.USER_THUMUP_KEY.getKey(),key, ThumbUpStatusEnum.UNLIKE.getCode());


    }

    @Override
    public void deleteThumupRedis(String likeUserId, String likePostId) {
        String key = RedisKeyEnum.getThumupKey(likeUserId,likePostId);
        redisTemplate.opsForHash().delete(RedisKeyEnum.USER_THUMUP_KEY.getKey(),key);
    }

    @Override
    public Object getThumbById(String likeUserId, String likePostId) {
        return redisTemplate.opsForHash().get(RedisKeyEnum.USER_THUMUP_KEY.getKey(),likeUserId+"::"+likePostId);
    }

    @Override
    public void incrementLikedCount(String likedCommentId) {

        redisTemplate.opsForHash().increment(RedisKeyEnum.COMMENT_THUMUP_KEY.getKey(),likedCommentId,1);

    }

    @Override
    public void decrementLikedCount(String likedCommentId) {
        redisTemplate.opsForHash().increment(RedisKeyEnum.COMMENT_THUMUP_KEY.getKey(),likedCommentId,-1);
    }

    @Override
    public List<ThumbupComment> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyEnum.USER_THUMUP_KEY.getKey(), ScanOptions.NONE);
        List<ThumbupComment> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedPostId
            String[] split = key.split("::");
            String likedUserId = split[0];
            String likedPostId = split[1];
            Integer value = (Integer) entry.getValue();
            //组装成 humbupComment 对象
            String id = idGenerator.generate()+"";

            ThumbupComment userLike = new ThumbupComment(id,likedUserId,likedPostId,value,new Date());
            list.add(userLike);

            //存到 list 后从 Redis 中删除
            redisTemplate.opsForHash().delete(RedisKeyEnum.USER_THUMUP_KEY.getKey(), key);
        }

        return list;
    }

    @Override
    public List<ThumbCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyEnum.COMMENT_THUMUP_KEY.getKey(), ScanOptions.NONE);
        List<ThumbCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDT
            String key = (String)map.getKey();
            String id = idGenerator.generate()+"";
            ThumbCountDTO dto = new ThumbCountDTO(id,key,(Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(RedisKeyEnum.COMMENT_THUMUP_KEY.getKey(), key);
        }

        return list;
    }
}
