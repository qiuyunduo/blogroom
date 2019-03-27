package cn.qyd.blogroom.blog.entity;

import cn.qyd.blogroom.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author qyd
 * @Date 19-2-27 上午10:35
 **/
@Entity
@Table(name = "integral_log")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IntegralLog extends BaseEntity {
    @Column(name = "blog_id",columnDefinition = "mediumint(9) COMMENT '博客id'")
    private Long blogId;

    @Column(name = "type",columnDefinition = "mediumint(9) COMMENT '变动类型'")
    private Long type;

    @Column(name = "article_id",columnDefinition = "mediumint(9) COMMENT '如果是因为文章而产生积分变动，文章id'")
    private Long articleId;
}
