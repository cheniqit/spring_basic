package com.mk.hotel.hotelinfo;


import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;

import java.util.List;

public interface HotelFacilityService {
    void saveOrUpdateByFangId (List<HotelFacilityDto> hotelFacilityDtoList);
    void mergeHotelFacility(int pageNo);
}
