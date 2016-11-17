package com.mk.hotel.consume.enums;


/**
 * 
 * @author chuaiqing.
 *
 */
public enum TopicEnum {
    ROOM_TYPE_PRICE(1, "hotel_roomtype_price", "价格"),
    ROOM_TYPE_STOCK(2, "hotel_roomtype_stock", "库存"),
    ;

    TopicEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    private Integer code;
    private String name;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static TopicEnum getByName(String name){
        for (TopicEnum temp : TopicEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return null;
    }
}
