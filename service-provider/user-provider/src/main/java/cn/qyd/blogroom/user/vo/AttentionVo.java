package cn.qyd.blogroom.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-26 下午12:16
 **/
@Data
@ApiModel("关注Vo")
public class AttentionVo {
    @ApiModelProperty("被关注者id")
    private Long user2Id;

    @ApiModelProperty("被关注者昵称")
    private String user2Name;

    @ApiModelProperty("被关注者头像")
    private String user2Image;
}
