package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSaleConfig;

import java.util.List;

@MyBatisRepository
public interface RoomSaleConfigMapper {

    public List<TRoomSaleConfig> queryRoomSaleConfigByParams(TRoomSaleConfigDto bean);
    public Integer saveRoomSaleConfig(TRoomSaleConfigDto bean);
    public Integer delTRoomTypeById(Integer id);
    public Integer updateRoomSaleConfig(TRoomSaleConfigDto bean);

}
