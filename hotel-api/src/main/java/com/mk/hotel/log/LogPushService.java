package com.mk.hotel.log;

import com.mk.hotel.log.dto.LogPushDto;

import java.util.Date;
import java.util.List;

public interface LogPushService {
    int save(LogPushDto logPush);

    LogPushDto getById(Long id);

    List<LogPushDto> getByTime(Date start, Date end, Long id);

    Integer countByTime(Date start, Date end, Long id);
}