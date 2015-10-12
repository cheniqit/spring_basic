package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSaleService;
import com.mk.taskfactory.api.RoomService;
import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.mapper.RoomMapper;
import com.mk.taskfactory.biz.mapper.RoomSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public int countRoomByRoomType(int roomTypeId) {
        return this.roomMapper.countRoomByRoomType(roomTypeId);
    }

    @Override
    public void updateRoomTypeByRoomType(TRoomChangeTypeDto roomChangeTypeDto) {
        if (null == roomChangeTypeDto
                || null == roomChangeTypeDto.getOldRoomTypeId()
                || null == roomChangeTypeDto.getRoomTypeId()) {
            //TODO LOG
            return;
        }
        this.roomMapper.updateRoomTypeByRoomType(roomChangeTypeDto);
    }
}
