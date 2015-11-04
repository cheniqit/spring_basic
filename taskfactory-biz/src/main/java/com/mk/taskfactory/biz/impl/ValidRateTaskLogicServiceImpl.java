package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.mapper.RoomTypeInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//            if(roomTypeMap.get(roomSaleConfig.getRoomTypeId()) == null){
                int newRoomTypeId = initRoomTypeDto(roomSaleConfig);
            if (newRoomTypeId < 0) {
                return executeRecordMap;
            }
                roomTypeMap.put(roomSaleConfig.getRoomTypeId(), newRoomTypeId);
                executeRecordMap.put("roomTypeMap", roomTypeMap);
//            }
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
            //
            BigDecimal basePrice = figureBasePrice(tRoomSaleConfigDto);
            if (basePrice.compareTo(roomTypeModel.getCost()) > 0) {
                logger.info("====================initSaleRoomSaleConfigDto >> basePrice > roomType Cost continue");
                return -1;
            }

            int configRoomTypeId = tRoomSaleConfigDto.getRoomTypeId();

            TRoomSaleConfigDto param = new TRoomSaleConfigDto();
            param.setValid(ValidEnum.VALID.getId());
            param.setRoomTypeId(configRoomTypeId);
            param.setSaleRoomTypeIdIsNotNull(true);
            param.setSaleConfigInfoId(tRoomSaleConfigDto.getSaleConfigInfoId());
            List<TRoomSaleConfigDto> existsList =  this.roomSaleConfigService.queryRoomSaleConfigByParams(param);

            if (existsList.isEmpty()){
                //初始化t_roomtype
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
//            if(CollectionUtils.isEmpty(roomTypeFacilityDtos)){
//                throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto >> find findRoomTypeInfo is null params roomTypeId[%s]===============", configRoomTypeId));
//            }
                for (TRoomTypeFacilityDto roomTypeFacilityDto : roomTypeFacilityDtos) {
                    roomTypeFacilityDto.setRoomTypeId(newRoomTypeId);
                    roomTypeFacilityService.saveRoomSaleConfig(roomTypeFacilityDto);
                }
                //初始化t_roomtype_bed
                roomTypeBedService.createByRoomTypeId(Long.valueOf(configRoomTypeId), Long.valueOf(newRoomTypeId));
                //初始化t_baseprice
                TBasePriceDto basePriceDto = new TBasePriceDto();
                basePriceDto.setUpdatetime(new Date());
                basePriceDto.setRoomtypeid(newRoomTypeId.longValue());
                BigDecimal price = figureBasePrice(tRoomSaleConfigDto);
                basePriceDto.setPrice(price);
                basePriceService.saveBasePriceService(basePriceDto);

            } else {

                TRoomSaleConfigDto dto = existsList.get(0);
                newRoomTypeId = dto.getSaleRoomTypeId();
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
