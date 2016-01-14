package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.RoomSaleAgreementPriceDto;
import com.mk.taskfactory.api.dtos.TRoomSaleTypeDto;

import java.util.List;
import java.util.Map;

public interface PriceToRedisService {

    public Map<String,Object> priceToRedis(RoomSaleAgreementPriceDto dto);
    public Map<String,Object> updateDealCountToRedis(RoomSaleAgreementPriceDto dto);
}

