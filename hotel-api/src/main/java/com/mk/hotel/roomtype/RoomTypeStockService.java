package com.mk.hotel.roomtype;

import java.util.Date;

public interface RoomTypeStockService {

    final int LOCK_TIIME = 10 * 1000;
    final long MAX_WAIT_TIME_OUT = 30 * 1000;
    /**
     * 锁定
     * @param hotelId hotelId
     * @param roomTypeId roomTypeId
     * @param day 指定日期
     * @param lockTime (毫秒)
     * @param maxWaitTimeOut (毫秒)
     */
    void lock(String hotelId, String roomTypeId, Date day, int lockTime, long maxWaitTimeOut);

    /**
     * 解锁
     * @param hotelId hotelId
     * @param roomTypeId roomTypeId
     * @param day 指定日期
     */
    void unlock(String hotelId, String roomTypeId, Date day);

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
