package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.model.crawer.TmpMappingRoomTypeId;

import java.util.List;

public interface TmpMappingRoomTypeIdMapper {
    public List<TmpMappingRoomTypeId> qureyByPramas(TmpMappingRoomTypeId bean);
    public Integer count();
}