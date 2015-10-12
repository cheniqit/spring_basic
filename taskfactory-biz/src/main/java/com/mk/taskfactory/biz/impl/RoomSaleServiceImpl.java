package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.mapper.RoomSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class RoomSaleServiceImpl implements RoomSaleService {

    @Autowired
    private RoomSaleMapper roomSaleMapper;

    @Override
    public List<TRoomSaleDto> queryYesterdayRoomSale() {
        List<TRoomSaleDto> roomSaleDtoList = this.roomSaleMapper.queryYesterdayRoomSale();
        return null;
    }
}
