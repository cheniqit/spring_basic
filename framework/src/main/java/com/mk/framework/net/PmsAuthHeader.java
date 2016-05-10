package com.mk.framework.net;

import com.mk.framework.UrlUtils;

/**
 * Created by kirinli on 16/5/10.
 */
public class PmsAuthHeader {
    private String channelId;
    private String token;
    private Long timestamp;

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

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public PmsAuthHeader() {
        this.channelId = UrlUtils.getUrl("pms.channelid");
        this.token = UrlUtils.getUrl("pms.token");
        this.timestamp = System.currentTimeMillis();
    }

}
