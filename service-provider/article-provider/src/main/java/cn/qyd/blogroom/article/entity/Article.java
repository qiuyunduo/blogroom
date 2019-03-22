package cn.qyd.blogroom.article.entity;

import cn.qyd.blogroom.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-2-22 上午11:56
 **/
@Entity
@Table(name = "article")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Article extends BaseEntity {
    @Column(name = "class_id",columnDefinition = "mediumint(9) COMMENT '文章类别'")
    private Long classId;

    @Column(name = "user_id",columnDefinition = "mediumint(9) COMMENT '文章作者id'")
    private Long userId;

    @Column(name = "author",columnDefinition = "varchar(20) COMMENT '文章作者'")
    private String author;

    @Column(name = "introduction",columnDefinition = "varchar(200) COMMENT '文章简介'")
    private String introduction;

    @Column(name = "content",columnDefinition = "longtext COMMENT '文章正文'")
    private String content;

    @Column(name = "title",columnDefinition = "VARCHAR COMMENT '标题'")
    private String title;

    @Column(name = "keyword",columnDefinition = "VARCHAR COMMENT '关键词'")
    private String keyword;

    @Column(name = "head_photo",columnDefinition = "varchar(100) COMMENT '文章标题图片'")
    private String headPhoto;

    @Column(name = "status",columnDefinition = "int(1) COMMENT '文章状态 (0草稿，1待审核，2.审核通过，3审核失败)'")
    private Integer status;

    @Column(name = "comments",columnDefinition = "int(8) COMMENT '文章获得评论数'")
    private Integer comments;

    @Column(name = "thumbs",columnDefinition = "int(8) COMMENT '文章获赞次数")
    private Integer thumbs;

    @Column(name = "submit_time",columnDefinition = "DATETIME COMMENT '文章提交审核时间'")
    private LocalDateTime submitTime;

    @Column(name = "publish_time",columnDefinition = "DATETIME COMMENT '文章发布时间'")
    private LocalDateTime publishTime;
}
