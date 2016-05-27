package com.mk.hotel.order.controller.json;

/**
 * Created by chenqi on 16/5/27.
 */
public class OrderStatusPush {
    private String orderid;
    private String orderstatus;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }
}
