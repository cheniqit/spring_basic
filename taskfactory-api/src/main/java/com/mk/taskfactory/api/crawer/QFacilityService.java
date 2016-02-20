package com.mk.taskfactory.api.crawer;

import com.mk.taskfactory.api.dtos.crawer.QFacilityDto;
import com.mk.taskfactory.api.dtos.crawer.QHotelDto;

import java.util.List;

public interface QFacilityService {
    public List<QFacilityDto> qureyByPramas(QFacilityDto bean);
    public QFacilityDto getByPramas(QFacilityDto bean);
    public Integer save(QFacilityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QFacilityDto bean);
    public Integer count(QFacilityDto bean);
}
