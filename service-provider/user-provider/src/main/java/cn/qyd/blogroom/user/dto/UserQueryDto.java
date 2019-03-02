package cn.qyd.blogroom.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author qyd
 * @Date 19-2-26 上午10:14
 **/
@Data
@ApiModel("查询用户条件")
public class UserQueryDto {
    private String name;

    private Integer status;

    @NotNull
    private Integer page;

    private Integer limit = 5;
}
