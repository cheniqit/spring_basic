package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.*;
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
    public Map<String,Object> tHotelToRedis(ValidPrice dto);
    public Map<String,Object> validPriceHotelToRedis();
    public Map<String,Object> validPriceRoomTypeToRedis();
    public Map<String,Object> cityHotelSetToRedis(TCityListDto dto);
    public Map<String,Object> hotelScoreToRedis(HotelScoreDto dto);
    public Map<String,Object> qHotelRoomTypeMinPriceToRedis(QHotelRoomtypeMinPriceDto dto);
    public Map<String,Object> otaPriceToRedis(QHotelDto dto);
    public Map<String,Object> roomtypeOldIdToNew(Integer start);
    public Map<String,Object> temMappingRoomTypeToRedis(Integer start);
    public Map<String,Object> roomtypeSetOldIdToNew(QHotelDto dto);
    public Map<String,Object> tQunarHotelScoreToRedis(QHotelDto dto);
    public Map<String,Object> onlineCityToRedis(TCityListDto dto);

}

