package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypePmsDynamicInfoDto;

import java.util.Map;

public interface PmsDynamicInfoService {
    public Map<String,Object> pmsDynamicToLog(TRoomTypePmsDynamicInfoDto dto);

}

