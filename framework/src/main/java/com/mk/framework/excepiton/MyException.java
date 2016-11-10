package com.mk.framework.excepiton;

import java.io.Serializable;

public class MyException extends RuntimeException implements Serializable {

    private Integer code;

    public MyException(String msg){
        super(msg);
    }
    public MyException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }



}
