package cn.qyd.blogroom.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-26 下午12:21
 **/
@Data
@ApiModel("粉丝Vo")
public class FanVo {
    @ApiModelProperty("关注者id")
    private Long user1Id;

    @ApiModelProperty("关注者姓名")
    private String user1Name;

    @ApiModelProperty("关注者头像")
    private String user1Image;
}
