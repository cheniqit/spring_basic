package com.mk.hotel.roomtype.service.impl;

import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;

}
