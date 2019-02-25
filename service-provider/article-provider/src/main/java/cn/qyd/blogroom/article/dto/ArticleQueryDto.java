package cn.qyd.blogroom.article.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author qyd
 * @Date 19-2-22 下午3:29
 **/
@Data
@ApiModel("文章查询条件Dto")
public class ArticleQueryDto {
    private String title;

    private Long classId;

    private Long userId;

    /**
     * 是否按照文章修改时间排序
     */
    private Boolean orderByUpdateTime;

    private Integer status;

    @NotNull
    private Integer page;

    private Integer limit = 5;
}
