package cn.qyd.blogroom.article.dao;

import cn.qyd.blogroom.article.entity.Thumb;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author qyd
 * @Date 19-3-19 上午11:20
 **/
public interface ThumbDao extends JpaRepository<Thumb,Long> {
}
