package com.mk.hotel.roomtype;

import java.util.Date;

public interface RoomTypeStockService {

    /**
     * 锁房
     * @param hotelId 酒店id
     * @param roomTypeId 房型id
     * @param from 开始日期
     * @param to 结束日期
     * @param num 锁几间房
     */
    void lockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num);

    /**
     * 解锁
     * @param hotelId 酒店id
     * @param roomTypeId 房型id
     * @param from 开始日期
     * @param to 结束日期
     * @param num 锁几间房
     */
    void unlockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num);

    /**
     * 更新房量
     * @param hotelId 酒店id
     * @param roomTypeId 房型id
     * @param day 日期
     * @param totalNum 房型总房量
     * @param num 可用房量
     */
    void updateStock (String hotelId, String roomTypeId, Date day, Integer totalNum, Integer num);

}
