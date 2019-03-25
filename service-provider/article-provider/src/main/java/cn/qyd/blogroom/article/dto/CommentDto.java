package cn.qyd.blogroom.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:16
 */
@Data
@ApiModel("添加评论dto")
public class CommentDto {
    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("评论者id")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String content;
}
