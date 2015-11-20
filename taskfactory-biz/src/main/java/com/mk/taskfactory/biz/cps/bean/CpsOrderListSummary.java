package com.mk.taskfactory.biz.cps.bean;

import java.math.BigDecimal;

/**
 * Created by Thinkpad on 2015/11/20.
 */
public class CpsOrderListSummary {
    /**订单总数**/
    private Integer sumOrder;
    /**订单总价格**/
    private BigDecimal sumOrderPrice;

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
}
