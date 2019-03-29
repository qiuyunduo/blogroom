package cn.qyd.blogroom.website.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-27 下午2:11
 **/
@Data
@ApiModel("管理员dto")
public class AdminDto {
    @ApiModelProperty("管理员名称")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("注册邮箱")
    private String email;

    @ApiModelProperty("管理员身份")
    private Integer status;
}
