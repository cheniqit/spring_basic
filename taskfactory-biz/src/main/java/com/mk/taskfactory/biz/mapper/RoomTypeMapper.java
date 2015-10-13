package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomType;

import java.util.List;

@MyBatisRepository
public interface RoomTypeMapper {
    public void updatePlusRoomNum(TRoomTypeDto bean);
    public Integer saveTRoomType(TRoomTypeDto bean);
    public Integer deleteRoomSaleConfig(Integer id);
    public Integer updateTRoomType(TRoomTypeDto bean);
    public TRoomType findTRoomTypeById(Integer id);

    public List<TRoomType> findByName(TRoomTypeDto bean);
}
