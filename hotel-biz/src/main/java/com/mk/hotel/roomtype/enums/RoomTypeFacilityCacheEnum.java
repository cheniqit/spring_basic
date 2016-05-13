package com.mk.hotel.roomtype.enums;

import org.apache.commons.lang.StringUtils;

public enum RoomTypeFacilityCacheEnum {
    FACILITY_KEY(10l, "HOTEL_ROOMTYPE_FACILITY_INFO_SET_"),
    ;
    private final Long id;
    private final String name;

    private RoomTypeFacilityCacheEnum(Long id, String name) {
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

    public static String getFacilityKeyName(String roomTypeId) {
        if (StringUtils.isBlank(roomTypeId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(RoomTypeFacilityCacheEnum.FACILITY_KEY.getName())
                .append("_")
                .append(roomTypeId);

        return result.toString();
    }

}
