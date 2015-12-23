package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;

import java.util.Map;

public interface DoPriceDumpService {
    public Map<String,Object> doPriceDump();
    public Map<String,Object> priceContrast();
    public Map<String,Object> sendEmail(TRoomPriceContrastDto bean);
}
