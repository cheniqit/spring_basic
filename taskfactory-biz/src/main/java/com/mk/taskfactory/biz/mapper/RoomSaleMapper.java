package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSale;

import java.util.List;

@MyBatisRepository
public interface RoomSaleMapper {
    public List<TRoomSale> queryYesterdayRoomSale();

    public void saveRoomSale(TRoomSaleDto roomSaleDto);
}
