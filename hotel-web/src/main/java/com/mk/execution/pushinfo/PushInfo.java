package com.mk.execution.pushinfo;

import com.mk.hotel.log.enums.LogPushTypeEnum;

/**
 * Created by 振涛 on 2016/2/18.
 */
public class PushInfo {

    /**
     * 酒店ID
     */
    public String body;

    public LogPushTypeEnum type;

    public PushInfo(String body, LogPushTypeEnum type) {
        this.body = body;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LogPushTypeEnum getType() {
        return type;
    }

    public void setType(LogPushTypeEnum type) {
        this.type = type;
    }
}
