package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;

import java.util.Map;

public interface OrderSendJobService {
    public Map<String,Object> orderSendToCs(OrderToCsDto bean);
}
