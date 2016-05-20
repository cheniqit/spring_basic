package com.mk.hotel.roomtype.service.impl;

import com.mk.framework.Constant;
import com.mk.framework.DateUtils;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.remote.pms.hotel.json.HotelPriceResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelRoomTypeQueryResponse;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import com.mk.hotel.roomtype.mapper.RoomTypeStockMapper;
import com.mk.hotel.roomtype.model.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/13.
 */

@Service
public class RoomTypeProxyService {
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

    private static Logger logger = LoggerFactory.getLogger(RoomTypeProxyService.class);

    @Transactional
    public void saveRoomType(Hotel hotel, List<HotelRoomTypeQueryResponse.HotelRoomType> roomTypeList) {
        if(CollectionUtils.isEmpty(roomTypeList)){
            return;
        }
        for(HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType : roomTypeList){
            RoomTypeDto roomTypeDto = roomTypeService.selectByFangId(Long.valueOf(hotelRoomType.getId()+""));
            RoomType roomType = null;
            if(roomTypeDto == null || roomTypeDto.getId() == null){
                roomType = saveRoomType(hotel, hotelRoomType);
            }else{
                roomType = updateRoomType(roomTypeDto.getId() ,hotel, hotelRoomType);
            }
            roomTypeService.updateRedisRoomType(roomType.getId(), roomType, "RoomTypeProxyService.saveRoomType");
        }
    }

    public RoomType saveRoomType(Hotel hotel, HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType){
        RoomType roomType = convertRoomType(hotel, hotelRoomType);
        roomTypeMapper.insertSelective(roomType);
        return roomType;
    }

    public RoomType updateRoomType(Long id ,Hotel hotel, HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType){
        RoomType roomType = convertRoomType(hotel, hotelRoomType);
        roomType.setId(id);
        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andIdEqualTo(id);
        roomTypeMapper.updateByExampleSelective(roomType, example);
        return roomType;
    }


    private RoomType convertRoomType(Hotel hotel, HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType){
        RoomType roomType = new RoomType();
        if(StringUtils.isNotBlank(hotelRoomType.getArea())) {
            roomType.setArea(Integer.valueOf(hotelRoomType.getArea()));
        }
        if(StringUtils.isNotBlank(hotelRoomType.getBedtype())){
            roomType.setBedType(Integer.valueOf(hotelRoomType.getBedtype()));
        }

        roomType.setBedSize(hotelRoomType.getBedsize());
        if(StringUtils.isNotBlank(hotelRoomType.getBreakfast())) {
            roomType.setBreakfast(Integer.valueOf(hotelRoomType.getBreakfast()));
        }
        roomType.setFangId(Long.valueOf(hotelRoomType.getId()+""));
        roomType.setHotelId(hotel.getId());
        roomType.setMaxRoomNum(Integer.valueOf(hotelRoomType.getRoomnum()));
        roomType.setName(hotelRoomType.getName());
        if(StringUtils.isNotBlank(hotelRoomType.getPrepay())) {
            roomType.setPrepay(Integer.valueOf(hotelRoomType.getPrepay()));
        }
        roomType.setRoomNum(hotelRoomType.getRoomnum());
        roomType.setRoomTypePics(hotelRoomType.getRoomtypepics());


        roomType.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomType.setUpdateDate(new Date());
        roomType.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomType.setCreateDate(new Date());
        roomType.setIsValid(ValidEnum.VALID.getCode());
        return roomType;
    }

