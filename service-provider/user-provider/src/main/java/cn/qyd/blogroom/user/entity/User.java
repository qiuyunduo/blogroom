package cn.qyd.blogroom.user.entity;

import cn.qyd.blogroom.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author qyd
 * @Date 19-1-2 下午2:09
 **/
@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class User extends BaseEntity {
    @Column(name = "name",columnDefinition = "varchar(32) COMMENT '用户名'")
    private String name;

    @Column(name = "password",columnDefinition = "varchar(64) COMMENT '用户密码'")
    private String password;

    @Column(name = "phone",columnDefinition = "varchar(32) COMMENT '电话号码'")
    private String phone;

    @Column(name = "email",columnDefinition = "varchar(32) COMMENT '邮箱'")
    private String email;

    @Column(name = "sex",columnDefinition = "int(1) COMMENT '性别'")
    private Integer sex;

    @Column(name = "birthday",columnDefinition = "date COMMENT '生日'")
    private Date birthday;

    @Column(name = "head_image",columnDefinition = "varchar(255) COMMENT '头像地址'")
    private String headImage;

    @Column(name = "area",columnDefinition = "varchar(32) COMMENT '地区'")
    private String area;

    @Column(name = "occupation",columnDefinition = "varchar(32) COMMENT '职业'")
    private String occupation;

    @Column(name = "description",columnDefinition = "varchar(32) COMMENT '自我描述'")
    private String description;

    @Column(name = "status",columnDefinition = "varchar(32) COMMENT '状态（0. 正常　1.锁定　2.注销）'")
    private Integer status;

    @Column(name = "last_login_ip",columnDefinition = "varchar(32) COMMENT '最近一次的登录地址'")
    private String lastLoginIp;
}
