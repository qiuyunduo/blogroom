package cn.qyd.blogroom.user.enums;

public enum UserStatusEnum {
    ACTIVE(0,"正常使用"),
    LOCK(1,"锁定"),
    CANCEL(2,"注销"),
    ;

    private Integer code;

    private String desc;

    UserStatusEnum(Integer code, String desc) {
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
        UserStatusEnum[] values = UserStatusEnum.values();

        for(UserStatusEnum value : values) {
            if(value.getCode() == code) {
                return value.getDesc();
            }
        }

        return "未知";
    }
}
