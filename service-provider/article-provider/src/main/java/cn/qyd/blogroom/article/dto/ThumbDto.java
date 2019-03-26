package cn.qyd.blogroom.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:16
 */
@Data
@ApiModel("添加点赞dto")
public class ThumbDto {
    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("作者id")
    private Long authorId;

    @ApiModelProperty("点赞者id")
    private Long userId;
}
