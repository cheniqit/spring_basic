package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypePmsDynamicInfoDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomTypeDynamicPrice;
import com.mk.taskfactory.model.TRoomTypePmsDynamicInfo;

import java.util.List;

@MyBatisRepository
public interface RoomTypePmsDynamicInfoMapper {
    public List<TRoomTypePmsDynamicInfo> qureyByPramas(TRoomTypePmsDynamicInfoDto bean);
    public TRoomTypePmsDynamicInfo getByPramas(TRoomTypePmsDynamicInfoDto bean);
    public Integer save(TRoomTypePmsDynamicInfoDto bean);
    public Integer delete(Integer id);
    public Integer updateById(Integer id);
    public Integer count(TRoomTypePmsDynamicInfoDto bean);
}
