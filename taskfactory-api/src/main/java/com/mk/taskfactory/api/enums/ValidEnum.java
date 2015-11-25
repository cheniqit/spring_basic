package com.mk.taskfactory.api.enums;

public enum ValidEnum {

    VALID("T","1","有效"),
    INVALID("F","0","无效");

    private String id;
    private String code;
    private String desc;

    ValidEnum(String id, String code, String desc) {
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

    public static ValidEnum getById(String id) {
        for (ValidEnum valueTypeEnum : ValidEnum.values()) {
            if (valueTypeEnum.getId().equals(id)) {
                return valueTypeEnum;
            }
        }
        return VALID;
    }

    public static ValidEnum getByCode(String code) {
        for (ValidEnum valueTypeEnum : ValidEnum.values()) {
            if (valueTypeEnum.getCode().equals(code)) {
                return valueTypeEnum;
            }
        }
        return VALID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
