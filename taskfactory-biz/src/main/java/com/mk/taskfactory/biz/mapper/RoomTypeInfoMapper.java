package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.api.dtos.TRoomTypeInfoDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomTypeInfo;

@MyBatisRepository
public interface RoomTypeInfoMapper {
    public void deleteByRoomType(Integer roomTypeId);
    public TRoomTypeInfoDto findByRoomTypeId(Integer roomTypeId);
    public Integer saveRoomTypeInfo(TRoomTypeInfoDto bean);

}
