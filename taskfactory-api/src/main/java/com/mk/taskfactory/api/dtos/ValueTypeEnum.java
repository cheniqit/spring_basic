package com.mk.taskfactory.api.dtos;

public enum ValueTypeEnum {

    //
    DEFAULT(0,"默认错误"),
    TYPE_TO(1,"减至"),
    TYPE_ADD(2,"补贴"),//在房类价格上面减少多少价格
    TYPE_OFF(3,"折扣");//

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
