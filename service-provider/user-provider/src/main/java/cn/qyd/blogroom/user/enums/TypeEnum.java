package cn.qyd.blogroom.user.enums;

public enum TypeEnum {
    REGISTER(1,"欢迎您注册博客屋账号，若非本人操作请忽略，您的验证码为： "),
    RESET_PASSWORD(2,"您正在进行重置密码操作，若非本人操作请忽略，您的验证码为： "),
    ;

    private Integer code;

    private String desc;

    TypeEnum(Integer code, String desc) {
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
        TypeEnum[] values = TypeEnum.values();

        for(TypeEnum value : values) {
            if(value.getCode() == code) {
                return value.getDesc();
            }
        }

        return "未知";
    }
}
