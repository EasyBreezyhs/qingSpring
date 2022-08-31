package com.qingspring.demo.service.Impl;

import com.qingspring.demo.common.ThumbUpStatusEnum;
import com.qingspring.demo.controller.DTO.ThumbCountDTO;
import com.qingspring.demo.entity.Comments;
import com.qingspring.demo.entity.ThumbupComment;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.mongo.ThumbupRepository;
import com.qingspring.demo.service.CommentService;
import com.qingspring.demo.service.ThumbupRedisService;
import com.qingspring.demo.service.ThumbupService;
import me.ahoo.cosid.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <h3>qingspring</h3>
 * <p>ThumbService</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 15:43
 **/
@Service
public class ThumbServiceImpl implements ThumbupService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ThumbupRedisService redisService;

    @Autowired
    private ThumbupRepository thumbupDao;



    @Override
    public ThumbupComment save(ThumbupComment thumb) {
//        String id = idGenerator.generate()+"";
//        thumb.setId(id);
//        thumb.setThumbDate(new Date());
        return thumbupDao.save(thumb);
    }

    @Override
    public List<ThumbupComment> saveAll(List<ThumbupComment> thumbList) {
        return thumbupDao.saveAll(thumbList);
    }

    @Override
    public Page<ThumbupComment> getLikedListByLikedUserId(String likedUserId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return thumbupDao.findLikedUserIdAndStatus(likedUserId, ThumbUpStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Page<ThumbupComment> getLikedListByLikedPostId(String likedCommentId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return thumbupDao.findLikedCommentIdAndStatus(likedCommentId, ThumbUpStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public ThumbupComment getByLikedUserIdAndLikedPostId(String likedUserId, String likedCommentId) {
        return thumbupDao.findByLikedUserIdAndLikedPostId(likedUserId,likedCommentId);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void transLikedFromRedis2Mongo() {
        List<ThumbupComment> likeList = redisService.getLikedDataFromRedis();
        for (ThumbupComment like : likeList) {
            ThumbupComment ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedCommentId());
            if (ul == null){
//              没有记录，直接存入
                save(like);
            }else {
//              有记录，需要更新
                ul.setStatus(like.getStatus());
                save(ul);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void transLikedCountFromRedis2Mongo() {
        List<ThumbCountDTO> countList = redisService.getLikedCountFromRedis();
        for (ThumbCountDTO countDTO : countList) {
            Comments comment = commentService.findById(countDTO.getCommentId());
            if (comment!=null){
                Integer likeNum = comment.getThumbup()+countDTO.getCount();
                comment.setThumbup(likeNum);
                //更新点赞数量
                commentService.save(comment);
            }
        }
    }
}
