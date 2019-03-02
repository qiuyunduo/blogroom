package cn.qyd.blogroom.website.entity;

import cn.qyd.blogroom.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author qyd
 * @Date 19-2-27 下午1:20
 **/
@Entity
@Table(name = "friendly_link")
@Data
@EqualsAndHashCode(callSuper = true)
public class FriendlyLink extends BaseEntity {
    @Column(name = "url",columnDefinition = "varchar(255) COMMENT '链接地址'")
    private String url;

    @Column(name = "content",columnDefinition = "varchar(32) COMMENT '链接文本内容'")
    private String content;

    @Column(name = "photo",columnDefinition = "varchar(255) COMMENT '链接图片'")
    private String photo;
}
