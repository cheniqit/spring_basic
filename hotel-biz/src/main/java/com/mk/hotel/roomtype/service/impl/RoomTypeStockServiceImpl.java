package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.DateUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.remote.pms.hotelstock.HotelStockRemoteService;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.StockInfoDto;
import com.mk.hotel.roomtype.enums.RoomTypeStockCacheEnum;
import com.mk.hotel.roomtype.model.RoomTypeStock;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangjie on 16/5/11.
 */
@Service
public class RoomTypeStockServiceImpl implements RoomTypeStockService {


    @Autowired
    private HotelStockRemoteService hotelStockRemoteService;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private RoomTypeService roomTypeService;

    public void lock(String hotelId, String roomTypeId, Date day, int lockTime, long maxWaitTimeOut) {

        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();

            //
            String lockKeyName = RoomTypeStockCacheEnum.getLockKeyName(hotelId, roomTypeId, day);

            long start = System.currentTimeMillis();

            String h = jedis.set(lockKeyName, "1", "NX", "EX", lockTime);

            while (null == h) {
                TimeUnit.MILLISECONDS.sleep(10);
                h = jedis.set(lockKeyName, "1", "NX", "EX", lockTime);

                //
                long now = System.currentTimeMillis();
                long diff = now - start;
                if (diff > maxWaitTimeOut) {
                    throw new MyException("-99", "-99", "锁定超时");
                }
            }

            return;

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }

        }

        throw new MyException("-99", "-99", "锁定超时");
    }

    public void unlock(String hotelId, String roomTypeId, Date day) {
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();

            //
            String lockKeyName = RoomTypeStockCacheEnum.getLockKeyName(hotelId, roomTypeId, day);
            jedis.del(lockKeyName);

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void updatePromoRedisStock(Long hotelId, Long roomTypeId, Integer promoNum){
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        if(null == hotel){
            Date date = new Date();
            for (int i =0 ; i<= 30; i++) {
                Date addDate =  DateUtils.addDays(date, i);
                updateRedisStock(hotelId, roomTypeId, addDate, 100, promoNum);
            }
            return;
        }

        RoomTypeDto roomTypeDto = this.roomTypeService.selectById(roomTypeId);
        if(null == roomTypeDto){
            throw new MyException("-99", "-99", "房型不存在");
        }

        //
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setRoomtypeid(roomTypeDto.getFangId().toString());
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
        QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);
        if(response == null || response.getData() == null){
            throw new MyException("-99", "-99", "房爸爸接口调用错误");
        }

        //
        for(QueryStockResponse.Roomtypestocks roomtypestock :  response.getData().getRoomtypestocks()) {
            for (QueryStockResponse.Stockinfos stockInfo : roomtypestock.getStockinfos()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = format.parse(stockInfo.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                updateRedisStock(hotelId, roomTypeId, date, Integer.valueOf(stockInfo.getNum()), promoNum);
            }
        }

    }

    public void updateRedisStock(Long hotelId, Long roomTypeId, Date day, Integer totalNum, Integer promoNum) {
        if (null == hotelId || null == roomTypeId || null == day || null == totalNum || null == promoNum) {
            throw new MyException("-99", "-99", "更新库存时,hotelId,roomTypeIdk,day,totalNum,promoNum必填");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            String availableHashName = RoomTypeStockCacheEnum.getAvailableHashName(String.valueOf(roomTypeId));
            String promoHashName = RoomTypeStockCacheEnum.getPromoHashName(String.valueOf(roomTypeId));

            if (totalNum.compareTo(promoNum) <= 0) {
                //
                jedis.hset(availableHashName, strDate, "0");
                jedis.hset(promoHashName, strDate, String.valueOf(totalNum));
            } else if (totalNum.compareTo(promoNum) > 0) {
                //
                jedis.hset(availableHashName, strDate, String.valueOf(totalNum - promoNum));
                jedis.hset(promoHashName, strDate, String.valueOf(promoNum));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }


    public List<StockInfoDto> getRemoteStock (Long roomTypeId, Date begin, Date end) {

        List<StockInfoDto> result = new ArrayList<StockInfoDto>();
        if (null == roomTypeId || null == begin || null == end) {
            return result;
        }

        //
        Long hotelId = this.roomTypeService.getHotelIdByRedis(roomTypeId);
        if (null == hotelId) {
            return result;
        }

        Hotel hotel = this.hotelMapper.selectByPrimaryKey(hotelId);
        if (null == hotel || HotelSourceEnum.LEZHU.getId().equals(hotel.getSourceType())) {
            return result;
        }

        //
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setBegintime(DateUtils.formatDateTime(begin, DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDateTime(end, DateUtils.FORMAT_DATE));

        //
        QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);

        if (null == response) {
            return result;
        }

        QueryStockResponse.Roominfo roominfo = response.getData();
        if (null == roominfo) {
            return result;
        }
        List<QueryStockResponse.Roomtypestocks>  roomtypestocksList = roominfo.getRoomtypestocks();
        if (null == roomtypestocksList || roomtypestocksList.isEmpty()) {
            return result;
        }

        //
        for (QueryStockResponse.Roomtypestocks roomtypestocks : roomtypestocksList) {
            int responseRoomTypeId = roomtypestocks.getRoomtypeid();
            if (roomTypeId.intValue() == responseRoomTypeId) {
                List<QueryStockResponse.Stockinfos> stockList = roomtypestocks.getStockinfos();
                for (QueryStockResponse.Stockinfos stockinfo : stockList) {
                    StockInfoDto infoDto = new StockInfoDto();
                    BeanUtils.copyProperties(stockinfo, infoDto);
                    result.add(infoDto);
                }
                return result;
            }

        }

        return result;

    }
}
