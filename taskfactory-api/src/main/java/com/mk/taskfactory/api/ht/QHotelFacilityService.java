package com.mk.taskfactory.api.ht;

import com.mk.taskfactory.api.dtos.ht.QHotelFacilityDto;

import java.util.List;

public interface QHotelFacilityService {
    public List<QHotelFacilityDto> qureyByPramas(QHotelFacilityDto bean);
    public QHotelFacilityDto getByPramas(QHotelFacilityDto bean);
    public Integer save(QHotelFacilityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QHotelFacilityDto bean);
    public Integer count(QHotelFacilityDto bean);
    public List<QHotelFacilityDto> qureyJionFacility(QHotelFacilityDto bean);
    public Integer coutJionFacility(QHotelFacilityDto bean);

}
