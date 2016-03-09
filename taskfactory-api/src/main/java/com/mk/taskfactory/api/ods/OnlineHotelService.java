package com.mk.taskfactory.api.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelDto;

import java.util.List;

public interface OnlineHotelService {
    public List<OnlineHotelDto> qureyByPramas(OnlineHotelDto bean);
    public OnlineHotelDto getByPramas(OnlineHotelDto bean);
    public Integer save(OnlineHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelDto bean);
    public Integer count(OnlineHotelDto bean);
}
