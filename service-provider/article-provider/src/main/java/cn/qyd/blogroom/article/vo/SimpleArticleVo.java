package cn.qyd.blogroom.article.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-13 下午3:49
 **/
@Data
@ApiModel("时尚博文Vo")
public class SimpleArticleVo {
    private Long id;

    private String title;
}
