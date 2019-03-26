package cn.qyd.blogroom.article.dao;

import cn.qyd.blogroom.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<Article,Long> {
    Page<Article> findAll(Specification<Article> specification, Pageable pageable);

    Long countByUserId(Long id);
}
