package com.qingspring.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qingspring.demo.entity.Vo.ArticleDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-19
 */
public interface IArticleService extends IService<Article> {

    Page<Article> findPage(Page<Article> page, String title, String articleName);

    ArticleDetailVo findArticleDetailById(Integer id);
}
