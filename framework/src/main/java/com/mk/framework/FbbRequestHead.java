package com.mk.framework;

import java.util.Calendar;

/**
 * Created by chenqi on 16/5/10.
 */
public class FbbRequestHead {
    private String channelId;
    private String token;
    private Long timeStamp;

    public FbbRequestHead(String channelId, String token) {
        this.channelId = channelId;
        this.token = token;
        this.timeStamp = Calendar.getInstance().getTimeInMillis();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
