package com.mk.hotel.roomtype.enums;

import org.apache.commons.lang.StringUtils;

public enum RoomTypePriceCacheEnum {
    PRICE_KEY(10l, "HOTEL_ROOMTYPE_PRICE_"),
    OTA_HOTEL_ROOMTYPE_SETTLEMENT_PRICE(20l, "OTA_HOTEL_ROOMTYPE_SETTLEMENT_PRICE_");  // roomTypeId;
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

    public static String getPriceHashName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypePriceCacheEnum.PRICE_KEY.getName())
                .append(roomTypeId);

        return result.toString();
    }

    public static String getOtaSetlementPriceName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypePriceCacheEnum.OTA_HOTEL_ROOMTYPE_SETTLEMENT_PRICE.getName())
                .append(roomTypeId);

        return result.toString();
    }

}
