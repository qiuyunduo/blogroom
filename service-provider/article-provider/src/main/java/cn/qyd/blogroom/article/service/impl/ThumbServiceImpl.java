package cn.qyd.blogroom.article.service.impl;

import cn.qyd.blogroom.article.dao.ThumbDao;
import cn.qyd.blogroom.article.dto.ArticleStatusDto;
import cn.qyd.blogroom.article.dto.ThumbDto;
import cn.qyd.blogroom.article.entity.Thumb;
import cn.qyd.blogroom.article.service.ArticleService;
import cn.qyd.blogroom.article.service.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-3-19 上午11:23
 **/
@Service
public class ThumbServiceImpl implements ThumbService {
    @Autowired
    private ThumbDao thumbDao;

    @Autowired
    private ArticleService articleService;

    @Override
    public Long countThumbsOfUser(Long id) {
        return thumbDao.countByAuthorId(id);
    }

    @Override
    public Thumb save(ThumbDto dto) {
        Thumb thumb = new Thumb();
        thumb.setArticleId(dto.getArticleId())
                .setAuthorId(dto.getAuthorId())
                .setUserId(dto.getUserId())
                .setAddTime(LocalDateTime.now());

        Thumb result = thumbDao.save(thumb);
        ArticleStatusDto statusDto = new ArticleStatusDto();
        statusDto.setId(dto.getArticleId());
        statusDto.setAddThumbs(true);
        articleService.updateStatus(statusDto);

        return result;
    }

    @Override
    public Thumb findOne(Long articleId, Long userId) {
        Thumb thumb = thumbDao.findByUserIdAndArticleId(userId, articleId);
        return thumb;
    }

    @Override
    public void delete(Long id, Long articleId) {
        ArticleStatusDto statusDto = new ArticleStatusDto();
        statusDto.setId(articleId);
        statusDto.setAddThumbs(true);
        articleService.updateStatus(statusDto);
        thumbDao.deleteById(id);
    }
}
