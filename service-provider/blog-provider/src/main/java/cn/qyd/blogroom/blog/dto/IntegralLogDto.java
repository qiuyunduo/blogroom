package cn.qyd.blogroom.blog.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-27 下午2:18
 **/
@Data
@ApiModel("博客添加积分")
public class IntegralLogDto {
    private Long userId;
    private Long type;
    private Long articleId;
}
