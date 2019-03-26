package cn.qyd.blogroom.article.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-26 下午6:10
 **/
@Data
@ApiModel("文章相关的信息计数")
public class CountInfoVo {
    /**
     * 文章数量
     */
    private Long articleNumber;
    /**
     * 点赞数
     */
    private Long likeNumber;
    /**
     * 评论数
     */
    private Long commentNumber;
}
