package com.mk.hotel.roomtype.bean;

/**
 * Created by huangjie on 16/8/15.
 */
public class RoomTypeStockBean {
    private Integer totalNum;
    private Integer totalPromoNum;
    private Integer availableNum ;
    private Integer promoNum;

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTotalPromoNum() {
        return totalPromoNum;
    }

    public void setTotalPromoNum(Integer totalPromoNum) {
        this.totalPromoNum = totalPromoNum;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }

    public Integer getPromoNum() {
        return promoNum;
    }

    public void setPromoNum(Integer promoNum) {
        this.promoNum = promoNum;
    }
}
