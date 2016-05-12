package com.mk.hotel.roomtype;

import java.util.Date;

public interface RoomTypeStockService {

    /**
     * 操作锁
     *
     * @param hotelId        酒店id
     * @param roomTypeId     房型id
     * @param day            日期
     * @param lockTime       锁时间(秒)
     * @param maxWaitTimeOut 超时等待(豪秒)
     * @return
     */
    void lock(String hotelId, String roomTypeId, Date day, int lockTime, long maxWaitTimeOut);

    /**
     * 操作解锁
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param day        日期
     */
    void unlock(String hotelId, String roomTypeId, Date day);

    /**
     * ots锁房,已执行操作锁
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param from       开始日期
     * @param to         结束日期
     * @param num        锁几间房
     */
    void lockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num);

    /**
     * ots解锁,已执行操作锁
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param from       开始日期
     * @param to         结束日期
     * @param num        锁几间房
     */
    void unlockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num);

    /**
     * 锁到pms的订单,未执行操作锁.使用前,必须先获得操作锁.
     *
     * @param hotelId
     * @param roomTypeId
     * @param from
     * @param to
     * @param num
     */
    void lockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num);

    /**
     * 解锁到pms到订单房间,未执行操作锁.使用前,必须先获得操作锁.
     *
     * @param hotelId
     * @param roomTypeId
     * @param from
     * @param to
     * @param num
     */
    void unlockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num);

    /**
     * 更新房量
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param day        日期
     * @param totalNum   房型总房量
     * @param num        可用房量
     */
    void updateStock(String hotelId, String roomTypeId, Date day, Integer totalNum, Integer num);

}