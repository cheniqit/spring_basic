package com.mk.hotel.hotelinfo.enums;

import org.apache.commons.lang.StringUtils;

public enum HotelCacheEnum {
    HOTEL_KEY(10l, "HOTEL_JSON_INFO_"),
    CITY_HOTEL_SET(10l, "CITY_HOTEL_SET_"),
    ;
    private final Long id;
    private final String name;

    private HotelCacheEnum(Long id, String name) {
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

    public static String getHotelKeyName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(HotelCacheEnum.HOTEL_KEY.getName())
                .append(roomTypeId);

        return result.toString();
    }

    public static String getCityHotelSetName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(HotelCacheEnum.CITY_HOTEL_SET.getName())
                .append(roomTypeId);

        return result.toString();
    }
}
