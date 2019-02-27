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
 * @Date 19-2-27 上午9:46
 **/
@Entity
@Table(name = "rank")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Rank extends BaseEntity {
    @Column(name = "level",columnDefinition = "int(5) COMMENT '博客等级'")
    private Integer level;

    @Column(name = "integral",columnDefinition = "int(8) COMMENT '等级积分要求'")
    private Integer integral;

    @Column(name = "name",columnDefinition = "varchar(32) COMMENT '博客等级称号'")
    private String name;
}
