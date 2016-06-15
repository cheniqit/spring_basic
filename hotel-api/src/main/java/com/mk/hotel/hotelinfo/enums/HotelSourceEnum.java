package com.mk.hotel.hotelinfo.enums;


public enum HotelSourceEnum {
    LEZHU(1, "LEZHU"),
    OTA(2, "OTA"),
    FANQIE(3, "FANQIE"),
    ;
    private final Integer id;
    private final String name;

    private HotelSourceEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + " " + name;
    }


    public static HotelSourceEnum getById(Integer id){
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
