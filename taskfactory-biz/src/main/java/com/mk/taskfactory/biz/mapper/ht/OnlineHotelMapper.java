package com.mk.taskfactory.biz.mapper.ht;

import com.mk.taskfactory.api.dtos.ht.OnlineHotelDto;
import com.mk.taskfactory.model.ht.OnlineHotel;
import java.util.List;

public interface OnlineHotelMapper {
    public List<OnlineHotel> qureyByPramas(OnlineHotelDto bean);
    public OnlineHotel getByPramas(OnlineHotelDto bean);
    public Integer save(OnlineHotelDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelDto bean);
    public Integer count(OnlineHotelDto bean);
    Integer queryHasOnlineHotelByCityCode(String cityCode);
}