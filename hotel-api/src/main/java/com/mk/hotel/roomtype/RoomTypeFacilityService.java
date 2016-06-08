package com.mk.hotel.roomtype;


import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;

import java.util.List;

public interface RoomTypeFacilityService {

    void saveOrUpdateByFangid(List<RoomTypeFacilityDto> roomTypeFacilityDtoList, HotelSourceEnum hotelSourceEnum);
}
