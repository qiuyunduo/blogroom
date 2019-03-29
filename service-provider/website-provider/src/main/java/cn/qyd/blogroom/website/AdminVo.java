package cn.qyd.blogroom.website;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author qyd
 * @Date 19-3-29 上午9:59
 **/
@Data
@ApiModel("管理员Vo")
public class AdminVo {
    private Long id;
    private String name;
    private String password;
    private String email;

    private String headImage;

    private Integer status;

    private Integer loginNumber;

    private String lastLoginIp;
    private LocalDateTime lastLoginTime;
}
