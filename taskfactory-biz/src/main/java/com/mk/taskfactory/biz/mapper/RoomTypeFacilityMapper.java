package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.api.dtos.TRoomTypeFacilityDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomTypeFacility;

import java.util.List;

@MyBatisRepository
public interface RoomTypeFacilityMapper {
    public void deleteByRoomType(Integer roomTypeId);
    public List<TRoomTypeFacility> findByRoomTypeId(Integer roomTypeId);
    public Integer saveRoomSaleConfig(TRoomTypeFacilityDto bean);
}
