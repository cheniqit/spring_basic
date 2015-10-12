package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.biz.repository.MyBatisRepository;

@MyBatisRepository
public interface RoomTypeFacilityMapper {

    public void deleteByRoomType(int roomTypeId);
}
