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
 * @Date 19-3-13 下午12:02
 **/
@Entity
@Table(name = "article_class")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ArticleClass extends BaseEntity {

    @Column(name = "name",columnDefinition = "varchar(60) COMMENT '文章类别名称'")
    private String name;

    @Column(name = "pid",columnDefinition = "mediumint(9) COMMENT '文章二级类别id'")
    private Long pid;
}
