package com.mk.taskfactory.api.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class PromoCardTypeDto {
    private Long id;
    private String type;
    private String cardName;
    private String createTime;
    private String price;
    private Integer beginUseDate;
    private BigDecimal endUseDate;
    private BigDecimal beginUseTime;
    private Date endUseTime;
    private Date useCityId;
    private String description;
    private Date cardType;
    private Date cost;
    private String isValidity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getBeginUseDate() {
        return beginUseDate;
    }

    public void setBeginUseDate(Integer beginUseDate) {
        this.beginUseDate = beginUseDate;
    }

    public BigDecimal getEndUseDate() {
        return endUseDate;
    }

    public void setEndUseDate(BigDecimal endUseDate) {
        this.endUseDate = endUseDate;
    }

    public BigDecimal getBeginUseTime() {
        return beginUseTime;
    }

    public void setBeginUseTime(BigDecimal beginUseTime) {
        this.beginUseTime = beginUseTime;
    }

    public Date getEndUseTime() {
        return endUseTime;
    }

    public void setEndUseTime(Date endUseTime) {
        this.endUseTime = endUseTime;
    }

    public Date getUseCityId() {
        return useCityId;
    }

    public void setUseCityId(Date useCityId) {
        this.useCityId = useCityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCardType() {
        return cardType;
    }

    public void setCardType(Date cardType) {
        this.cardType = cardType;
    }

    public Date getCost() {
        return cost;
    }

    public void setCost(Date cost) {
        this.cost = cost;
    }

    public String getIsValidity() {
        return isValidity;
    }

    public void setIsValidity(String isValidity) {
        this.isValidity = isValidity;
    }
}
