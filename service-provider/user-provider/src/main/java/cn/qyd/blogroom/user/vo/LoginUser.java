package cn.qyd.blogroom.user.vo;

import cn.qyd.blogroom.user.entity.User;
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
    @ApiModelProperty("用户")
    private User user;

    @ApiModelProperty("登录token")
    private String token;
}
