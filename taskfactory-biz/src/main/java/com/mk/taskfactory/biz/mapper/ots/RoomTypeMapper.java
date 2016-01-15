package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomType;

import java.util.List;

@MyBatisRepository
public interface RoomTypeMapper {
    public void updatePlusRoomNum(TRoomTypeDto bean);
    public Integer saveTRoomType(TRoomTypeDto bean);
    public Integer deleteTRoomType(Integer id);
    public Integer updateTRoomType(TRoomTypeDto bean);
    public TRoomType findTRoomTypeById(Integer id);
    public List<TRoomType> findByName(TRoomTypeDto bean);
    public List<TRoomType> queryJionThotel(TRoomTypeDto bean);
    public Integer count();

}