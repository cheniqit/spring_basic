package com.mk.hotel.hotelinfo.enums;


public enum HotelSourceEnum {
    LEZHU(1l, "LEZHU"),
    OTA(2l, "OTA"),
    FANQIE(3l, "FANQIE"),
    ;
    private final Long id;
    private final String name;

    private HotelSourceEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + " " + name;
    }


    public static HotelSourceEnum getById(Long id){
        for(HotelSourceEnum hotelPicTypeEnum : HotelSourceEnum.values()){
            if(hotelPicTypeEnum.getId().equals(id)){
                return hotelPicTypeEnum;
            }
        }
        return null;
    }

    public static HotelSourceEnum getByName(String name) {
        for(HotelSourceEnum hotelPicTypeEnum : HotelSourceEnum.values()){
            if(hotelPicTypeEnum.getName().equals(name)){
                return hotelPicTypeEnum;
            }
        }
        return null;
    }
}
