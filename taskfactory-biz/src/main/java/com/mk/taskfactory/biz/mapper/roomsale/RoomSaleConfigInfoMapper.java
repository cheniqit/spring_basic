package com.mk.taskfactory.biz.mapper.roomsale;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;

import java.util.List;

@MyBatisRepository
public interface RoomSaleConfigInfoMapper {

    public List<TRoomSaleConfigInfo> queryRoomSaleConfigInfoList();

}
