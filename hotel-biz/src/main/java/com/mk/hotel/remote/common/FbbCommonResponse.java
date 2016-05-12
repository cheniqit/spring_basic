package com.mk.hotel.remote.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by chenqi on 16/5/9.
 */
public class FbbCommonResponse<T> {
    private String success;
    private String errorCode;
    private String errorMessage;
    private String result;
    @JSONField(name ="data")
    private T date;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
