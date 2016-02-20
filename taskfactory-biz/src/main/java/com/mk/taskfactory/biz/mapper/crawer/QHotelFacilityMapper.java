package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.QHotelFacilityDto;
import com.mk.taskfactory.model.crawer.QHotelFacility;

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