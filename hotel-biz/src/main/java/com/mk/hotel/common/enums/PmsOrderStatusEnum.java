package com.mk.hotel.common.enums;

/**
 * Created by chenqi on 16/5/19.
 */
public enum PmsOrderStatusEnum {
    /**
     * 待确认
     */
    toBeConfirmed(0, "待确认"),
    /**
     * 已经客服确认，等待老板确认
     */
    waitingBossConfirmed(5, "已经客服确认，等待老板确认"),
    pmsFullStock(7, "pms满房"),
    // /**
    // * 确认中
    // */
    // confirming(10, "确认中"), //已作废
    /**
     * 已确认
     */
    confirmed(20, "已确认"),
    /**
     * 入住
     */
    checkIn(40, "入住"),
    /**
     * 完成
     */
    finished(100, "完成"),
    /**
     * 用户未入住
     */
    noshow(150, "用户未入住"),
    /**
     * 渠道取消
     */
    channelCanceled(200, "订单取消"),
    /**
     * pms取消
     */
    pmsCanceled(210, "pms取消"), //已作废
    /**
     * 客服取消
     */
    customerServiceCanceled(220, "客服取消"), //已作废
    /**
     * 系统取消
     */
    serviceCanceled(230, "系统取消"); //已作废

    private final Integer code;
    private final String name;

    PmsOrderStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static PmsOrderStatusEnum getEnumById(Integer code){
        for (PmsOrderStatusEnum enu : PmsOrderStatusEnum.values()) {
            if(enu.getCode().equals(code)){
                return enu;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


}
