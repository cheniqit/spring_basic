package com.mk.hotel.roomtype.enums;

import org.apache.commons.lang.StringUtils;

public enum RoomTypePriceCacheEnum {
    PRICE_KEY(10l, "HOTEL_ROOMTYPE_PRICE_"),
    ;
    private final Long id;
    private final String name;

    private RoomTypePriceCacheEnum(Long id, String name) {
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

    public static String getPriceHashName(String hotelId, String roomTypeId) {
        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypePriceCacheEnum.PRICE_KEY.getName())
                .append("_")
                .append(hotelId)
                .append("_")
                .append(roomTypeId);

        return result.toString();
    }

}
