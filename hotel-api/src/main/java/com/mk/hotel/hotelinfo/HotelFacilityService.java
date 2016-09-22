package com.mk.hotel.hotelinfo;


import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;

import java.util.List;

public interface HotelFacilityService {
    void saveOrUpdateByFangId (List<HotelFacilityDto> hotelFacilityDtoList, HotelSourceEnum hotelSourceEnum);
    void neverHotelFacility(int pageNo);
}
