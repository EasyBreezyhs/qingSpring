package com.qingspring.demo.controller;

import com.qingspring.demo.common.RedisKeyEnum;
import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.common.Result;
import com.qingspring.demo.entity.Comments;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.service.CommentService;
import com.qingspring.demo.utils.JWT.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <h3>qingspring</h3>
 * <p>Comment</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-28 10:30
 **/

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Comments comments = commentService.findById(id);
        return Result.success(comments);
    }

    @GetMapping("/all")
    public Result findAll(){
        return Result.success(commentService.findAll());
    }

    @PassToken
    @PostMapping("/insert")
    public Result insertComment(@RequestBody Comments comments){
        if (comments.getId()==null){
            commentService.save(comments);
        }else {
            commentService.update(comments);
        }
        return Result.success();
    }


    @DeleteMapping("/del/{userId}/{commentId}")
    public Result deleteById(@PathVariable String userId,@PathVariable String commentId){
        if (!Objects.equals(commentService.findById(commentId).getUserId(), userId)){
         throw new ServiceException(ResponseEnum.COMMENT_DELETE);
        }else {
            commentService.deteleById(commentId);
        }
        return Result.success();
    }

    @PassToken
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String nickName){
        if (pageNum <= 0){
            pageNum = 1;
        }
        if (pageSize <= 0){
            pageSize = 10;
        }
        return Result.success(commentService.findAllInPage(pageNum,pageSize,nickName));
    }

    @PassToken
    @GetMapping("/article/{articleId}")
    public Result findByArticleId(@PathVariable String articleId){

        return Result.success(commentService.findByArticleId(articleId));
    }




}
