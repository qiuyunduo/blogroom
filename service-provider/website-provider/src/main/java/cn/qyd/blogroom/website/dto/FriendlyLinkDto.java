package cn.qyd.blogroom.website.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-26 上午9:57
 **/
@Data
@ApiModel("添加链接dto")
public class FriendlyLinkDto {
    private Long id;
    private String url;
    private String content;
}
