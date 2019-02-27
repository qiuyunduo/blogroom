package cn.qyd.blogroom.article.enums;

/**
 * @Author qyd
 * @Date 19-2-27 上午9:24
 **/
public enum  ArticleStatusEnum {
    DRAFT(0,"草稿"),
    SUBMIT_REQUEST_PUBLISH(1,"提交发布审核"),
    SUCCESS_PUBLISH(2,"审核成功，发布"),
    FAIL_PUBLISH(3,"审核失败"),
    ;

    private Integer code;

    private String desc;

    ArticleStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByCode (Integer code) {
        ArticleStatusEnum[] values = ArticleStatusEnum.values();

        for(ArticleStatusEnum value : values) {
            if(value.getCode() == code) {
                return value.getDesc();
            }
        }

        return "未知";
    }
}
