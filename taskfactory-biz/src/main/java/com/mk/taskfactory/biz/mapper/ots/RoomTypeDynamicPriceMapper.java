package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomType;
import com.mk.taskfactory.model.TRoomTypeDynamicPrice;

import java.util.List;

@MyBatisRepository
public interface RoomTypeDynamicPriceMapper {
    public List<TRoomTypeDynamicPrice> qureyByPramas(TRoomTypeDynamicPriceDto bean);
    public TRoomTypeDynamicPrice getByPramas(TRoomTypeDynamicPriceDto bean);
    public Integer save(TRoomTypeDynamicPriceDto bean);
    public Integer delete(Integer id);
    public Integer updateById(Integer id);
    public Integer count(TRoomTypeDynamicPriceDto bean);
}
