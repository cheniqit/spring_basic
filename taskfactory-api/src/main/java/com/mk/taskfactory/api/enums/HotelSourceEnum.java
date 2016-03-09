package com.mk.taskfactory.api.enums;

public enum HotelSourceEnum {

    LEZHU("LEZHU",1,"乐住"),
    OTA("OTA",2,"OTA");

    private String id;
    private Integer code;
    private String desc;

    HotelSourceEnum(String id, Integer code, String desc) {
        this.id = id;
        this.code = code;
        this.desc=desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static HotelSourceEnum getById(String id) {
        for (HotelSourceEnum valueTypeEnum : HotelSourceEnum.values()) {
            if (valueTypeEnum.getId().equals(id)) {
                return valueTypeEnum;
            }
        }
        return LEZHU;
    }

    public static HotelSourceEnum getByCode(String code) {
        for (HotelSourceEnum valueTypeEnum : HotelSourceEnum.values()) {
            if (valueTypeEnum.getCode().equals(code)) {
                return valueTypeEnum;
            }
        }
        return LEZHU;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
