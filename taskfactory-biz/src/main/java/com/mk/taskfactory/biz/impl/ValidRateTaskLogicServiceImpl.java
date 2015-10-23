package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.biz.mapper.RoomTypeInfoMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.model.TRoomTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Thinkpad on 2015/10/18.
 */
@Service
public class ValidRateTaskLogicServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(ValidRateTaskLogicServiceImpl.class);

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomSaleConfigService roomSaleConfigService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;
    @Autowired
    private BasePriceService basePriceService;
    @Autowired
    private RoomTypeInfoMapper roomTypeInfoMapper;
    @Autowired
    private RoomTypeBedServiceImpl roomTypeBedService;

    @Transactional
    public HashMap<String, Map> initSaleRoomSaleConfigDto(TRoomSaleConfigDto roomSaleConfig, HashMap<String, Map> executeRecordMap) {
        //将新roomTypeId和老的roomTypeId对应起来
        Map<Integer, Integer> roomTypeMap = executeRecordMap.get("roomTypeMap");
        Map<Integer, Integer> hotelMap = executeRecordMap.get("hotelMap");
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> find data begin===================="));
        try {

            logger.info(String.format("====================initSaleRoomSaleConfigDto >> find data end===================="));
            if(roomTypeMap.get(roomSaleConfig.getRoomTypeId()) == null){
                int newRoomTypeId = initRoomTypeDto(roomSaleConfig);
                roomTypeMap.put(roomSaleConfig.getRoomTypeId(), newRoomTypeId);
                executeRecordMap.put("roomTypeMap", roomTypeMap);
            }
            //刷新价格
            if (hotelMap.get(roomSaleConfig.getHotelId()) == null) {
                hotelMap.put(roomSaleConfig.getHotelId(), roomSaleConfig.getHotelId());
                executeRecordMap.put("hotelMap", hotelMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("initSaleRoomSaleConfigDto error");
        }
        return executeRecordMap;
    }

    private void saveBasePrice(TRoomSaleConfigDto tRoomSaleConfigDto, TBasePriceDto tBasePriceDto){
        BigDecimal price = figureBasePrice(tRoomSaleConfigDto);
        tBasePriceDto.setPrice(price);
        basePriceService.saveBasePriceService(tBasePriceDto);
    }

    private void updateBasePrice(TRoomSaleConfigDto tRoomSaleConfigDto, TBasePriceDto tBasePriceDto){
        BigDecimal price = figureBasePrice(tRoomSaleConfigDto);
        tBasePriceDto.setPrice(price);
        basePriceService.updateBasePriceService(tBasePriceDto);
    }

    private BigDecimal figureBasePrice(TRoomSaleConfigDto tRoomSaleConfigDto){
        BigDecimal price = new BigDecimal(0);
        if(tRoomSaleConfigDto == null){
            return price;
        }
        if(ValueTypeEnum.TYPE_TO.getId() == tRoomSaleConfigDto.getSaleType()){
            price = tRoomSaleConfigDto.getSaleValue();
        }else if(ValueTypeEnum.TYPE_ADD.getId() == tRoomSaleConfigDto.getSaleType()) {
            //得到房型价格
            OtsRoomStateDto otsRoomStateDto = roomService.getOtsRoomState(tRoomSaleConfigDto.getHotelId(), tRoomSaleConfigDto.getRoomTypeId(), new Date(), new Date());
            BigDecimal roomTypePrice = otsRoomStateDto.getPrice();
            if(roomTypePrice == null){
                throw new RuntimeException(String.format("saveBasePriceService roomTypePrice id is null >> tRoomSaleConfigDto HotelId[%s]", tRoomSaleConfigDto.getHotelId()));
            }
            price = roomTypePrice.subtract(tRoomSaleConfigDto.getSaleValue());
            price = price.compareTo(new BigDecimal(0)) < 1 ? new BigDecimal(0) : price;
        }else if(ValueTypeEnum.TYPE_OFF.getId() == tRoomSaleConfigDto.getSaleType()) {
            //得到房型价格
            OtsRoomStateDto otsRoomStateDto = roomService.getOtsRoomState(tRoomSaleConfigDto.getHotelId(), tRoomSaleConfigDto.getRoomTypeId(),new Date(), new Date());
            BigDecimal roomTypePrice = otsRoomStateDto.getPrice();
            if(roomTypePrice == null){
                throw new RuntimeException(String.format("saveBasePriceService roomTypePrice id is null >> tRoomSaleConfigDto HotelId[%s]", tRoomSaleConfigDto.getHotelId()));
            }
            price = roomTypePrice.multiply(tRoomSaleConfigDto.getSaleValue().divide(new BigDecimal(100)));
            price = price.compareTo(new BigDecimal(0)) < 1 ? new BigDecimal(0) : price;
        }
        //四舍五入处理
        price = price.setScale(0,BigDecimal.ROUND_HALF_UP);
        return price;
    }

    @Transactional
    public int initRoomTypeDto(TRoomSaleConfigDto tRoomSaleConfigDto){
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto method begin===================="));
        Integer newRoomTypeId = null;
        try{
            TRoomTypeDto roomTypeModel=roomTypeService.findTRoomTypeById(tRoomSaleConfigDto.getRoomTypeId());
            if(roomTypeModel == null || roomTypeModel.getId() == null){
                throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto >> find TRoomTypeDto is null params roomTypeId[%s]===============", tRoomSaleConfigDto.getRoomTypeId()));
            }
            int configRoomTypeId = tRoomSaleConfigDto.getRoomTypeId();
            //初始化t_roomtype
            roomTypeModel.setCost(roomTypeModel.getCost());
            roomTypeModel.setName(tRoomSaleConfigDto.getSaleName());
            roomTypeModel.setRoomNum(tRoomSaleConfigDto.getNum());
            roomTypeService.saveTRoomType(roomTypeModel);
            newRoomTypeId = roomTypeModel.getId();
            //初始化t_roomtype_info
            TRoomTypeInfoDto findRoomTypeInfo = roomTypeInfoMapper.findByRoomTypeId(configRoomTypeId);
            if(findRoomTypeInfo == null || findRoomTypeInfo.getId() == null){
                throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto >> find findRoomTypeInfo is null params roomTypeId[%s]===============", configRoomTypeId));
            }
            findRoomTypeInfo.setRoomTypeId(newRoomTypeId);
            roomTypeInfoMapper.saveRoomTypeInfo(findRoomTypeInfo);
            //初始化t_roomtype_facility
            List<TRoomTypeFacilityDto> roomTypeFacilityDtos = roomTypeFacilityService.findByRoomTypeId(configRoomTypeId);
            if(CollectionUtils.isEmpty(roomTypeFacilityDtos)){
                throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto >> find findRoomTypeInfo is null params roomTypeId[%s]===============", configRoomTypeId));
            }
            for (TRoomTypeFacilityDto roomTypeFacilityDto : roomTypeFacilityDtos) {
                roomTypeFacilityDto.setRoomTypeId(newRoomTypeId);
                roomTypeFacilityService.saveRoomSaleConfig(roomTypeFacilityDto);
            }
            //初始化t_roomtype_bed
            roomTypeBedService.createByRoomTypeId(Long.valueOf(configRoomTypeId), Long.valueOf(newRoomTypeId));
            //初始化t_baseprice
            //先查找新房间类型的价格是否有，如果有则只需要更新
            TBasePriceDto newBasePriceDto = basePriceService.findByRoomtypeId(newRoomTypeId.longValue());
            if(newBasePriceDto == null){
                TBasePriceDto oldBasePriceDto = basePriceService.findByRoomtypeId(new Long(configRoomTypeId));
                //根据配置文件的价格规则算出价格设置到base Price表中
                if(oldBasePriceDto == null || oldBasePriceDto.getId() == null){
                    throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto  >> find tBasePriceDto id is null roomTypeId[%s]", configRoomTypeId));
                }
                oldBasePriceDto.setRoomtypeid(newRoomTypeId.longValue());
                saveBasePrice(tRoomSaleConfigDto, oldBasePriceDto);
            }else {
                updateBasePrice(tRoomSaleConfigDto, newBasePriceDto);
            }
            //回填config信息
            tRoomSaleConfigDto.setSaleRoomTypeId(newRoomTypeId);
            roomSaleConfigService.updateRoomSaleConfig(tRoomSaleConfigDto);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("initSaleRoomSaleConfigDto error");
        }
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto method end===================="));
        return newRoomTypeId;
    }
}
