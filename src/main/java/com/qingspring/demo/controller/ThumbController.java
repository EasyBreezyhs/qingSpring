package com.qingspring.demo.controller;

import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.common.ThumbUpStatusEnum;
import com.qingspring.demo.entity.ThumbupComment;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.service.CommentService;
import com.qingspring.demo.service.ThumbupRedisService;
import com.qingspring.demo.service.ThumbupService;
import com.qingspring.demo.utils.JWT.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>qingspring</h3>
 * <p>thumbControll</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 16:54
 **/
@RestController
@RequestMapping("/thumb")
public class ThumbController {

    @Autowired
    private ThumbupRedisService thumbupRedisService;
    @Autowired
    private ThumbupService thumbupService;
    @Autowired
    private CommentService commentService;


    @PassToken
    @GetMapping("/{userId}/{commentId}")
    public Result thumupById(@PathVariable String userId, @PathVariable String commentId){

        Boolean flag;
        if (userId==null|| "".equals(userId)){
            throw new ServiceException(ResponseEnum.NOT_LOGIN);
        }

        //        在redis中查询用户是否已经点赞
        Integer thumbById = (Integer) thumbupRedisService.getThumbById(userId, commentId);
        if (thumbById == null){
            flag = null;
        }else {
            flag = thumbById != 0;
        }

        if(flag == null){
            ThumbupComment t = thumbupService.getByLikedUserIdAndLikedPostId(userId, commentId);
            if (t==null){
                commentService.thumbup(commentId,userId);
                thumbupRedisService.saveThumup(userId,commentId);
            }else if (t.getStatus().equals(0)){
                commentService.thumbup(commentId,userId);
                thumbupRedisService.saveThumup(userId,commentId);
            } else if (t.getStatus().equals(1)){
                commentService.thumbDown(commentId,userId);
                thumbupRedisService.cancelThumup(userId,commentId);
            }
        }else if (flag){
            commentService.thumbDown(commentId,userId);
            thumbupRedisService.cancelThumup(userId,commentId);
        }else {
            commentService.thumbup(commentId,userId);
            thumbupRedisService.saveThumup(userId,commentId);
        }

//        thumbupRedisService.saveThumup(userId,commentId);
//        thumbupRedisService.cancelThumup(userId,commentId);

        return Result.success(Boolean.FALSE.equals(flag));
    }


}
