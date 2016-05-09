package com.mk.hotel.hotelinfo.exception.service;

import com.mk.hotel.hotelinfo.exception.model.Hotel;

/**
 * Created by chenqi on 16/5/9.
 */
public interface HotelService {
    public Hotel findHotelById(Long hotelId);
}
