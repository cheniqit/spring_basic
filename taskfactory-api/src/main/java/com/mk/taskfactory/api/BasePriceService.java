package com.mk.taskfactory.api;
import com.mk.taskfactory.api.dtos.TBasepriceDto;
public interface BasePriceService {
    public TBasepriceDto selectByPrimaryKey(Long id);

    public TBasepriceDto findByRoomtypeId(Long roomTypeId);
}
