package cn.qyd.blogroom.article.dao;

import cn.qyd.blogroom.article.entity.ArticleClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleClassDao extends JpaRepository<ArticleClass, Long> {
}
