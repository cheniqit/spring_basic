package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

@MyBatisRepository
public interface RoomTypeMapper {
    public void updatePlusRoomNum(TRoomTypeDto roomTypeDto);
}
