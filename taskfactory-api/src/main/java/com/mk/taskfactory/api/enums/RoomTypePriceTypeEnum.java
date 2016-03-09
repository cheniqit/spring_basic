package com.mk.taskfactory.api.enums;

public enum RoomTypePriceTypeEnum {

    OTHER(0, "OTHER"),
    DYNAMIC(1, "DYNAMIC"),
    OTA(2, "OTA"),
    LeZhu(3,"LeZhu"),
    Min(4,"Min"),
    WorkIn(5,"WorkIn");

    private Integer code;
    private String desc;

    RoomTypePriceTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc=desc;
    }


    public static RoomTypePriceTypeEnum getByCode(Integer code) {
        for (RoomTypePriceTypeEnum valueTypeEnum : RoomTypePriceTypeEnum.values()) {
            if (valueTypeEnum.getCode()==code) {
                return valueTypeEnum;
            }
        }
        return OTHER;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
