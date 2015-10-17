package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.TBasePriceDto;

public interface BasePriceService {
    public void saveBasePriceService(TBasePriceDto tBasePriceDto);
    public TBasePriceDto findTRoomTypeById(Integer id);
}
