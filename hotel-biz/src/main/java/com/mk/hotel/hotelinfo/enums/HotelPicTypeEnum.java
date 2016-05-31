package com.mk.hotel.hotelinfo.enums;

public enum HotelPicTypeEnum {
    def(1, "门头及招牌"),
    lobby(2, "大堂"),
    mainHousing(3, "主力房源"),
    roomType(3, "房型图片"),
    ;
    private final int code;
    private final String name;

    HotelPicTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static HotelPicTypeEnum getHotelPicTypeEnumByCode(Integer code){
        if(code == null){
            return null;
        }
        for(HotelPicTypeEnum hotelPicTypeEnum : HotelPicTypeEnum.values()){
            if(hotelPicTypeEnum.getCode() == code.intValue()){
                return hotelPicTypeEnum;
            }
        }
        return null;
    }
}
