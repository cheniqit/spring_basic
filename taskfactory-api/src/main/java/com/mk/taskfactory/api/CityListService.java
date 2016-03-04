package com.mk.taskfactory.api;
import com.mk.taskfactory.api.dtos.HotelDayClickDto;
import com.mk.taskfactory.api.dtos.HotelHourClickDto;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.api.dtos.TCityListDto;

import java.util.Map;

public interface CityListService {
    public Map<String,Object> cityToCityList(TCityDto bean);

}
