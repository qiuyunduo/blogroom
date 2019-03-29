package cn.qyd.blogroom.website.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-28 下午4:35
 **/
@Data
@ApiModel("更新管理员身份")
public class AdminUpdateDto {
    private Long id;

    private Integer status;
}
