package cn.qyd.blogroom.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author qyd
 * @Date 19-2-26 下午12:33
 **/
@Data
@ApiModel("查询用户的关注或粉丝Dto")
public class AttentionQueryDto {
    @ApiModelProperty("获取用户关注设置值")
    private Long user1Id;

    @ApiModelProperty("获取用户粉丝设置值")
    private Long user2Id;

    @NotNull
    private Integer page;

    private Integer limit = 5;
}
