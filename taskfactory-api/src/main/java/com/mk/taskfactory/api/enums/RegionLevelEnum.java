package com.mk.taskfactory.api.enums;

/**
 * Created by chenqi on 16/4/22.
 */
public enum RegionLevelEnum {
    province(1,"省"),
    city(2,"市"),
    district(3,"县区"),
    town(4,"城镇");


    private Integer code;
    private String desc;

    RegionLevelEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
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
