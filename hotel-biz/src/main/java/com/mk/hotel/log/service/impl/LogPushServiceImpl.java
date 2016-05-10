package com.mk.hotel.log.service.impl;

import com.mk.hotel.log.mapper.LogPushMapper;
import com.mk.hotel.log.model.LogPush;
import com.mk.hotel.log.service.LogPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogPushServiceImpl implements LogPushService {
    @Autowired
    private LogPushMapper logPushMapper;

    public int save(LogPush logPush) {
        if (null != logPush) {
            logPush.setCreateDate(new Date());
            return this.logPushMapper.insert(logPush);
        }

        return -1;
    }
}
