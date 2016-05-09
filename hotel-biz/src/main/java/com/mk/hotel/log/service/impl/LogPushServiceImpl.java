package com.mk.hotel.log.service.impl;

import com.mk.hotel.log.mapper.LogPushMapper;
import com.mk.hotel.log.model.LogPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogPushServiceImpl {
    @Autowired
    private LogPushMapper logPushMapper;


    public int save(LogPush logPush) {
        return this.logPushMapper.insert(logPush);
    }
}
