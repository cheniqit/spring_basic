package com.mk.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mk.framework.enums.ValidEnum;

/**
 * Created by chenqi on 16/5/9.
 */
public class CommonResponse {
    private String success = ValidEnum.INVALID.getCode();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errcode;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String errmsg;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
