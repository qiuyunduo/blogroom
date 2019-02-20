package cn.qyd.blogroom.user.enums;

public enum SexEnum {
    UNKNOWN(0,"未知"),
    MEN(1,"男"),
    WOMAN(2,"女"),
    ;

    private Integer code;

    private String desc;

    SexEnum(Integer code, String desc) {
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
        SexEnum[] values = SexEnum.values();

        for(SexEnum value : values) {
            if(value.getCode() == code) {
                return value.getDesc();
            }
        }

        return "未知";
    }
}
