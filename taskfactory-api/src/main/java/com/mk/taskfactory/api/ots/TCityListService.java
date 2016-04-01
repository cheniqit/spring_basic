package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.TCityListDto;

import java.util.List;

public interface TCityListService {
    public List<TCityListDto> qureyByPramas(TCityListDto bean);
    public TCityListDto getByPramas(TCityListDto bean);
    public Integer save(TCityListDto bean);
    public Integer delete(Integer id);
    public Integer updateById(TCityListDto bean);
    public Integer count(TCityListDto bean);
}
