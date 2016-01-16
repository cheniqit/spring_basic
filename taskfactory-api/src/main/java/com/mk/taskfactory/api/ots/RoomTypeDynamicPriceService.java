package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.SyServDictItemDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;

import java.util.List;

public interface RoomTypeDynamicPriceService {
    public List<TRoomTypeDynamicPriceDto> qureyByPramas(TRoomTypeDynamicPriceDto bean);
    public TRoomTypeDynamicPriceDto getByPramas(TRoomTypeDynamicPriceDto bean);
    public Integer save(TRoomTypeDynamicPriceDto bean);
    public Integer delete(Integer id);
    public Integer updateById(Integer id);
    public Integer count(TRoomTypeDynamicPriceDto bean);
}
