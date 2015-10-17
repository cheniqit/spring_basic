package com.mk.taskfactory.biz.mapper;

import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSale;

import java.util.HashMap;
import java.util.List;

@MyBatisRepository
public interface RoomSaleMapper {
    public List<TRoomSale> queryYesterdayRoomSale();
    public void saveRoomSale(TRoomSaleDto roomSaleDto);
    public void updateRoomSaleBack(TRoomSaleDto roomSaleDto);
    public List<TRoomSale> queryUnBackRoomSale();
    public List<TRoomSale> queryRoomSale(TRoomSaleDto bean);
    public TRoomSale getHotelSaleByHotelId(Integer hotelId);

    public List<TRoomSaleDto>   queryByConfigAndBack(HashMap parme);
}
