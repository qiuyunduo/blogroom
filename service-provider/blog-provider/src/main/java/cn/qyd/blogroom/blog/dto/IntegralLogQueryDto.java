package cn.qyd.blogroom.blog.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-27 下午2:58
 **/
@Data
@ApiModel("查询积分日志")
public class IntegralLogQueryDto {
    private Long blogId;

    private Long type;

    private Long articleId;

    private String startTime;

    private String endTime;

    private Integer page = 1;

    private Integer limit = 10;
}
