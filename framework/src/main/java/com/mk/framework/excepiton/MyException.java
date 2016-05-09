package com.mk.framework.excepiton;

import java.io.Serializable;

public class MyException extends RuntimeException implements Serializable {

    private String errorCode;
    private String errorKey;

    public MyException(String errorCode, String errorKey, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }

    public MyException(String errorCode, String errorKey) {
        super("errorcode:" + errorCode);
        this.errorCode = errorCode;
        this.errorKey = errorKey;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public final String getErrorKey() {
        return errorKey;
    }

    public final void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }
}
