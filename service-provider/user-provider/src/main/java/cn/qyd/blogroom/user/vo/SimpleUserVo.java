package cn.qyd.blogroom.user.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author qyd
 * @Date 19-3-13 下午3:45
 **/
@Data
@ApiModel("用户基本头像信息")
public class SimpleUserVo {
    private Long id;

    private String name;

    private String headImage;
}
