package cn.qyd.blogroom.user.entity;

import cn.qyd.blogroom.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author qyd
 * @Date 19-2-22 下午4:23
 **/
@Entity
@Table(name = "attention")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Attention extends BaseEntity {
    @Column(name = "user1_id",columnDefinition = "mediumint(9) COMMENT '关注者id'")
    private Long user1Id;

    @Column(name = "user1_name",columnDefinition = "varchar(32) COMMENT '关注者姓名'")
    private String user1Name;

    @Column(name = "user1_image",columnDefinition = "varchar(255) COMMENT '关注者头像'")
    private String user1Image;

    @Column(name = "user2_id",columnDefinition = "mediumint(9) COMMENT '被关注者id'")
    private Long user2Id;

    @Column(name = "user2_name",columnDefinition = "varchar(32) COMMENT '被关注者姓名'")
    private String user2Name;

    @Column(name = "user2_image",columnDefinition = "varchar(255) COMMENT '被关注者头像'")
    private String user2Image;

}
