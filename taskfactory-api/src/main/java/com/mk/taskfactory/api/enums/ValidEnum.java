package com.mk.taskfactory.api.enums;

public enum ValidEnum {

    VALID("T","有效"),
    DISVALID("F","无效");

    private String id;
    private String desc;

    ValidEnum(String id, String desc) {
        this.id = id;
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
            if (valueTypeEnum.getId() == id) {
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
}
