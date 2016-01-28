package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.QHotelDto;
import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;

import java.util.Map;

public interface QHotelToRedisService {

    public Map<String,Object> qHotelToRedis(QHotelDto dto);
}

