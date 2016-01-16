package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;

import java.util.Map;

public interface PriceToRedisService {

    public Map<String,Object> priceToRedis(TRoomSaleAgreementPriceDto dto);
    public Map<String,Object> updateDealCountToRedis(TRoomSaleAgreementPriceDto dto);
    public Map<String,Object> deleteRedis(Integer id,String key);
}

