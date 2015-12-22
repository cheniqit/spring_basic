package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;

import java.util.Map;

public interface DoPriceDumpService {
    public Map<String,Object> doPriceDump();
}
