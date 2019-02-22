package cn.qyd.blogroom.article.service;

import cn.qyd.blogroom.article.entity.Comment;
import java.util.List;
/**
 * @Author qyd
 * @Date 19-2-22 下午4:22
 **/
public interface CommentService {
    int deleteByCid(Long commentId);

    int insertOne(Comment comment);

    Comment selectOneByCid(Long commentId);

    List<Comment> selectAllByAid(Long aid);

    int updateByComment(Comment comment);
}
