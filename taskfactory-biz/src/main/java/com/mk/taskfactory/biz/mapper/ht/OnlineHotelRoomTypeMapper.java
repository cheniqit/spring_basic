package com.mk.taskfactory.biz.mapper.ht;

import com.mk.taskfactory.api.dtos.ht.OnlineHotelRoomTypeDto;
import com.mk.taskfactory.model.ht.OnlineHotelRoomType;
import java.util.List;

public interface OnlineHotelRoomTypeMapper {
    public List<OnlineHotelRoomType> qureyByPramas(OnlineHotelRoomTypeDto bean);
    public OnlineHotelRoomType getByPramas(OnlineHotelRoomTypeDto bean);
    public Integer save(OnlineHotelRoomTypeDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelRoomTypeDto bean);
    public Integer count(OnlineHotelRoomTypeDto bean);
}