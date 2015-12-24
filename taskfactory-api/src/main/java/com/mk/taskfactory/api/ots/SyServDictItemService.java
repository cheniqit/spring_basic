package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.SyServDictItemDto;

import java.util.List;

public interface SyServDictItemService {
    public List<SyServDictItemDto> queryByParams(SyServDictItemDto bean);
}
