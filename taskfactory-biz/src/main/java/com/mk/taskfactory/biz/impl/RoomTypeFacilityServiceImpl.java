package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomTypeFacilityService;
import com.mk.taskfactory.biz.mapper.RoomTypeFacilityMapper;
import com.mk.taskfactory.biz.mapper.RoomTypeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeFacilityServiceImpl implements RoomTypeFacilityService {

    @Autowired
    private RoomTypeFacilityMapper roomTypeFacilityMapper;

    @Override
    public void deleteByRoomType(int roomTypeId) {
        this.roomTypeFacilityMapper.deleteByRoomType(roomTypeId);
    }
}
