package cn.qyd.blogroom.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-27 下午6:13
 **/
@Data
@ApiModel("修改用户状态Dto")
public class UserUpdateStatusDto {
    private Long userId;

    private Integer status;
}
