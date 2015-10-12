package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.mapper.RoomTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Override
    public void updatePlusRoomNum(TRoomTypeDto roomTypeDto) {
        if (null ==  roomTypeDto || null == roomTypeDto.getId() || null == roomTypeDto.getRoomNum()) {
            //TODO LOG
            return;
        }
        this.roomTypeMapper.updatePlusRoomNum(roomTypeDto);
    }
}
