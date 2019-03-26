package cn.qyd.blogroom.article.service;

import cn.qyd.blogroom.article.dto.CommentDto;
import cn.qyd.blogroom.article.entity.Comment;
import java.util.List;
/**
 * @Author qyd
 * @Date 19-2-22 下午4:22
 **/
public interface CommentService {
    Comment save(CommentDto dto);

    Comment findById(Long id);

    List<Comment> articleComments(Long articleId);

    Long countCommentsOfUser(Long id);

    Boolean update(Long id,String content);

    Boolean delete(Long id);
}
