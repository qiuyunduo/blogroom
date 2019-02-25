package cn.qyd.blogroom.article.dao;

import cn.qyd.blogroom.article.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:54
 */
public interface CommentDao extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long articleId, Sort sort);
}
