package com.mk.hotel.remote.fanqielaile.hotel.json.proxysalelist;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huangjie on 16/6/7.
 */
public class PricePatterns {
    private String pattern;
    private Integer accountId;

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
