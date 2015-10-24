package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TRoomTypeFacilityDto;

import java.util.List;

public interface RoomTypeFacilityService {
    public void deleteByRoomType(Integer roomTypeId);
    public List<TRoomTypeFacilityDto> findByRoomTypeId(Integer roomTypeId);
    public Integer saveRoomSaleConfig(TRoomTypeFacilityDto bean);

}
