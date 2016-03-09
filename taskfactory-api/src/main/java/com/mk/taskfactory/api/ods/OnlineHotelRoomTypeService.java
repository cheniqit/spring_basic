package com.mk.taskfactory.api.ods;

import com.mk.taskfactory.api.dtos.ods.OnlineHotelRoomTypeDto;

import java.util.List;

public interface OnlineHotelRoomTypeService {
    public List<OnlineHotelRoomTypeDto> qureyByPramas(OnlineHotelRoomTypeDto bean);
    public OnlineHotelRoomTypeDto getByPramas(OnlineHotelRoomTypeDto bean);
    public Integer save(OnlineHotelRoomTypeDto bean);
    public Integer delete(Integer id);
    public Integer updateById(OnlineHotelRoomTypeDto bean);
    public Integer count(OnlineHotelRoomTypeDto bean);
}
