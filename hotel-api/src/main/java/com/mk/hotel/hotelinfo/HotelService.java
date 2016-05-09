package com.mk.hotel.hotelinfo;


import com.mk.hotel.hotelinfo.dto.HotelDto;

public interface HotelService {
    HotelDto findById(Long id);
}
