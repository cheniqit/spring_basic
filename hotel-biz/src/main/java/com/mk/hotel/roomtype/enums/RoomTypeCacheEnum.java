package com.mk.hotel.roomtype.enums;

import org.apache.commons.lang.StringUtils;

public enum RoomTypeCacheEnum {
    ROOMTYPE_SET(10l, "HOTEL_ROOMTYPE_INFO_SET_"),
    ROOMTYPE_KEY(20l, "HOTEL_ROOMTYPE_INFO_"),
    ;
    private final Long id;
    private final String name;

    private RoomTypeCacheEnum(Long id, String name) {
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

    public static String getRoomTypeSetName(String hotelId) {
        if (StringUtils.isBlank(hotelId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypeCacheEnum.ROOMTYPE_SET.getName())
                .append("_")
                .append(hotelId);

        return result.toString();
    }

    public static String getRoomTypeKeyName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypeCacheEnum.ROOMTYPE_KEY.getName())
                .append("_")
                .append(roomTypeId);

        return result.toString();
    }

}
