package com.qingspring.demo.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.Article;
import com.qingspring.demo.entity.Vo.ArticleDetailVo;
import com.qingspring.demo.mapper.ArticleMapper;
import com.qingspring.demo.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Page<Article> findPage(Page<Article> page, String title, String articleName) {
        return articleMapper.findMapper(page,title,articleName);
    }

    @Override
    public ArticleDetailVo findArticleDetailById(Integer id) {
        return articleMapper.findArticleDetailById(id);
    }
}
