package com.mk.taskfactory.biz.mapper.ots;

import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.TRoomSale;

import java.util.HashMap;
import java.util.List;


public interface RoomSaleMapper {
    public List<TRoomSale> queryYesterdayRoomSale();
    public void saveRoomSale(TRoomSaleDto roomSaleDto);
    public void updateRoomSaleBack(TRoomSaleDto roomSaleDto);
    public List<TRoomSale> queryUnBackRoomSale();
    public List<TRoomSale> queryRoomSale(TRoomSaleDto bean);
    public TRoomSale getHotelSaleByHotelId(Integer hotelId);
    public List<TRoomSale>   queryByConfigAndBack(HashMap parme);
    public List<Integer>   queryByConfigGroup(HashMap parme);

    public List<TRoomSale> queryAll();

    public int updateRoomSale(TRoomSaleDto roomSaleDto);
}
