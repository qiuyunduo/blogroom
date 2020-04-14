package cn.qyd.blogroom.article.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-13 下午12:05
 **/
@Data
@ApiModel("文章类别")
public class ArticleClassVo {
    @ApiModelProperty("类别id")
    private Long id;

    @ApiModelProperty("类别名称")
    private String name;

    @ApiModelProperty("类别的二级id")
    private Long pid;
}
