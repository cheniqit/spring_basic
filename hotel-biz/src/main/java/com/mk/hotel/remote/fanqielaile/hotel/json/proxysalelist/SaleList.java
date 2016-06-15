package com.mk.hotel.remote.fanqielaile.hotel.json.proxysalelist;

import java.util.List;

/**
 * Created by huangjie on 16/6/7.
 */
public class SaleList {
    private Integer status;
    private String message;
    private String percentage;
    private List<ProxyInns> proxyInns;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public List<ProxyInns> getProxyInns() {
        return proxyInns;
    }

    public void setProxyInns(List<ProxyInns> proxyInns) {
        this.proxyInns = proxyInns;
    }
}
