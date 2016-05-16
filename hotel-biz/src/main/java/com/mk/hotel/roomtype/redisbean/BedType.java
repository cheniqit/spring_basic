package com.mk.hotel.roomtype.redisbean;

/**
 * Created by huangjie on 16/5/16.
 */
public class BedType {
    private String name;
    private Integer type;
    private String length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
