package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.QHotelDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelFacilityDto;

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
