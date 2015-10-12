package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomTypeInfoService;
import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.mapper.RoomTypeInfoMapper;
import com.mk.taskfactory.biz.mapper.RoomTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeInfoServiceImpl implements RoomTypeInfoService {

    @Autowired
    private RoomTypeInfoMapper roomTypeInfoMapper;

    @Override
    public void deleteByRoomType(int roomTypeId) {
        this.roomTypeInfoMapper.deleteByRoomType(roomTypeId);
    }
}
