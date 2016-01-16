package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;

import java.util.Map;

public interface DynamicPriceService {
    public Map<String,Object> dynamicPriceToLog(TRoomTypeDynamicPriceDto dto);
}

