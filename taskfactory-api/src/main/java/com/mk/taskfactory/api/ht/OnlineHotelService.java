package com.mk.taskfactory.api.ht;

import com.mk.taskfactory.api.dtos.ht.OnlineHotelDto;

import java.util.List;

public interface OnlineHotelService {
    public List<OnlineHotelDto> qureyByPramas(OnlineHotelDto bean);
    public OnlineHotelDto getByPramas(OnlineHotelDto bean);
    public Integer save(OnlineHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelDto bean);
    public Integer count(OnlineHotelDto bean);
    Integer queryHasOnlineHotelByCityCode(String cityCode);
}
