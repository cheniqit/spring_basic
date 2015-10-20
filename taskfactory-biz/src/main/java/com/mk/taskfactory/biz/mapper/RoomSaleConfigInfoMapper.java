package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;

import java.util.List;

@MyBatisRepository
public interface RoomSaleConfigInfoMapper {

    public List<TRoomSaleConfigInfo> queryRoomSaleConfigInfoList(TRoomSaleConfigInfo tRoomSaleConfigInfo);

    public TRoomSaleConfigInfo saveRoomSale(TRoomSaleConfigInfo tRoomSaleConfigInfo);

    public int updateRoomSale(TRoomSaleConfigInfo tRoomSaleConfigInfo);
    public TRoomSaleConfigInfo queryRoomSaleConfigById(Integer Id);


}
