package cn.qyd.blogroom.article.service.impl;

import cn.qyd.blogroom.article.dao.ArticleDao;
import cn.qyd.blogroom.article.dao.CommentDao;
import cn.qyd.blogroom.article.dto.CommentDto;
import cn.qyd.blogroom.article.entity.Comment;
import cn.qyd.blogroom.article.service.CommentService;
import cn.qyd.blogroom.common.exception.BusinessException;
import cn.qyd.blogroom.common.resp.code.FrontRespEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:11
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ArticleServiceImpl articleService;

    @Override
    public Comment save(CommentDto dto) {
        Integer floor = articleService.findById(dto.getArticleId()).getComments() + 1;
        Comment comment = new Comment();
        comment.setArticleId(dto.getArticleId())
                .setUserId(dto.getUserId())
                .setNickName(dto.getNickName())
                .setHeadImage(dto.getHeadImage())
                .setContent(dto.getContent())
                .setFloor(floor)
                .setAddTime(LocalDateTime.now());
        Comment result = commentDao.save(comment);
        return result;
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id)
                .orElseThrow(() -> BusinessException.fail(FrontRespEnum.COMMENT_NOT_EXIST));
    }

    @Override
    public List<Comment> articleComments(Long articleId) {
        Sort sort = Sort.by(Sort.Order.desc("addTime"));
        List<Comment> comments = commentDao.findAllByArticleId(articleId, sort);
        return comments;
    }

    @Override
    public Boolean update(Long id, String content) {
        Comment comment = findById(id);
        comment.setContent(content)
                .setUpdateTime(LocalDateTime.now());

        commentDao.save(comment);
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        commentDao.deleteById(id);
        return true;
    }
}
