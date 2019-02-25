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
 * @Date 19-2-22 下午4:21
 **/
@Entity
@Table(name = "comment")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity {
    @Column(name = "article_id",columnDefinition = "mediumint(9) COMMENT '文章id'")
    private Long articleId;

    @Column(name = "user_id",columnDefinition = "mediumint(9) COMMENT '评论者id'")
    private Long userId;

    @Column(name = "nick_name",columnDefinition = "varchar(32) COMMENT '评论者昵称'")
    private String nickName;

    @Column(name = "head_image",columnDefinition = "varchar(255) COMMENT '评论者头像'")
    private String headImage;

    @Column(name = "content",columnDefinition = "text COMMENT '评论内容'")
    private String content;

    @Column(name = "floor",columnDefinition = "int(8) COMMENT '评论楼层'")
    private Integer floor;

}
