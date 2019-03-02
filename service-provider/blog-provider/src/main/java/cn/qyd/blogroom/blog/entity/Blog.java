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
 * @Date 19-2-22 下午12:22
 **/
@Entity
@Table(name = "blog")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Blog extends BaseEntity {

    @Column(name = "blog_name",columnDefinition = "varchar(32) COMMENT '博客名称'")
    private String blogName;

    @Column(name = "user_id",columnDefinition = "mediumint(9) COMMENT '拥有该博客的用户id'")
    private Long userId;

    @Column(name = "rank_id",columnDefinition = "mediumint(9) COMMENT '博客等级id'")
    private Long rankId;

    @Column(name = "integral",columnDefinition = "int(8) COMMENT '博客积分'")
    private Integer integral;

    @Column(name = "article_number",columnDefinition = "int(8) COMMENT '博客文章总量'")
    private Integer articleNumber;

    @Column(name = "fans_number",columnDefinition = "int(8) COMMENT '粉丝数'")
    private Integer fansNumber;

    @Column(name = "like_number",columnDefinition = "int(8) COMMENT '博客所有文章被赞总数'")
    private Integer likeNumber;

    @Column(name = "comment_number",columnDefinition = "int(8) COMMENT '博客所有文章获得评论总数'")
    private Integer commentNumber;

    @Column(name = "visit_number",columnDefinition = "int(8) COMMENT '博客访客'")
    private Integer visitNumber;

    @Column(name = "ranking",columnDefinition = "int(8) COMMENT '排名'")
    private Integer ranking;

    @Column(name = "status",columnDefinition = "int(1) COMMENT '博客状态(0.正常　1.禁用)'")
    private Integer status;
}
