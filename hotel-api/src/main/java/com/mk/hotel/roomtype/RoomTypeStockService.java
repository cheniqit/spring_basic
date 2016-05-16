package com.mk.hotel.roomtype;

import java.util.Date;

public interface RoomTypeStockService {

//    /**
//     * 操作锁
//     *
//     * @param hotelId        酒店id
//     * @param roomTypeId     房型id
//     * @param day            日期
//     * @param lockTime       锁时间(秒)
//     * @param maxWaitTimeOut 超时等待(豪秒)
//     * @return
//     */
//    void lock(String hotelId, String roomTypeId, Date day, int lockTime, long maxWaitTimeOut);
//
//    /**
//     * 操作锁
//     *
//     * @param hotelId        酒店id
//     * @param roomTypeId     房型id
//     * @param from           开始日期
//     * @param to             结束日期
//     * @param lockTime       锁时间(秒)
//     * @param maxWaitTimeOut 超时等待(豪秒)
//     * @return
//     */
//    void lock(String hotelId, String roomTypeId, Date from, Date to, int lockTime, long maxWaitTimeOut);
//
//    /**
//     * 操作解锁
//     *
//     * @param hotelId    酒店id
//     * @param roomTypeId 房型id
//     * @param day        日期
//     */
//    void unlock(String hotelId, String roomTypeId, Date day);
//
//    /**
//     * 操作解锁
//     *
//     * @param hotelId    酒店id
//     * @param roomTypeId 房型id
//     * @param from       开始日期
//     * @param to         结束日期
//     */
//    void unlock(String hotelId, String roomTypeId, Date from, Date to);
//
//    /**
//     * ots锁房,已执行操作锁
//     *
//     * @param hotelId    酒店id
//     * @param roomTypeId 房型id
//     * @param from       开始日期
//     * @param to         结束日期
//     * @param num        锁几间房
//     */
//    void lockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num);
//
//    /**
//     * ots解锁,已执行操作锁
//     *
//     * @param hotelId    酒店id
//     * @param roomTypeId 房型id
//     * @param from       开始日期
//     * @param to         结束日期
//     * @param num        锁几间房
//     */
//    void unlockRoomType(String hotelId, String roomTypeId, Date from, Date to, Integer num);
//
//    /**
//     * 锁到pms的订单,未执行操作锁.使用前,必须先获得操作锁.锁房状态从ots 到 pms.
//     *
//     * @param hotelId    酒店id
//     * @param roomTypeId 房型id
//     * @param from       开始日期
//     * @param to         结束日期
//     * @param num        锁几间房
//     */
//    void lockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num);
//
//    /**
//     * 解锁到pms到订单房间,未执行操作锁.使用前,必须先获得操作锁.锁房状态回到ots.
//     *
//     * @param hotelId    酒店id
//     * @param roomTypeId 房型id
//     * @param from       开始日期
//     * @param to         结束日期
//     * @param num        解锁几间房
//     */
//    void unlockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num);
//
//
//    /**
//     * 取消pms订单房间锁,,未执行操作锁.使用前,必须先获得操作锁.锁房状态解除,不回到ots.
//     *
//     * @param hotelId    酒店id
//     * @param roomTypeId 房型id
//     * @param from       开始日期
//     * @param to         结束日期
//     * @param num        解锁几间房
//     */
//    void cancelLockPms(String hotelId, String roomTypeId, Date from, Date to, Integer num);

    /**
     * 更新房量
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param day        日期
     * @param num        可用房量
     */
    void updateRedisStock(String hotelId, String roomTypeId, Date day, Integer num);

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
}
