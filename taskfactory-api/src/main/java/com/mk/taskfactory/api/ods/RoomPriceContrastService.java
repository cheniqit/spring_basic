package com.mk.taskfactory.api.ods;

import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;

import java.math.BigInteger;
import java.util.List;

public interface RoomPriceContrastService {
    public List<TRoomPriceContrastDto> queryByParams(TRoomPriceContrastDto bean);
    public List<TRoomPriceContrastDto> getRoomPriceContrast(TRoomPriceContrastDto bean);
    public TRoomPriceContrastDto getById(BigInteger id);
    public Integer save(TRoomPriceContrastDto bean);
    public Integer deleteById(BigInteger id);
    public Integer updateById(TRoomPriceContrastDto bean);
}
