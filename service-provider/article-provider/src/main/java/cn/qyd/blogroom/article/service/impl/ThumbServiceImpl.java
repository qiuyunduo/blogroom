package cn.qyd.blogroom.article.service.impl;

import cn.qyd.blogroom.article.dao.ThumbDao;
import cn.qyd.blogroom.article.dto.ArticleStatusDto;
import cn.qyd.blogroom.article.entity.Thumb;
import cn.qyd.blogroom.article.service.ArticleService;
import cn.qyd.blogroom.article.service.ThumbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Boolean save(Long articleId, Long userId) {
        Thumb thumb = new Thumb();
        thumb.setArticleId(articleId)
                .setUserId(userId)
                .setAddTime(LocalDateTime.now());

        thumbDao.save(thumb);
        ArticleStatusDto statusDto = new ArticleStatusDto();
        statusDto.setAddThumbs(true);
        articleService.updateStatus(statusDto);

        return true;
    }
}
