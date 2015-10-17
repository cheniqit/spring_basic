package com.mk.taskfactory.biz.domain;

/**
 * Created by Thinkpad on 2015/10/17.
 */
public enum ValidEnum {
    VALID("1","有效"),INVLIDA("0","无效");
    private String code;
    private String text;

    ValidEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
