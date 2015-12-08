package com.mk.taskfactory.api.enums;

public enum HMSStatusEnum {
    DOING("10","审批中"),
    PASS("20","审批通过"),
    FAIL("30","审批未过"),
    OTHER("-1","其他");

    private String code;
    private String desc;

    HMSStatusEnum(String code, String desc) {
        this.code = code;
        this.desc=desc;
    }

    public static HMSStatusEnum getByCode(String code) {
        for (HMSStatusEnum valueTypeEnum : HMSStatusEnum.values()) {
            if (valueTypeEnum.getCode().equals(code)) {
                return valueTypeEnum;
            }
        }
        return OTHER;
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
