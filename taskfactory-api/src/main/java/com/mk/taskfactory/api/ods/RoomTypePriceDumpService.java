package com.mk.taskfactory.api.ods;

import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;

import java.math.BigInteger;
import java.util.List;

public interface RoomTypePriceDumpService {
    public List<TRoomTypePriceDumpDto> queryByParams(TRoomTypePriceDumpDto bean);
    public TRoomTypePriceDumpDto getById(BigInteger id);
    public Integer save(TRoomTypePriceDumpDto bean);
    public Integer deleteById(BigInteger id);
    public Integer updateById(TRoomTypePriceDumpDto bean);
}
