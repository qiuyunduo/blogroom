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
@Table(name = "integral_config")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class IntegralConfig extends BaseEntity {
    @Column(name = "type",columnDefinition = "int(2) COMMENT '积分变动类型'")
    private Integer type;

    @Column(name = "describe",columnDefinition = "varchar(20) COMMENT '积分变动描述'")
    private String describe;

    @Column(name = "number",columnDefinition = "int(5) COMMENT '改变积分数量'")
    private Integer number;
}