    @Transactional
    public void saveRoomTypePrice(HotelPriceResponse.HotelPrice data) {
        if(data == null || CollectionUtils.isEmpty(data.getRoomtypeprices())){
            return;
        }
        for(HotelPriceResponse.Roomtypeprices roomPriceType : data.getRoomtypeprices()){
            RoomTypeDto roomTypeDto = roomTypeService.selectByFangId(Long.valueOf(roomPriceType.getRoomtypeid()+""));
            if(roomTypeDto == null){
                logger.info("by roomTypeId  {} is not find roomType data", roomPriceType.getRoomtypeid()+"");
                return;
            }
            for(HotelPriceResponse.Priceinfos priceinfo : roomPriceType.getPriceinfos()){
                RoomTypePriceExample example = new RoomTypePriceExample();
                try {
                    example.createCriteria()
                            .andDayEqualTo(DateUtils.parseDate(priceinfo.getDate(), DateUtils.FORMAT_DATE))
                            .andRoomTypeIdEqualTo(Long.valueOf(roomTypeDto.getId()+""));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<RoomTypePrice> roomTypePriceList = roomTypePriceMapper.selectByExample(example);
                RoomTypePrice roomTypePrice = null;
                if(CollectionUtils.isEmpty(roomTypePriceList)){
                    roomTypePrice = saveRoomTypePrice(roomTypeDto.getId(), priceinfo);
                }else{
                    RoomTypePrice typePrice = roomTypePriceList.get(0);
                    roomTypePrice = updateRoomTypePrice(typePrice, priceinfo);
                }

                roomTypePriceService.updateRedisPrice(
                        roomTypeDto.getId(), roomTypeDto.getName(),
                        roomTypePrice.getDay(), roomTypePrice.getPrice(),
                        "RoomTypeProxyService.saveRoomTypePrice");

            }

        }
    }

    private RoomTypePrice updateRoomTypePrice(RoomTypePrice roomTypePrice,HotelPriceResponse.Priceinfos priceinfo) {
        try {
            RoomTypePrice record = convertRoomTypePrice(roomTypePrice.getRoomTypeId(), priceinfo);
            record.setId(roomTypePrice.getId());
            RoomTypePriceExample example = new RoomTypePriceExample();
            example.createCriteria().andRoomTypeIdEqualTo(roomTypePrice.getRoomTypeId()).
                    andDayEqualTo(DateUtils.parseDate(priceinfo.getDate(), DateUtils.FORMAT_DATE));
            roomTypePriceMapper.updateByExampleSelective(record, example);
            return record;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RoomTypePrice saveRoomTypePrice(Long roomTypeId, HotelPriceResponse.Priceinfos priceinfo) {
        try {
            RoomTypePrice record = convertRoomTypePrice(roomTypeId, priceinfo);
            roomTypePriceMapper.insertSelective(record);
            return record;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RoomTypePrice convertRoomTypePrice(Long roomTypeId, HotelPriceResponse.Priceinfos priceinfo) throws ParseException {
        RoomTypePrice roomTypePrice = new RoomTypePrice();
        roomTypePrice.setRoomTypeId(roomTypeId);
        roomTypePrice.setDay(DateUtils.parseDate(priceinfo.getDate(), DateUtils.FORMAT_DATE));
        roomTypePrice.setPrice(new BigDecimal(priceinfo.getCost()));

        roomTypePrice.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomTypePrice.setUpdateDate(new Date());
        roomTypePrice.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomTypePrice.setCreateDate(new Date());
        roomTypePrice.setIsValid(ValidEnum.VALID.getCode());
        return roomTypePrice;
    }

    public void saveRoomTypeStock(Hotel hotel, QueryStockResponse.Roominfo data) {
        if(data == null || CollectionUtils.isEmpty(data.getRoomtypestocks())){
            return;
        }
        for(QueryStockResponse.Roomtypestocks roomtypestock :  data.getRoomtypestocks()){
            RoomTypeDto roomTypeDto = roomTypeService.selectByFangId(Long.valueOf(roomtypestock.getRoomtypeid()));
            if(roomTypeDto == null){
                logger.info("by roomTypeId  {} is not find roomType data", roomtypestock.getRoomtypeid());
                return;
            }
            for(QueryStockResponse.Stockinfos stockInfo : roomtypestock.getStockinfos()){
                RoomTypeStockExample example = new RoomTypeStockExample();
                try {
                    example.createCriteria()
                            .andRoomTypeIdEqualTo(Long.valueOf(roomTypeDto.getId()))
                            .andDayEqualTo(DateUtils.parseDate(stockInfo.getDate(), DateUtils.FORMAT_DATE));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<RoomTypeStock> roomTypePriceList = roomTypeStockMapper.selectByExample(example);
                RoomTypeStock typeStock = null;
                if(CollectionUtils.isEmpty(roomTypePriceList)){
                    typeStock = saveRoomTypeStock(roomTypeDto.getId(), stockInfo);
                }else{
                    typeStock = updateRoomTypeStock(roomTypeDto.getId(), stockInfo);
                }
                roomTypeStockService.updateRedisStock(hotel.getId().toString(), roomTypeDto.getId().toString(), typeStock.getDay(), typeStock.getNumber().intValue());
            }
        }
    }

    private RoomTypeStock updateRoomTypeStock(Long roomTypeId, QueryStockResponse.Stockinfos stockInfo) {
        try {
            RoomTypeStock record = convertRoomTypeStock(roomTypeId, stockInfo);
            record.setId(roomTypeId);
            RoomTypeStockExample example = new RoomTypeStockExample();
            example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayEqualTo(DateUtils.parseDate(stockInfo.getDate(), DateUtils.FORMAT_DATE));
            roomTypeStockMapper.updateByExampleSelective(record, example);
            return record;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RoomTypeStock saveRoomTypeStock(Long roomTypeId, QueryStockResponse.Stockinfos stockInfo) {
        RoomTypeStock record = null;
        try {
            record = convertRoomTypeStock(roomTypeId, stockInfo);
            roomTypeStockMapper.insertSelective(record);
            return record;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private RoomTypeStock convertRoomTypeStock(Long roomTypeId, QueryStockResponse.Stockinfos stockInfo) throws ParseException {
        RoomTypeStock roomTypeStock = new RoomTypeStock();
        roomTypeStock.setDay(DateUtils.parseDate(stockInfo.getDate(), DateUtils.FORMAT_DATE));
        roomTypeStock.setRoomTypeId(roomTypeId);
        roomTypeStock.setNumber(Long.valueOf(stockInfo.getNum()));

        roomTypeStock.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomTypeStock.setUpdateDate(new Date());
        roomTypeStock.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomTypeStock.setCreateDate(new Date());
        roomTypeStock.setIsValid(ValidEnum.VALID.getCode());

        return roomTypeStock;
    }
}
