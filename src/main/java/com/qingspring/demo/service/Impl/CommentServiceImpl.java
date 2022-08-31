package com.qingspring.demo.service.Impl;

import com.qingspring.demo.common.ResponseEnum;
import com.qingspring.demo.entity.Comments;
import com.qingspring.demo.entity.Vo.CommentVo;
import com.qingspring.demo.exception.ServiceException;
import com.qingspring.demo.mongo.CommentRepository;
import com.qingspring.demo.service.CommentService;
import com.qingspring.demo.utils.mapstruct.CommentMapping;
import me.ahoo.cosid.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <h3>qingspring</h3>
 * <p>CommentService</p>
 *
 * @author : EasyBreezyhs
 * @date : 2022-08-28 09:29
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private CommentRepository commentDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Comments findById(String id) {

        Comments comments =commentDao.findById(id).isPresent()?commentDao.findById(id).get():null;

        if (comments ==null){
            throw new ServiceException(ResponseEnum.COMMENT_NOT_EXISRT);
        }
        return comments;
    }

    @Override
    public List<Comments> findAll() {

        return commentDao.findAll();
    }

    @Override
    public void save(Comments comments) {
        String id = idGenerator.generate()+"";
        comments.setId(id);

//        初始化数据
        comments.setPublishdate(new Date());
        comments.setThumbup(0);

        commentDao.save(comments);



    }

    @Override
    public void update(Comments comments) {
        commentDao.save(comments);
    }

    @Override
    public boolean deteleById(String id) {
        boolean present = commentDao.findById(id).isPresent();
        if (!present){
            throw new ServiceException(ResponseEnum.COMMENT_NOT_EXISRT);
        }else {
            commentDao.deleteById(id);
        }
        return present;
    }

    @Override
    public Page<Comments> findAllInPage(int pageNum, int pageSize, String name) {

        Pageable pageable = PageRequest.of(pageNum-1,pageSize);

        return commentDao.findPage(name,pageable);
    }

    @Override
    public List<Comments> findByArticleId(String articleId) {
//      找到该文章下的所有评论
        List<Comments> articleList = commentDao.findByArticleId(articleId);
//        所有顶级评论，默认时间倒序
        List<Comments> originList = articleList.stream().filter(comments -> Objects.equals(comments.getOriginId(), "")|| comments.getOriginId() == null).collect(Collectors.toList());

        for (Comments comments : originList) {
//            开销有点大啊，实现子回复按时间正序排序
            List<Comments> collect = commentDao.findByOriginId(articleId, comments.getId());
//            获得顶级评论下的所有回复
//            List<Comments> collect = articleList.stream().filter(comments1 -> comments.getId().equals(comments1.getOriginId())).collect(Collectors.toList());
//            转为Vo类
            List<CommentVo> commentVos = CommentMapping.INSTANCE.commentToVoList(collect);
//            设置回复对象
            commentVos.forEach(c1 ->{
                Optional<Comments> first = articleList.stream().filter(c2 -> c2.getId().equals(c1.getPid())).findFirst();
                first.ifPresent(v->{
                    c1.setPUserId(v.getUserId());
                    c1.setPnickName(v.getNickName());
                });
            });

            comments.setChildren(commentVos);
        }

        return originList;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void thumbup(String commentId,String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));
        Update update =new Update();
        update.addToSet("likeUserIdList",userId);
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"t_comment");

    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void thumbDown(String commentId,String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));
        Update update =new Update();
        update.pull("likeUserIdList",userId);
        update.inc("thumbup",-1);
        mongoTemplate.updateFirst(query,update,"t_comment");

    }


}
