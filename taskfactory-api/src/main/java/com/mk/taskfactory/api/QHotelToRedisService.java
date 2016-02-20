package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.THotelDto;
import com.mk.taskfactory.api.dtos.crawer.*;

import java.util.Map;

public interface QHotelToRedisService {

    public Map<String,Object> qHotelToRedis(QHotelDto dto);
    public Map<String,Object> qCommentToRedis(QCommentDto dto);
    public Map<String,Object> qCommentDetailToRedis(QCommentDetailDto dto);
    public Map<String,Object> qFacilityToRedis(QFacilityDto dto);
    public Map<String,Object> qHotelFacilityToRedis(QHotelDto dto);
    public Map<String,Object> qHotelRoomTypeSetToRedis(QHotelDto dto);
    public Map<String,Object> qHotelRoomtypeToRedis(QHotelRoomtypeDto dto);
    public Map<String,Object> qHotelSurroundSetToRedis(QHotelDto dto);
    public Map<String,Object> tFacilityToRedis(TFacilityDto dto);
    public Map<String,Object> tHotelToRedis(THotelDto dto);
    public Map<String,Object> validPriceHotelToRedis();
    public Map<String,Object> validPriceRoomTypeToRedis();
    public Map<String,Object> cityHotelSetToRedis(TCityDto dto);
}

