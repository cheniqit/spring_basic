package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.biz.repository.MyBatisRepository;

@MyBatisRepository
public interface RoomTypeInfoMapper {

    public void deleteByRoomType(Integer roomTypeId);
}
