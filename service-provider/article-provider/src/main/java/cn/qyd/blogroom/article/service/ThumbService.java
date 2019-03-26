package cn.qyd.blogroom.article.service;

/**
 * @Author qyd
 * @Date 19-3-19 上午11:21
 **/
public interface ThumbService {
    Long countThumbsOfUser(Long id);

    Boolean save(Long articleId, Long userId);

    void delete(Long articleId, Long userId);
}
