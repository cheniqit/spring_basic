package com.mk.taskfactory.biz.mapper.ods;


import com.mk.taskfactory.api.dtos.THotelDto;
import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.THotel;
import com.mk.taskfactory.model.ods.TRoomPriceContrast;
import com.mk.taskfactory.model.ods.TRoomTypePriceDump;

import java.math.BigInteger;
import java.util.List;

@MyBatisRepository
public interface RoomTypePriceDumpMapper {
    public List<TRoomTypePriceDump> queryByParams(TRoomTypePriceDumpDto bean);
    public TRoomTypePriceDump getById(BigInteger id);
    public Integer save(TRoomTypePriceDumpDto bean);
    public Integer deleteById(BigInteger id);
    public Integer updateById(TRoomTypePriceDumpDto bean);
}
