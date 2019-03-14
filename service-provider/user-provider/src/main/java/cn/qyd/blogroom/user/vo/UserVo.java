package cn.qyd.blogroom.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author qyd
 * @Date 19-2-26 上午9:50
 **/
@Data
@ApiModel("用户信息")
public class UserVo {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("生日")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    @ApiModelProperty("头像地址")
    private String headImage;

    @ApiModelProperty("地区")
    private String area;

    @ApiModelProperty("职业")
    private String occupation;

    @ApiModelProperty("自我描述")
    private String description;

    @ApiModelProperty("最近一次的登录地址")
    private String lastLoginIp;

    @ApiModelProperty("用户状态")
    private Integer status;

    @ApiModelProperty("添加时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime addTime;
}
