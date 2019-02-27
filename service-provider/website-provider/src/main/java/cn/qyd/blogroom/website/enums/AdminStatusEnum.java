package cn.qyd.blogroom.website.enums;

public enum AdminStatusEnum {
    ACTIVE(0,"正常使用"),
    LOCK(1,"锁定"),
    CANCEL(2,"注销"),
    ;

    private Integer code;

    private String desc;

    AdminStatusEnum(Integer code, String desc) {
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
        AdminStatusEnum[] values = AdminStatusEnum.values();

        for(AdminStatusEnum value : values) {
            if(value.getCode() == code) {
                return value.getDesc();
            }
        }

        return "未知";
    }
}
