package cn.qyd.blogroom.article.entity;

import cn.qyd.blogroom.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author qyd
 * @Date 19-3-19 上午11:19
 **/
@Entity
@Table(name = "thumb")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Thumb extends BaseEntity {
    @Column(name = "article_id", columnDefinition = "mediumint(9) COMMENT '文章id'")
    private Long articleId;

    @Column(name = "user_id", columnDefinition = "mediumint(9) COMMENT '点赞者id'")
    private Long userId;
}