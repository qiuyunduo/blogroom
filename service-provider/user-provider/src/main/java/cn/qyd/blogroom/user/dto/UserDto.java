package cn.qyd.blogroom.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-26 上午10:01
 **/
@Data
@ApiModel("用户注册")
public class UserDto {
    @ApiModelProperty("用户账号")
    private String account;

    @ApiModelProperty("用户名称")
    private String nickName;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    private String email;

}
