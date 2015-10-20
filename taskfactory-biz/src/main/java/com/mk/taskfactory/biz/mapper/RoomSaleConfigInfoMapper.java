package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.TRoomSaleConfigInfoDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;

import java.util.List;

@MyBatisRepository
public interface RoomSaleConfigInfoMapper {

    public List<TRoomSaleConfigInfo> queryRoomSaleConfigInfoList(TRoomSaleConfigInfoDto dto);

    public int saveRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto);

    public int updateTRoomSaleConfigInfo(TRoomSaleConfigInfoDto dto);

}
