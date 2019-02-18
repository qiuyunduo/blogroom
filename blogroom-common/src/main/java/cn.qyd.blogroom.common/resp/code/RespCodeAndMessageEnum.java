package cn.qyd.blogroom.common.resp.code;

/**
 * 返回状态值枚举
 */
public enum RespCodeAndMessageEnum  implements CodeAndMessage{
    /**
     * 成功
     */
    SUCCEED(0,"成功"),
    PREFIX_NOT_EXIST(100, "手机号前缀不存在"),
    MOBILE_LENGTH_ERROR(101, "手机号长度不正确"),
    USER_NOT_LOGIN_ERROR(401, "用户没有登录"),
    INVALID_ACCESS_TOKEN(401, "无效的Token"),
    USER_AUTH_ERROR(402, "用户权限不够"),
    UNAUTHORIZED_CLIENT(405, "未授权的客户端"),
    ASSERT_CHECK_ERROR(997,"断言校验失败"),
    PARAMS_ERROR(998,"参数校验失败"),
    NULL_ERROR(998,"系统空指针错误"),
    SYSTEM_ERROR(999,"系统内部错误"),

    LANGUAGE_TYPE_REPEAT(1000,"语言code重复了"),
    LANGUAGE_TYPE_NOT_EXISTS(1001, "目前只支持中文，英文和繁体"),


    /**
     * 文件
     */
    FILE_INFO_NOT_EXITS(4001,"文件不存在"),
    GET_FILE_ERROR(4002,"查看文件异常"),

    ;

    //code
    private Integer code;

    //描述
    private String message;

    RespCodeAndMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;

    }

    @Override
    public Integer getCode() {
        return code;
    }



    @Override
    public String getMessage() {
        return message;
    }




}
