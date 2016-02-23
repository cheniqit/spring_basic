package com.mk.taskfactory.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderToCsBean {
    private Long orderId;

    private String liveUserPhone;

    private String orderUserPhone;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getLiveUserPhone() {
        return liveUserPhone;
    }

    public void setLiveUserPhone(String liveUserPhone) {
        this.liveUserPhone = liveUserPhone;
    }

    public String getOrderUserPhone() {
        return orderUserPhone;
    }

    public void setOrderUserPhone(String orderUserPhone) {
        this.orderUserPhone = orderUserPhone;
    }
}