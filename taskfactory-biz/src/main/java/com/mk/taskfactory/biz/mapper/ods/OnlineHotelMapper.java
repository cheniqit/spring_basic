package com.mk.taskfactory.biz.mapper.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelDto;
import com.mk.taskfactory.model.ods.OnlineHotel;
import java.util.List;

public interface OnlineHotelMapper {
    public List<OnlineHotel> qureyByPramas(OnlineHotelDto bean);
    public OnlineHotel getByPramas(OnlineHotelDto bean);
    public Integer save(OnlineHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelDto bean);
    public Integer count(OnlineHotelDto bean);
}