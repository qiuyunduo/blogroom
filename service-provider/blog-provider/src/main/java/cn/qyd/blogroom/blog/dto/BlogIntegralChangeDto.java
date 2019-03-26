package cn.qyd.blogroom.blog.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-2-27 上午10:11
 **/
@Data
@ApiModel("更新博客积分信息")
public class BlogIntegralChangeDto {
    private Long id;

    private Integer integral;

    private Long rankId;
}
