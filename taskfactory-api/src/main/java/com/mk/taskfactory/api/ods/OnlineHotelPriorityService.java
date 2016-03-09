package com.mk.taskfactory.api.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelPriorityDto;

import java.util.List;

public interface OnlineHotelPriorityService {
    public List<OnlineHotelPriorityDto> qureyByPramas(OnlineHotelPriorityDto bean);
    public OnlineHotelPriorityDto getByPramas(OnlineHotelPriorityDto bean);
    public Integer save(OnlineHotelPriorityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelPriorityDto bean);
    public Integer count(OnlineHotelPriorityDto bean);
}
