package com.mk.taskfactory.api;
import com.mk.taskfactory.api.dtos.TBasePriceDto;
public interface BasePriceService {
    public TBasePriceDto selectByPrimaryKey(Long id);

    public TBasePriceDto findByRoomtypeId(Long roomTypeId);

    public int saveBasePriceService(TBasePriceDto dto);

    public int updateBasePriceService(TBasePriceDto dto);
}
