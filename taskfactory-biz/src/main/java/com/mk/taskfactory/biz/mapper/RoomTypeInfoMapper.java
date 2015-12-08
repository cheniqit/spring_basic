package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.api.dtos.TRoomTypeInfoDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomTypeInfo;

import java.util.List;

@MyBatisRepository
public interface RoomTypeInfoMapper {
    public void deleteByRoomType(Integer roomTypeId);
    public TRoomTypeInfo findByRoomTypeId(Integer roomTypeId);
    public Integer saveRoomTypeInfo(TRoomTypeInfoDto bean);
    public List<TRoomTypeInfo> queryTRoomTypeInfo(TRoomTypeInfoDto bean);
    public Integer countTRoomTypeInfo(TRoomTypeInfoDto bean);

}
