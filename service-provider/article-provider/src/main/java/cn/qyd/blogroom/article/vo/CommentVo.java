package cn.qyd.blogroom.article.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:23
 */
@Data
@ApiModel("评论Vo")
public class CommentVo {
    @ApiModelProperty("评论者id")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论楼层")
    private Integer floor;

    @ApiModelProperty("评论时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime addTime;
}
