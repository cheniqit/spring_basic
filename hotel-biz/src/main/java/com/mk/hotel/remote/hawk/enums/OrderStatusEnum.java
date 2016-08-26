package com.mk.hotel.remote.hawk.enums;

/**
 * Created by jeashi on 16/2/19.
 */
public enum OrderStatusEnum {

    /**=========正向状态======***/
    waitConfirm(0,"未处理", ""),
    confirm(1,"已接受", ""),
    checkIn(2,"已入住", ""),

    /**=========反向状态======***/
    refuse(3,"已拒绝", ""),
    userCancelOrder(4,"已取消", ""),

    ;

    private final Integer code;
    private final String name;
    private final String desc;

    OrderStatusEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static OrderStatusEnum getEnumById(Integer code){
        for (OrderStatusEnum enu : OrderStatusEnum.values()) {
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

    public String getDesc() {
        return desc;
    }
}
