package com.qingspring.demo.mongo;

import com.qingspring.demo.entity.Comments;
import com.qingspring.demo.entity.ThumbupComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * <h3>qingspring</h3>
 * <p>Thumbup</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-30 14:32
 **/
public interface ThumbupRepository extends MongoRepository<ThumbupComment, String> {

    @Query(value = "{'$and':[{'likedUserId' : ?0},{'status':?1}]}",sort = "{thumbDate : -1}")
    Page<ThumbupComment> findLikedUserIdAndStatus(String likedUserId, Integer code, Pageable pageable);

    @Query(value = "{'$and':[{'likedCommentId' : ?0},{'status':?1}]}",sort = "{thumbDate : -1}")
    Page<ThumbupComment> findLikedCommentIdAndStatus(String likedCommentId, Integer code, Pageable pageable);

    @Query(value = "{'$and':[{'likedUserId' : ?0},{'likedCommentId':?1}]}",sort = "{thumbDate : -1}")
    ThumbupComment findByLikedUserIdAndLikedPostId(String likedUserId, String likedCommentId);
}
