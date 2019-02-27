package cn.qyd.blogroom.blog.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-27 上午10:57
 **/
@Data
@ApiModel("添加博客信息")
public class BlogDto {
    private String blogName;

    private Long userId;
}
