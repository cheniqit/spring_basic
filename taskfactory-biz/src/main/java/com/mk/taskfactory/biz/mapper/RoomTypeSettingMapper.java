package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.biz.repository.MyBatisRepository;

@MyBatisRepository
public interface RoomTypeSettingMapper {

    public void deleteByRoomType(int roomTypeId);
}
