package com.qingspring.demo.mongo;

import com.qingspring.demo.entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * <h3>qingspring</h3>
 * <p>CommentRepository</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-27 17:21
 **/
public interface CommentRepository extends MongoRepository<Comments, String> {


    @Query(value = "{'nickName': ?#{ ([0] == null) or ([0].length() == 0) ? {$exists:true} : {$regex: [0] }  }}",sort = "{publishdate : -1}")
    Page<Comments> findPage(String name, Pageable pageable);

    @Query(value = "{'articleId' : ?0}",sort = "{publishdate : -1}")
    List<Comments> findByArticleId(String articleId);

    @Query(value = "{'$and':[{'articleId' : ?0},{'originId':?1}]}",sort = "{publishdate : 1}")
    List<Comments> findByOriginId(String articleId,String originId);

//    value = "{'nickName': ?#{ ([0] == null) or ([0].length() == 0) ? '{$exists:true}':[0]  }}",


}
