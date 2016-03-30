package com.mk.taskfactory.biz.mapper.ht;

import com.mk.taskfactory.api.dtos.ht.QFacilityDto;
import com.mk.taskfactory.model.ht.QFacility;

import java.util.List;

public interface QFacilityMapper {
    public List<QFacility> qureyByPramas(QFacilityDto bean);
    public QFacility getByPramas(QFacilityDto bean);
    public Integer save(QFacilityDto bean);
    public Integer delete(Integer id);
    public Integer updateById(QFacilityDto bean);
    public Integer count(QFacilityDto bean);
}