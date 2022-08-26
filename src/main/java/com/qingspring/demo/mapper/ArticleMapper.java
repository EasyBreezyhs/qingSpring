package com.qingspring.demo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingspring.demo.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qingspring.demo.entity.Vo.ArticleDetailVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author EasyBreezyhs
 * @since 2022-08-19
 */
public interface ArticleMapper extends BaseMapper<Article> {

    Page<Article> findMapper(Page<Article> page,@Param("title") String title, @Param("articleName") String articleName);

    ArticleDetailVo findArticleDetailById(@Param("id") Integer id);
}
