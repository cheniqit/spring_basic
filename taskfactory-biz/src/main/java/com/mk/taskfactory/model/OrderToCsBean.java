package com.mk.taskfactory.model;

public class OrderToCsBean {
    private Long orderId;

    private String liveUserPhone;

    private String orderUserPhone;

    private String isRecommandOrder;

    private String createTime;

    public String getIsRecommandOrder() {
        return isRecommandOrder;
    }

    public void setIsRecommandOrder(String isRecommandOrder) {
        this.isRecommandOrder = isRecommandOrder;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}