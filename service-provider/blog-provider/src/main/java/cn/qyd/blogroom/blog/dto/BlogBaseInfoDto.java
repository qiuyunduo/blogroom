package cn.qyd.blogroom.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-27 上午9:52
 **/
@Data
@ApiModel("更新博客信息")
public class BlogBaseInfoDto {
    @ApiModelProperty("博客id")
    private Long id;

    @ApiModelProperty("博客总文章数+1或-1")
    private Boolean articleNumberStatus;

    @ApiModelProperty("用户总粉丝数+1或-1")
    private Boolean fansNumberStatus;

    @ApiModelProperty("博客总赞数+1或-1")
    private Boolean likeNumberStatus;

    @ApiModelProperty("博客总评论数+1或-1")
    private Boolean commentNumberStatus;

    @ApiModelProperty("博客访问数+1")
    private Boolean visit;

}
