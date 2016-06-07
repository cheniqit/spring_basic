package com.mk.hotel.hotelinfo.enums;


import org.codehaus.plexus.util.StringUtils;

public enum HotelSourceEnum {
    TYPE_FANGBABA(10l, "房爸爸"),
    TYPE_FANQIELAILE(20l, "番茄来了"),
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
