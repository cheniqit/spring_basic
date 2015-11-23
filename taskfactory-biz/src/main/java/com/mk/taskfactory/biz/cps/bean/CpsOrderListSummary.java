package com.mk.taskfactory.biz.cps.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Thinkpad on 2015/11/20.
 */
public class CpsOrderListSummary {
    /**订单总数**/
    private Integer sumOrder;
    /**订单总价格**/
    private BigDecimal sumOrderPrice;
    private Date updateTime;

    public Integer getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(Integer sumOrder) {
        this.sumOrder = sumOrder;
    }

    public BigDecimal getSumOrderPrice() {
        return sumOrderPrice;
    }

    public void setSumOrderPrice(BigDecimal sumOrderPrice) {
        this.sumOrderPrice = sumOrderPrice;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
