package com.mk.hotel.roomtype.service.impl;

import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypePriceServiceImpl implements RoomTypePriceService {
    @Autowired
    private RoomTypePriceMapper roomTypePriceMapper;

}
