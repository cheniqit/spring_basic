package com.mk.taskfactory.model;

public enum ValueTypeEnum {

    //1-生成 2-入库3-激活4-使用5-注销
    DEFAULT(0,"默认错误"),
    TYPE_INIT(1,"减至"),           // 直接减到
    TYPE_OUT(2,"补贴"),            // 当前值减去
    TYPE_ACTIVE(3,"折扣");        // 基于当前值打折


    private int id;
    private String desc;

    ValueTypeEnum(int id, String desc) {
        this.id = id;
        this.desc=desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static ValueTypeEnum getById(int id) {
        for (ValueTypeEnum valueTypeEnum : ValueTypeEnum.values()) {
            if (valueTypeEnum.getId() == id) {
                return valueTypeEnum;
            }
        }
        return DEFAULT;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
