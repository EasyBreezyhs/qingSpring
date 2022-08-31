package com.qingspring.demo.service;

import com.qingspring.demo.entity.Comments;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

//    通过id查找
    Comments findById(String id);
//    查找全部
    List<Comments> findAll();

//    新增
    void save(Comments comments);
//    更新
    void update(Comments comments);

//    删除
    boolean deteleById(String id);

//    分页
    Page<Comments> findAllInPage(int pageNum, int pageSize, String name);


    List<Comments> findByArticleId(String articleId);

//      点赞加一
    void thumbup(String commentId,String userId);

//    点赞数减一
    void thumbDown(String commentId,String userId);


}
