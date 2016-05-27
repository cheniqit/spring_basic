package com.mk.hotel.roomtype;

import java.util.Date;

public interface RoomTypeStockService {

    /**
     * 更新房量
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param day        日期
     * @param totalNum   总可用房量
     * @param promoNum   特价房房量
     */
    void updateRedisStock(String hotelId, String roomTypeId, Date day, Integer totalNum, Integer promoNum) ;

}
