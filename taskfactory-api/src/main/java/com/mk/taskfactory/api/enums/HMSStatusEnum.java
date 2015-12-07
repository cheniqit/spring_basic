package com.mk.taskfactory.api.enums;

public enum HMSStatusEnum {
    Init("-1","初始化"),
    UNInitial("0","未初次审核"),
    Initial("1","正在初次审核"),
    ManagerEdite("2","店长编辑中（未经过上限）"),
    Submit("3","提交审核中"),
    ManagerEditing("4","店长正在编辑中（已进行过一次上线）"),
    Editing("5","审核中（已进行过一次上线）");

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
        return Init;
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
