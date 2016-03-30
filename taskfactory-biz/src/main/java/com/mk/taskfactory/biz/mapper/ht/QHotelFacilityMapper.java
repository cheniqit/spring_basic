package com.mk.taskfactory.biz.mapper.ht;

import com.mk.taskfactory.api.dtos.ht.QHotelFacilityDto;
import com.mk.taskfactory.model.ht.QHotelFacility;

import java.util.List;

public interface QHotelFacilityMapper {
    public List<QHotelFacility> qureyByPramas(QHotelFacilityDto bean);
    public QHotelFacility getByPramas(QHotelFacilityDto bean);
    public Integer save(QHotelFacilityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelFacilityDto bean);
    public Integer count(QHotelFacilityDto bean);
    public List<QHotelFacility> qureyJionFacility(QHotelFacilityDto bean);
    public Integer coutJionFacility(QHotelFacilityDto bean);

}