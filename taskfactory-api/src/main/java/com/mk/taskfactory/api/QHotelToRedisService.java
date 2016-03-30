package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TCityListDto;
import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.ht.OnlineHotelDto;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelPriorityDto;
import com.mk.taskfactory.api.dtos.ht.OnlineHotelRoomTypeDto;

import java.util.Map;

public interface QHotelToRedisService {

    public Map<String,Object> onlineHotelToRedis(OnlineHotelDto dto);//5
    public Map<String,Object> roomTypePicMappingToRedis(OnlineHotelDto dto);//4
    public Map<String,Object> onlineRoomTypeToRedis(OnlineHotelRoomTypeDto dto);//6
    public Map<String,Object> tFacilityToRedis(TFacilityDto dto);//1
    public Map<String,Object> cityHotelSetToRedis(TCityListDto dto);//7
    public void indexerjob();//8
    public Map<String,Object> onlineCityToRedis(TCityListDto dto);//2
    public Map<String,Object> hotelPriorityToRedis(OnlineHotelPriorityDto dto);//3
}

