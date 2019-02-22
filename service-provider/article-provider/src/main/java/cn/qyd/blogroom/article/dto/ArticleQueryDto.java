package cn.qyd.blogroom.article.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author qyd
 * @Date 19-2-22 下午3:29
 **/
@Data
public class ArticleQueryDto {
    private String title;

    @NotNull
    private Integer page;

    private Integer limit = 5;
}
