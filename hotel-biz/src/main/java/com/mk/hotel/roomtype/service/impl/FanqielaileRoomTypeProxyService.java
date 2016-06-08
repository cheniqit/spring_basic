package com.mk.hotel.roomtype.service.impl;


import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.roomtype.enums.BedTypeEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import com.mk.hotel.roomtype.mapper.RoomTypeStockMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.model.RoomTypeExample;
import com.mk.hotel.roomtype.model.RoomTypePrice;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/13.
 */

@Service
public class FanqielaileRoomTypeProxyService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private RoomTypePriceMapper roomTypePriceMapper;
    @Autowired
    private RoomTypeStockMapper roomTypeStockMapper;
    @Autowired
    private RoomTypeServiceImpl roomTypeService;
    @Autowired
    private RoomTypePriceServiceImpl roomTypePriceService;
    @Autowired
    private RoomTypeStockServiceImpl roomTypeStockService;

    private static Logger logger = LoggerFactory.getLogger(FanqielaileRoomTypeProxyService.class);

    public RoomType saveOrUpdateRoomType(Long hotelId, com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomType fanqieRoomType) {
        RoomType roomType = convertRoomType(hotelId,fanqieRoomType);

        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andHotelIdEqualTo(hotelId).andFangIdEqualTo(roomType.getFangId());
        List<RoomType> dbRoomTypeList = this.roomTypeMapper.selectByExample(example);

        if (dbRoomTypeList.isEmpty()) {
            this.roomTypeMapper.insert(roomType);
            //TODO facilitiesMap
        } else {
            RoomType dbRoomType = dbRoomTypeList.get(0);
            roomType.setId(dbRoomType.getId());
            roomType.setCreateBy(dbRoomType.getCreateBy());
            roomType.setCreateDate(dbRoomType.getCreateDate());

            this.roomTypeMapper.updateByPrimaryKeySelective(roomType);
            //TODO facilitiesMap
        }

        roomTypeService.updateRedisRoomType(roomType.getId(), roomType, "FanqielaileRoomTypeProxyService.saveOrUpdateRoomType");

        return roomType;
    }

    private RoomType convertRoomType(Long hotelId, com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomType fanqieRoomType){
        RoomType roomType = new RoomType();

        //
        String strArea = fanqieRoomType.getRoomArea();
        if(StringUtils.isNotBlank(strArea)) {
            try {
                roomType.setArea(new BigDecimal(strArea).intValue());
            } catch (Exception e) {
                Cat.logError(e);
            }
        }

        //大床1, 双床2,大/双床3, 单人床4,圆床 5,三张床 6, 床位  7,  榻榻米  8,
        String strBedType = fanqieRoomType.getBedTypeValue();
        if(StringUtils.isNotBlank(strBedType)){
            BedTypeEnum bedTypeEnum = BedTypeEnum.getByFanqieType(strBedType);
            roomType.setBedType(bedTypeEnum.getId());
        }

        roomType.setBedSize(String.valueOf(fanqieRoomType.getBedWid()));

        //TODO 待 设施解析
        roomType.setBreakfast(0);
        roomType.setFangId(fanqieRoomType.getRoomTypeId().longValue());
        roomType.setHotelId(hotelId);
        roomType.setMaxRoomNum(8);
        roomType.setName(fanqieRoomType.getRoomTypeName());

        //1、预付
        roomType.setPrepay(1);
        roomType.setRoomNum(0);
        roomType.setRoomTypePics("");


        roomType.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomType.setUpdateDate(new Date());
        roomType.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomType.setCreateDate(new Date());
        roomType.setIsValid(ValidEnum.VALID.getCode());
        return roomType;
    }

//    private RoomTypePrice convertRoomTypePrice(Long roomTypeId, HotelPriceResponse.Priceinfos priceinfo) throws ParseException {
//        RoomTypePrice roomTypePrice = new RoomTypePrice();
//        roomTypePrice.setRoomTypeId(roomTypeId);
//        roomTypePrice.setDay(DateUtils.parseDate(priceinfo.getDate(), DateUtils.FORMAT_DATE));
//        BigDecimal price = new BigDecimal(priceinfo.getPrice());
//        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
//        roomTypePrice.setPrice(price);
//        BigDecimal cost = new BigDecimal(priceinfo.getCost());
//        cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
//        roomTypePrice.setCost(cost);
//
//        roomTypePrice.setUpdateBy(Constant.SYSTEM_USER_NAME);
//        roomTypePrice.setUpdateDate(new Date());
//        roomTypePrice.setCreateBy(Constant.SYSTEM_USER_NAME);
//        roomTypePrice.setCreateDate(new Date());
//        roomTypePrice.setIsValid(ValidEnum.VALID.getCode());
//        return roomTypePrice;
//    }

}
