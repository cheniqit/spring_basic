package com.mk.hotel.roomtype;

import com.mk.hotel.roomtype.dto.RoomTypeStockDto;
import com.mk.hotel.roomtype.dto.RoomTypeStockRedisDto;
import com.mk.hotel.roomtype.dto.StockInfoDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @param promoNum   计划投放特价房量
     */
    String updateRedisStock(Long hotelId, Long roomTypeId, Date day, Integer totalNum, Integer promoNum);

    /**
     * 更新房量(仅供房爸爸接口使用)
     *
     * @param hotelId    酒店id
     * @param roomTypeId 房型id
     * @param promoNum   特价房房量
     */
    void updatePromoRedisStock(Long hotelId, Long roomTypeId, Integer promoNum);

    /**
     * 库存满
     * @param hotelId
     * @param roomTypeId
     * @param from 开始日期
     * @param to 结束日期
     */
    void fullStock(Long hotelId, Long roomTypeId, Date from, Date to);


    /**
     * 更新房量
     *
     * @param hotelId       酒店id
     * @param roomTypeId    房型id
     * @param day           日期
     * @param totalNum      签约房量
     * @param totalPromoNum 计划投放特价房量
     * @return
     */
    String updateRedisStockByTotal(Long hotelId, Long roomTypeId, Date day, Integer totalNum, Integer totalPromoNum);

    /**
     * 查询redis中库存量
     * @param roomTypeId
     * @param day
     * @return
     */
    RoomTypeStockRedisDto queryStockFromRedis(Long roomTypeId, Date day);

    /**
     *
     * @param roomTypeId
     * @param begin
     * @param end
     * @return
     */
    List<StockInfoDto> getRemoteStock (Long roomTypeId, Date begin, Date end);

    /**
     * 查询特价库存
     * @param hotelId
     * @param roomTypeId
     * @param from
     * @param to
     * @return
     */
    Map<String, Integer> getPromoStock(String hotelId, String roomTypeId, Date from, Date to) ;

    /**
     * 查询普通房库存
     * @param hotelId
     * @param roomTypeId
     * @param from
     * @param to
     * @return
     */
    Map<String, Integer> getNormalStock(String hotelId, String roomTypeId, Date from, Date to) ;

    /**
     * 查询连续天内的可用特价库存
     * @param hotelId
     * @param roomTypeId
     * @param from
     * @param to
     * @return
     */
    Integer getAvailableByPromo(String hotelId, String roomTypeId, String from, String to) ;

    /**
     * 查询连续天内的可用普通库存
     * @param hotelId
     * @param roomTypeId
     * @param from
     * @param to
     * @return
     */
    Integer getAvailableByNormal(String hotelId, String roomTypeId, String from, String to);

    /**
     * 从redis 持久化到 db
     * @param roomTypeId
     * @param date
     * @return
     */
    int savePersistToDb(Long roomTypeId, Date date);

    int saveOrUpdate(RoomTypeStockDto dto);

    RoomTypeStockDto queryByDate(Long roomTypeId, Date date);
}
