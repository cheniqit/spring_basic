package com.mk.taskfactory.api;
import com.mk.taskfactory.api.dtos.HotelDayClickDto;
import com.mk.taskfactory.api.dtos.HotelHourClickDto;
import com.mk.taskfactory.api.dtos.TBasePriceDto;

import java.util.Map;

public interface BookClickService {
    public Map<String,Object> thotelHourStatistics(HotelHourClickDto bean);
    public Map<String,Object> thotelDayStatistics(HotelDayClickDto bean);

}
