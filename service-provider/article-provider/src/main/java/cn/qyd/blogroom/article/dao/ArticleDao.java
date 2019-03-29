package cn.qyd.blogroom.article.dao;

import cn.qyd.blogroom.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleDao extends JpaRepository<Article,Long> {
    Page<Article> findAll(Specification<Article> specification, Pageable pageable);

    @Modifying
    @Query(value = "update article set article.author = ?1 where article.user_id = ?2", nativeQuery = true)
    void updateAuthorByUserId(String nickName, Long userId );

    Long countByUserId(Long id);

    Long countByStatus(Integer status);
}
