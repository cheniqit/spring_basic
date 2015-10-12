package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.biz.repository.MyBatisRepository;

@MyBatisRepository
public interface RoomTypeMapper {
    public void updatePlusRoomNum(int roomTypeId, int plusNum);
}
