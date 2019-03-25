package cn.qyd.blogroom.blog.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-25 下午6:56
 **/
@Data
@ApiModel("修改博客等级信息")
public class RankDto {
    private Long id;

    private String name;

    private Integer integral;
}
