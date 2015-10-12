package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSettingService;
import com.mk.taskfactory.api.RoomTypeInfoService;
import com.mk.taskfactory.biz.mapper.RoomTypeInfoMapper;
import com.mk.taskfactory.biz.mapper.RoomTypeSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomSettingServiceImpl implements RoomSettingService {

    @Autowired
    private RoomTypeSettingMapper roomTypeSettingMapper;

    @Override
    public void deleteByRoomType(int roomTypeId) {
        this.roomTypeSettingMapper.deleteByRoomType(roomTypeId);
    }
}
