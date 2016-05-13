package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.enums.RoomTypePriceCacheEnum;
import com.mk.hotel.roomtype.enums.RoomTypeStockCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.model.RoomTypeExample;
import com.mk.hotel.roomtype.model.RoomTypePrice;
import com.mk.hotel.roomtype.model.RoomTypePriceExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RoomTypePriceServiceImpl implements RoomTypePriceService {

    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomTypePriceMapper roomTypePriceMapper;

    public int saveOrUpdateByFangId(List<RoomTypePriceDto> roomTypePriceDtoList) {

        for (RoomTypePriceDto dto : roomTypePriceDtoList) {
            this.saveOrUpdateByFangId(dto);
        }

        return 1;
    }

    public int saveOrUpdateByFangId(RoomTypePriceDto roomTypePriceDto) {

        if (null == roomTypePriceDto) {
            throw new MyException("-99", "-99", "roomTypePriceDto 不可为空");
        }

        //roomType
        RoomTypeDto roomTypeDto =
                this.roomTypeService.selectByFangId(
                        roomTypePriceDto.getFangHotelId(), roomTypePriceDto.getFangRoomTypeId());
        if (null == roomTypeDto) {
            return -1;
        }
        //
        RoomTypePriceExample roomTypePriceExample = new RoomTypePriceExample();
        roomTypePriceExample.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId()).andDayEqualTo(roomTypePriceDto.getDay());
        List<RoomTypePrice> roomTypePriceList = this.roomTypePriceMapper.selectByExample(roomTypePriceExample);

        if (roomTypePriceList.isEmpty()) {
            RoomTypePrice roomTypePrice = new RoomTypePrice();
            roomTypePrice.setRoomTypeId(roomTypeDto.getId());
            roomTypePrice.setDay(roomTypePriceDto.getDay());
            roomTypePrice.setPrice(roomTypePriceDto.getPrice());
            roomTypePrice.setIsValid("T");
            return this.roomTypePriceMapper.insert(roomTypePrice);

        } else {
            RoomTypePrice dbRoomTypePrice = roomTypePriceList.get(0);
            dbRoomTypePrice.setPrice(roomTypePriceDto.getPrice());

            return this.roomTypePriceMapper.updateByPrimaryKeySelective(dbRoomTypePrice);
        }
    }

    public void updatePrice(String roomTypeId, Date day, BigDecimal price) {
        if (StringUtils.isBlank(roomTypeId) || null == day || null == price) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(day);

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
            String priceHashName = RoomTypePriceCacheEnum.getPriceHashName(roomTypeId);
            jedis.hset(priceHashName, strDate, String.valueOf(price));
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }
}
