package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSettingService;
import com.mk.taskfactory.biz.mapper.RoomSettingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomSettingServiceImpl implements RoomSettingService {

    @Autowired
    private RoomSettingMapper roomTypeSettingMapper;

    @Override
    public void deleteByRoomType(int roomTypeId) {
        this.roomTypeSettingMapper.deleteByRoomType(roomTypeId);
    }
}
