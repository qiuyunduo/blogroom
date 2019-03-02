package cn.qyd.blogroom.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-26 下午12:23
 **/
@Data
@ApiModel("添加关注Dto")
public class AttentionDto {
    @ApiModelProperty("关注者id")
    private Long user1Id;

    @ApiModelProperty("被关注者id")
    private Long user2Id;
}
