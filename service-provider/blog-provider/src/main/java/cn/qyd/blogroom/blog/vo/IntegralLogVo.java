package cn.qyd.blogroom.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-3-27 下午2:51
 **/
@Data
@ApiModel("积分记录Vo")
public class IntegralLogVo {
    private Long id;

    private Long blogId;

    private Long type;

    private Long articleId;

    private Integer changeNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime addTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;
}
