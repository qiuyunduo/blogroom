package cn.qyd.blogroom.article.service;

import cn.qyd.blogroom.article.dto.ArticleDto;
import cn.qyd.blogroom.article.dto.ArticleQueryDto;
import cn.qyd.blogroom.article.dto.ArticleStatusDto;
import cn.qyd.blogroom.article.entity.Article;
import cn.qyd.blogroom.article.vo.CountInfoVo;
import org.springframework.data.domain.Page;

import java.util.List;
/**
 * @Author qyd
 * @Date 19-2-22 下午12:26
 **/
public interface ArticleService {
    Article save(ArticleDto dto);

    Page<Article> query(ArticleQueryDto dto);

    Article findById(Long id);

    List<Article> fashionArticles();

    Long countArticlesOfUser(Long id);

    Boolean update(ArticleDto dto);

    Boolean updateStatus(ArticleStatusDto statusDto);

    Boolean delete(Long id);
}
