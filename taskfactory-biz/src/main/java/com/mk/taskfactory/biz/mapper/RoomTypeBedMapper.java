package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.TRoomTypeBedDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomTypeBed;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface RoomTypeBedMapper {

    public List<TRoomTypeBed> queryParams(TRoomTypeBedDto dto);

    public int saveRoomTypeBed(TRoomTypeBedDto dto);

    public int deleteById(Long id);

    public int deleteByRoomTypeId(Long roomTypeId);

    public int updateById(TRoomTypeBedDto dto);

    public int createByRoomTypeId(Map<String,Long> param);
}
