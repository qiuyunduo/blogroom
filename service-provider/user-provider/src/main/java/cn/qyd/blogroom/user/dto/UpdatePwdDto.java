package cn.qyd.blogroom.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-26 上午10:33
 **/
@Data
@ApiModel("用户修改密码Dto")
public class UpdatePwdDto {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("原密码")
    private String oldPassword;

    @ApiModelProperty("新密码")
    private String newPassword;
}
