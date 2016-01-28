package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.QHotelFacilityDto;
import com.mk.taskfactory.model.QHotelFacility;

import java.util.List;

public interface QHotelFacilityMapper {
    public List<QHotelFacility> qureyByPramas(QHotelFacilityDto bean);
    public QHotelFacility getByPramas(QHotelFacilityDto bean);
    public Integer save(QHotelFacilityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelFacilityDto bean);
    public Integer count(QHotelFacilityDto bean);
}