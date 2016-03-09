package com.mk.taskfactory.biz.mapper.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelPriorityDto;
import com.mk.taskfactory.model.ods.OnlineHotelPriority;
import java.util.List;

public interface OnlineHotelPriorityMapper {
    public List<OnlineHotelPriority> qureyByPramas(OnlineHotelPriorityDto bean);
    public OnlineHotelPriority getByPramas(OnlineHotelPriorityDto bean);
    public Integer save(OnlineHotelPriorityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelPriorityDto bean);
    public Integer count(OnlineHotelPriorityDto bean);
}