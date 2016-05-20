package com.mk.hotel.hotelinfo.enums;

import org.apache.commons.lang.StringUtils;

public enum HotelFacilityCacheEnum {
    FACILITY_KEY(10l, "HOTEL_FACILITY_INFO_SET_"),
    ;
    private final Long id;
    private final String name;

    private HotelFacilityCacheEnum(Long id, String name) {
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

    public static String getFacilityKeyName(String hotelId) {
        if (StringUtils.isBlank(hotelId)) {
            return "";
        }

        //
        StringBuilder result = new StringBuilder()
                .append(HotelFacilityCacheEnum.FACILITY_KEY.getName())
                .append(hotelId);

        return result.toString();
    }

}
