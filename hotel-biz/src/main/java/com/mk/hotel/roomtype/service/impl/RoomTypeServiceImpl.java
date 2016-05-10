package com.mk.hotel.roomtype.service.impl;

import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.log.service.impl.LogPushServiceImpl;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.model.RoomType;
import org.springframework.beans.BeanUtils;
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
