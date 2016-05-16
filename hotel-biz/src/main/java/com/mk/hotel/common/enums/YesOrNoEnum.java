package com.mk.hotel.common.enums;

/**
 * Created by Thinkpad on 2015/11/16.
 */
public enum YesOrNoEnum {
    YES(1L,"是"),
    NO(0L,"否");
    private final Long code;
    private final String text;

    private YesOrNoEnum(Long code, String text){
        this.code=code;
        this.text=text;
    }

    public Long getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
