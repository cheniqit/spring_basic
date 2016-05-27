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
    void updateRedisStock(Long hotelId, Long roomTypeId, Date day, Integer totalNum, Integer promoNum);

    /**
     * 更新房量
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param promoNum   特价房房量
     */
    void updatePromoRedisStock(Long hotelId, Long roomTypeId, Integer promoNum);

}
