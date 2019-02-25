package cn.qyd.blogroom.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:48
 */
@Data
@ApiModel("更新文章状态")
public class ArticleStatusDto {
    @ApiModelProperty("文章id")
    private Long id;
    @ApiModelProperty("文章点赞数是否+1")
    private Boolean addThumbs;
    @ApiModelProperty("文章点赞数是否-1")
    private Boolean removeThumbs;
    @ApiModelProperty("文章评论数是否+1")
    private Boolean addComments;
    @ApiModelProperty("文章评论数是否-1")
    private Boolean removeComments;
    @ApiModelProperty("文章状态(0.保存为草稿1.提交审核2.审核成功发布3.审核失败)")
    private Integer status;
}
