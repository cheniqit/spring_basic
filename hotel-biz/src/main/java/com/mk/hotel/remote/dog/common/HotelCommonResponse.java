package com.mk.hotel.remote.dog.common;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chenqi on 16/5/9.
 */
public class HotelCommonResponse {
    private String success;
    @SerializedName("errorcode")
    private String errorCode;
    @SerializedName("errormessage")
    private String errorMessage;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
