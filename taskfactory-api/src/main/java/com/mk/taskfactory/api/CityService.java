package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TCityDto;

public interface CityService {
    public TCityDto getByCode(String code);
}
