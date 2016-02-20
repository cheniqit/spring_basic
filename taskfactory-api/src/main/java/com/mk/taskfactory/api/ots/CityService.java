package com.mk.taskfactory.api.ots;

import com.mk.taskfactory.api.dtos.TCityDto;

import java.util.List;

public interface CityService {
    public TCityDto getByCode(String code);
    public List<TCityDto> qureyByPramas(TCityDto bean);
}
