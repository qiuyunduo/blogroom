package cn.qyd.blogroom.website.entity;

import cn.qyd.blogroom.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @Author qyd
 * @Date 19-2-27 下午1:20
 **/
@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Admin extends BaseEntity {
    @Column(name = "name",columnDefinition = "varchar(32) COMMENT '姓名'")
    private String name;

    @Column(name = "password",columnDefinition = "varchar(64) COMMENT '密码'")
    private String password;

    @Column(name = "phone",columnDefinition = "varchar(32) COMMENT '电话号码'")
    private String phone;

    @Column(name = "email",columnDefinition = "varchar(32) COMMENT '邮箱'")
    private String email;

    @Column(name = "sex",columnDefinition = "int(1) COMMENT '性别'")
    private Integer sex;

    @Column(name = "birthday",columnDefinition = "date COMMENT '生日'")
    private LocalDate birthday;

    @Column(name = "head_image",columnDefinition = "varchar(255) COMMENT '头像地址'")
    private String headImage;

    @Column(name = "status",columnDefinition = "varchar(32) COMMENT '状态（0. 正常　1.锁定　2.注销）'")
    private Integer status;

    @Column(name = "last_login_ip",columnDefinition = "varchar(32) COMMENT '最近一次的登录地址'")
    private String lastLoginIp;
}
