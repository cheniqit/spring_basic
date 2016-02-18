package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.crawer.QHotelDto;

import java.util.Map;

public interface QHotelToRedisService {

    public Map<String,Object> qHotelToRedis(QHotelDto dto);
}

