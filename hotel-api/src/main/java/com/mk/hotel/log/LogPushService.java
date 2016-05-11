package com.mk.hotel.log;

import com.mk.hotel.log.dto.LogPushDto;

public interface LogPushService {
    public int save(LogPushDto logPush);
}