package com.mk.taskfactory.api;

import com.mk.taskfactory.api.dtos.RoomSaleToOtsDto;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.api.dtos.TRoomTypeBedDto;

import java.util.List;
import java.util.Map;

public interface RoomTypeBedService {

    public List<TRoomTypeBedDto> queryParams(TRoomTypeBedDto dto);

    public int saveRoomTypeBed(TRoomTypeBedDto dto);

    public int deleteById(Long id);

    public int deleteByRoomTypeId(Long roomTypeId);

    public int updateById(TRoomTypeBedDto dto);

    public int createByRoomTypeId(Long oldRoomTypeId, Long newRoomTypeId);
}
