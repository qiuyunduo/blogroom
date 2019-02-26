package cn.qyd.blogroom.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-26 上午10:30
 **/
@Data
@ApiModel("用户修改基础信息Dto")
public class UserUpdateInfoDto {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("地区")
    private String area;

    @ApiModelProperty("职业")
    private String occupation;

    @ApiModelProperty("自我描述")
    private String description;
}
