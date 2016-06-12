package com.mk.hotel.hotelinfo.enums;

public enum HotelPicTypeEnum {
    def(1, "def", "门头及招牌"),
    lobby(2, "lobby", "大堂"),
    mainHousing(3, "mainHousing", "主力房源"),
    roomType(4, "roomType", "房型图片"),
    ;
    private final int code;
    private final String pmsPicCode;
    private final String name;

    HotelPicTypeEnum(int code, String pmsPicCode, String name) {
        this.code = code;
        this.pmsPicCode = pmsPicCode;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getPmsPicCode() {
        return pmsPicCode;
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

    public static HotelPicTypeEnum getHotelPicTypeEnumByPmsPicCode(String code){
        if(code == null){
            return null;
        }
        for(HotelPicTypeEnum hotelPicTypeEnum : HotelPicTypeEnum.values()){
            if(code.equalsIgnoreCase(hotelPicTypeEnum.getPmsPicCode())){
                return hotelPicTypeEnum;
            }
        }
        return null;
    }
}
