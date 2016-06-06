package com.mk.execution.index.hotel;

/**
 * Created by 振涛 on 2016/2/18.
 */
public class PushInfo {

    /**
     * 酒店ID
     */
    public String body;

    public String type;

    public PushInfo(String body, String type) {
        this.body = body;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
