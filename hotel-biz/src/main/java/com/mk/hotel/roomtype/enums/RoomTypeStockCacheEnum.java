package com.mk.hotel.roomtype.enums;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum RoomTypeStockCacheEnum {
    LOCK_KEY(10l, "HOTEL_ROOMTYPE_STOCK_LOCK_"),     //操作锁
    AVAILABLE_KEY(20l, "HOTEL_ROOMTYPE_STOCK_"),     //普通房可用数量
//    OtsUsingKey(30l, "RoomTypeStockUsing"),
//    PmsUsingKey(40l, "RoomTypeStockPmsUsing"),
    PROMO_KEY(50l, "HOTEL_ROOMTYPE_PROMO_STOCK_"),   //特价房数量
    ;
    private final Long id;
    private final String name;

    private RoomTypeStockCacheEnum(Long id, String name) {
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

    public static String getLockKeyName(String hotelId, String roomTypeId, Date day) {

        if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == day) {
            return "";
        }

        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder result = new StringBuilder()
                .append(RoomTypeStockCacheEnum.LOCK_KEY.getName())
                .append(hotelId)
                .append("_")
                .append(roomTypeId)
                .append("_")
                .append(format.format(day));

        return result.toString();
    }

    public static String getAvailableHashName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypeStockCacheEnum.AVAILABLE_KEY.getName())
                .append(roomTypeId);

        return result.toString();
    }


    public static String getPromoHashName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypeStockCacheEnum.PROMO_KEY.getName())
                .append(roomTypeId);

        return result.toString();
    }

}
