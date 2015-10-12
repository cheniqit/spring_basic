package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

import java.util.List;

@MyBatisRepository
public interface RoomSaleMapper {
    List<TRoomSaleDto> queryYesterdayRoomSale();
}
