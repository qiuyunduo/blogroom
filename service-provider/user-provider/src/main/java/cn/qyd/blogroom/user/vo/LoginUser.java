package cn.qyd.blogroom.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-6 上午10:44
 **/
@Data
@ApiModel("登录用户")
public class LoginUser {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("头像地址")
    private String headImage;

    @ApiModelProperty("登录token")
    private String token;
}
