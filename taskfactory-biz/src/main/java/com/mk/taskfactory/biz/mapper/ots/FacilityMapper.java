package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.crawer.QCommentDto;
import com.mk.taskfactory.model.TFacility;
import com.mk.taskfactory.model.crawer.QComment;

import java.util.List;

public interface FacilityMapper {
    public List<TFacility> qureyByPramas(TFacilityDto bean);
    public TFacility getByPramas(TFacilityDto bean);
    public Integer save(TFacilityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TFacilityDto bean);
    public Integer count(TFacilityDto bean);
}