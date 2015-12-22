package com.mk.taskfactory.biz.mapper.ods;


import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.BCard;
import com.mk.taskfactory.model.ods.TRoomPriceContrast;

import java.math.BigInteger;
import java.util.List;

@MyBatisRepository
public interface RoomPriceContrastMapper {
    public List<TRoomPriceContrast> queryByParams(TRoomPriceContrastDto bean);
    public TRoomPriceContrast getById(BigInteger id);
    public Integer save(TRoomPriceContrastDto bean);
    public Integer deleteById(BigInteger id);
    public Integer updateById(TRoomPriceContrastDto bean);
}
