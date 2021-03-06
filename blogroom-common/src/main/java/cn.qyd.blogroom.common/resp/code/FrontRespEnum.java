package cn.qyd.blogroom.common.resp.code;

public enum FrontRespEnum implements CodeAndMessage {

    /**
     * 注册用户模块
     */
    REGISTER_TYPE_ERROR(201,"注册类型错误"),
    ACCOUNT_EXIST(202,"账号已经存在"),
    EMAIL_EXIST(203,"邮箱已经注册"),
    MOBILE_NOT_EXIST(204,"手机号不存在"),
    EMAIL_NOT_EXIST(205,"邮箱未注册"),
    VERIFICATION_CODE_ERROR(206,"验证码错误"),

    USER_EXIT(207,"用户名已经存在"),
    LOGIN_TYPE_ERROR(208,"登录类型错误"),
    GET_WECHAT_SMALL_LOGIN_ERROR(209,"获取微信小程序登录信息异常"),
    WX_USER_CHECK_FAIL(210,"微信用户信息校验失败"),
    OTHER_LOGIN(211,"您已经在其他地方进行登录，请重新登录"),
    LOGIN_INFO_EXIST(212,"您还未登录,请进行登录"),
    LOGIN_ACCOUNT_EXIST(213,"请重新登录"),
    LOGIN_PWD_NOT_NULL(214,"登录密码不能为空"),
    LOGIN_PWD_ERROR(215,"账号或密码错误"),
    PLEASE_LOGIN_FIRST(216,"请先登录"),
    UPDATE_PARAM_TYPE_ERROR(217,"修改类型错误"),
    USER_ACCOUNT_EXIST(218,"账号已经存在"),
    THE_ID_NUMBER_ALREADY_EXIST(219,"身份证号码已经存在"),
    COUNTRY_CONFIG_INFO_NOT_EXIST(220,"国籍配置信息不存在"),
    PLEASE_DO_NOT_REPEAT_THE_AUTH(221,"请不要重复实名认证"),
    USER_ACCOUNT_LOCK(222,"账号已被锁定，请联系平台管理员"),
    PLEASE_AUTH_THE_REAL_NAME_FIRST(223,"请先进行实名认证"),
    THE_ACCOUNT_IS_LOCK_AND_CANNOT_FAIL_MORE_THAN_THREE_TIMES(224,"该账户被锁定，失败次数不能超过3次"),
    THE_OLD_PASSWORD_ERROR(225,"原密码输入错误"),
    THE_MAIL_HAS_BEEN_USED(226,"邮箱已经被使用"),

    THE_USER_TOKEN_EXPIRE(227,"用户token过期"),
    THE_USER_NOT_EXIST(228,"用户不存在"),

    /**
     * 博客
     */
    BLOG_NOT_ACTIVE(300, "该用户尚未开通博客"),
    BLOG_NOT_EXIST(301, "博客不存在"),
    TRAVELS_HAS_FINISHED(302, "游记已完成，不能更新"),
    TRAVELS_DRAFT_EXIST(303, "完成草稿前，不能新增游记"),
    TOKEN_NOT_ENOUGH(305, "没子弹了"),
    /**
     * 评论
     */
    COMMENT_NOT_EXIST(330, "评论不存在"),
    COMMENT_CONTAIN_SENSITIVE_WORD(331,"您的评论包含敏感词汇"),

    /**
     * 文章模块
     */
    ARTICLE_NOT_EXIST(440, "找不到对应文章信息"),
    COOPERATIVE_NEWS_CONTAIN_SENSITIVE_WORD(441,"需求信息包含敏感词汇"),
    COOPERATIVE_NEWS_SUMBIT_OVER(442,"您已达今日提交上限，请明日在试试，或直接电话联系我们"),

    /**
     * 管理员
     */
    ADMIN_NOT_EXIST(450, "管理员不存在"),
    ADMIN_LOGIN_FAIL_PASSWORD_ERROR(451, "密码错误"),
    ADMIN_LOCK(452,"此管理员账锁定"),
    ADMIN_HAVING_DELETE(452,"此管理员账号已注销"),

    /**
     * 其他
     */
    RANK_ERROR(460,"获取博客等级信息出现错误"),
    INTEGRAL_CONFIG_ERROR(461,"积分设置获取出错"),
    ;

    private Integer code;

    private String message;

    FrontRespEnum(Integer code, String message){
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
