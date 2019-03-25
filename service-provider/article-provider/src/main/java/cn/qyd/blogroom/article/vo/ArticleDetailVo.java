package cn.qyd.blogroom.article.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-3-4 下午6:33
 **/
@Data
@ApiModel("博客文章")
public class ArticleDetailVo {
    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("文章作者id")
    private Long userId;

    @ApiModelProperty("文章类别")
    private Long classId;

    @ApiModelProperty("文章作者")
    private String author;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("文章content")
    private String content;

    @ApiModelProperty("文章获得评论数")
    private Integer comments;

    @ApiModelProperty("文章获赞次数")
    private Integer thumbs;

    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("文章标题图片")
    private String headPhoto;

    @ApiModelProperty("文章状态（0.保存为草稿　1.提交审核）")
    private Integer status;

    @ApiModelProperty("文章时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime publishTime;
}
