package cn.qyd.blogroom.article.service;

import cn.qyd.blogroom.article.dto.ThumbDto;
import cn.qyd.blogroom.article.entity.Thumb;

/**
 * @Author qyd
 * @Date 19-3-19 上午11:21
 **/
public interface ThumbService {
    Long countThumbsOfUser(Long id);

    Thumb save(ThumbDto dto);

    Thumb findOne(Long articleId, Long userId);

    void delete(Long id, Long articleId);
}
