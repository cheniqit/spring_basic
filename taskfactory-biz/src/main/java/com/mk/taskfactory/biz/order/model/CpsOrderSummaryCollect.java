package com.mk.taskfactory.biz.order.model;


import java.math.BigDecimal;
import java.util.Date;

/**
 * cps_order_summary_collect
 */

public class CpsOrderSummaryCollect {

    private Long   id;
    private Integer channelType;
    private String  channelCode;
    private String  channelName;
    private Integer  sumOrderFirst;
    private BigDecimal  sumOrderPriceFirst;
    private Integer sumOrder;
    private BigDecimal  sumOrderPrice;
    private Double  deductRateFirst;
    private Double  cpsRateFirst;
    private Date    createTime;
    private Date    ruleUpdateTime;
    private String  ruleUpdateBy;
    private Double  cpsRate;
    private Double  deductRate;
    private Date  orderDate;
    private BigDecimal  sumOrderPriceRateFirst;
    private BigDecimal  sumOrderPriceRate;
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getSumOrderFirst() {
        return sumOrderFirst;
    }

    public void setSumOrderFirst(Integer sumOrderFirst) {
        this.sumOrderFirst = sumOrderFirst;
    }

    public BigDecimal getSumOrderPriceFirst() {
        return sumOrderPriceFirst;
    }

    public void setSumOrderPriceFirst(BigDecimal sumOrderPriceFirst) {
        this.sumOrderPriceFirst = sumOrderPriceFirst;
    }

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

    public Double getDeductRateFirst() {
        return deductRateFirst;
    }

    public void setDeductRateFirst(Double deductRateFirst) {
        this.deductRateFirst = deductRateFirst;
    }

    public Double getCpsRateFirst() {
        return cpsRateFirst;
    }

    public void setCpsRateFirst(Double cpsRateFirst) {
        this.cpsRateFirst = cpsRateFirst;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRuleUpdateTime() {
        return ruleUpdateTime;
    }

    public void setRuleUpdateTime(Date ruleUpdateTime) {
        this.ruleUpdateTime = ruleUpdateTime;
    }

    public String getRuleUpdateBy() {
        return ruleUpdateBy;
    }

    public void setRuleUpdateBy(String ruleUpdateBy) {
        this.ruleUpdateBy = ruleUpdateBy;
    }

    public Double getCpsRate() {
        return cpsRate;
    }

    public void setCpsRate(Double cpsRate) {
        this.cpsRate = cpsRate;
    }

    public Double getDeductRate() {
        return deductRate;
    }

    public void setDeductRate(Double deductRate) {
        this.deductRate = deductRate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getSumOrderPriceRateFirst() {
        return sumOrderPriceRateFirst;
    }

    public void setSumOrderPriceRateFirst(BigDecimal sumOrderPriceRateFirst) {
        this.sumOrderPriceRateFirst = sumOrderPriceRateFirst;
    }

    public BigDecimal getSumOrderPriceRate() {
        return sumOrderPriceRate;
    }

    public void setSumOrderPriceRate(BigDecimal sumOrderPriceRate) {
        this.sumOrderPriceRate = sumOrderPriceRate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
