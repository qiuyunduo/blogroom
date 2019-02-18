package cn.qyd.blogroom.common.resp.code;

public enum CommonRespEnum implements CodeAndMessage {


    PHONE_LENGTH_ERROR(300,"手机号长度错误"),
    EMAIL_TEMPLATE_EXIST(301,"邮件模板不存在"),
    THE_NUMBER_OF_EMAIL_SENT_HAS_BEEN_EXCEEDED(302,"发送邮件次数已超限"),
    VERIFI_CODE_NOT_EXIST(303,"验证码不存在"),
    VERIFI_CODE_PAST_DUE(304,"验证码已经过期"),
    VERIFI_CODE_ERROR(305,"验证码错误"),
    SEND_CODE_FAIL(306,"发送短信验证码失败"),
    SMS_TEMPLATE_NOT_EXIST(307,"短信模板不存在"),
    THE_NUMBER_OF_SMS_SENT_HAS_BEEN_EXCEEDED(308,"发送短信次数已超限"),
    NO_VERIF_CODE_NOT_FOUND(309,"未找到验证码"),
    USER_WALLET_ADDRESS_NOT_EXIST(310,"用户钱包地址不存在"),
    TRANSACTION_AMOUNT_MUST_GT_0(311,"激励Token必须大于0"),
    FAILED_TO_CREATE_WALLET_ADDRESS(312,"创建钱包地址失败"),
    UPDATE_USER_TOKEN_FAIL(313,"更新用户token失败"),
    USER_BALANCE_INSUFF_AND_PRO_OF_DEDUCT_FAIL(314,"用户余额不够，处理扣除token失败"),
    UPDATE_USER_ASSET_FAIL_REWARD_AND_PUN_CONFIG_STATIS_CYCLE_ERROR(315,"更新用户资产失败，奖惩配置统计周期错误"),
    UPDATE_USER_ASSET_FAIL_EWARD_AND_PUN_CONFIG_STATUS_ERROR(316,"更新用户资产结束，奖惩配置状态错误"),
    ;

    private Integer code;

    private String message;

    CommonRespEnum(Integer code, String message){
        this.code=code;
        this.message=message;
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
