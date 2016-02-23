package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.QCommentDto;
import com.mk.taskfactory.model.crawer.QComment;
import com.mk.taskfactory.model.crawer.TmpMappingRoomTypeId;

import java.util.List;

public interface TmpMappingRoomTypeIdMapper {
    public List<TmpMappingRoomTypeId> qureyByPramas(TmpMappingRoomTypeId bean);
    public Integer count();
}