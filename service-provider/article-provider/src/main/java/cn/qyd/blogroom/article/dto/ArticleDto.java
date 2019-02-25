package cn.qyd.blogroom.article.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:18
 */
@Data
@ApiModel("添加/更新文章Dto")
public class ArticleDto {
    @ApiModelProperty("文章id")
    private Long id;

    @ApiModelProperty("文章类别")
    private Long classId;

    @ApiModelProperty("文章作者id")
    private Long userId;

    @ApiModelProperty("文章作者")
    private String author;

    @ApiModelProperty("文章简介")
    private String describe;

    @ApiModelProperty("文章正文")
    private String content;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("文章标题图片")
    private String headPhoto;

    @ApiModelProperty("文章状态（0.保存为草稿　1.提交审核）")
    private Integer status;
}
