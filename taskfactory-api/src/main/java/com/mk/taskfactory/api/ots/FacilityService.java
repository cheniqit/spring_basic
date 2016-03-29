package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.TFacilityDto;

import java.util.List;

public interface FacilityService {
    public List<TFacilityDto> qureyByPramas(TFacilityDto bean);
    public TFacilityDto getByPramas(TFacilityDto bean);
    public Integer save(TFacilityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TFacilityDto bean);
    public Integer count(TFacilityDto bean);
}
