package com.mk.hotel.hotelinfo.service.impl;

import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class HotelServiceImpl  implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public Hotel findHotelById(Long hotelId) {
        return  hotelMapper.selectByPrimaryKey(hotelId);
    }
}
