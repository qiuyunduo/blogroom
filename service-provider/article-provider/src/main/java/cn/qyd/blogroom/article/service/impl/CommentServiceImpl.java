package cn.qyd.blogroom.article.service.impl;

import cn.qyd.blogroom.article.dao.CommentDao;
import cn.qyd.blogroom.article.dto.ArticleStatusDto;
import cn.qyd.blogroom.article.dto.CommentDto;
import cn.qyd.blogroom.article.entity.Article;
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
                .setAuthorId(dto.getAuthorId())
                .setContent(dto.getContent())
                .setFloor(floor)
                .setAddTime(LocalDateTime.now());
        Comment result = commentDao.save(comment);

        ArticleStatusDto statusDto = new ArticleStatusDto();
        statusDto.setId(dto.getArticleId());
        statusDto.setAddComments(true);
        articleService.updateStatus(statusDto);
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
        List<Comment> comments = commentDao.findAllByArticleIdOrderByFloor(articleId, sort);
        return comments;
    }

    @Override
    public Long countCommentsOfUser(Long id) {
        return commentDao.countByAuthorId(id);
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
    public Boolean delete(Long id, Long articleId) {
        findById(id);
        commentDao.deleteById(id);
        ArticleStatusDto statusDto = new ArticleStatusDto();
        statusDto.setId(articleId);
        statusDto.setAddComments(true);
        articleService.updateStatus(statusDto);
        return true;
    }
}
