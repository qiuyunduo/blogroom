package cn.qyd.blogroom.blog.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-27 上午10:13
 **/
@Data
@ApiModel("更新博客状态")
public class BlogStatusDto {
    private Long id;

    private Integer status;
}
