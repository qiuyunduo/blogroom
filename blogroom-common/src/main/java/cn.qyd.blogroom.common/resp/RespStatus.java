package cn.qyd.blogroom.common.resp;

import io.swagger.annotations.ApiModelProperty;

/**
 * 返回状态值
 *
 */
public class RespStatus {

    @ApiModelProperty(notes = "调用结果编码")
    private Integer code;

    @ApiModelProperty(notes = "调用结果信息")
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RespStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespStatus() {
    }
}
