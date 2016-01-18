package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypePmsDynamicInfoDto;

import java.util.List;

public interface RoomTypePmsDynamicInfoService {
    public List<TRoomTypePmsDynamicInfoDto> qureyByPramas(TRoomTypePmsDynamicInfoDto bean);
    public TRoomTypePmsDynamicInfoDto getByPramas(TRoomTypePmsDynamicInfoDto bean);
    public Integer save(TRoomTypePmsDynamicInfoDto bean);
    public Integer delete(Integer id);
    public Integer updateById(Integer id);
    public Integer count(TRoomTypePmsDynamicInfoDto bean);
}
