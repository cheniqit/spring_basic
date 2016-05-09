package com.mk.hotel.hotelinfo.service;

import com.mk.hotel.hotelinfo.model.Hotel;

/**
 * Created by chenqi on 16/5/9.
 */
public interface HotelService {
    public Hotel findHotelById(Long hotelId);
}
