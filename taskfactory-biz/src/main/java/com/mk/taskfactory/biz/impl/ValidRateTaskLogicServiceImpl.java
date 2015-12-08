package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.mapper.RoomTypeInfoMapper;
import com.mk.taskfactory.model.TRoomTypeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private ValidRateTaskServiceImpl validRateTaskService;

    @Transactional("ots")
    public HashMap<String, Map> initSaleRoomSaleConfigDto(TRoomSaleConfigDto roomSaleConfig, HashMap<String, Map> executeRecordMap) {
        //将新roomTypeId和老的roomTypeId对应起来
        Map<Integer, Integer> roomTypeMap = executeRecordMap.get("roomTypeMap");
        Map<Integer, Integer> hotelMap = executeRecordMap.get("hotelMap");
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> begin do roomSaleConfig id : [%s]",roomSaleConfig.getId()));
        try {

            //刷新价格
            if (hotelMap.get(roomSaleConfig.getHotelId()) == null) {
                hotelMap.put(roomSaleConfig.getHotelId(), roomSaleConfig.getHotelId());
                executeRecordMap.put("hotelMap", hotelMap);
            }

//            if(roomTypeMap.get(roomSaleConfig.getRoomTypeId()) == null){
                int newRoomTypeId = initRoomTypeDto(roomSaleConfig);
            if (newRoomTypeId < 0) {
                //失败后，将vaild置为F
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> do roomSaleConfig faild set roomSaleConfig id: [%s] vaild To F1", roomSaleConfig.getId()));
                this.roomSaleConfigService.updateRoomSaleConfigValid(roomSaleConfig.getId(),ValidEnum.INVALID.getId());
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> do roomSaleConfig faild set roomSaleConfig id: [%s] vaild To F2", roomSaleConfig.getId()));
                return executeRecordMap;
            }
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> do roomSaleConfig createRoomTypeId : [%s]", newRoomTypeId));
                roomTypeMap.put(roomSaleConfig.getRoomTypeId(), newRoomTypeId);
                executeRecordMap.put("roomTypeMap", roomTypeMap);
//            }

        } catch (Exception e) {
            e.printStackTrace();
            //失败后，将vaild置为F
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> do roomSaleConfig faild set roomSaleConfig id: [%s] vaild To F1", roomSaleConfig.getId()));
            this.roomSaleConfigService.updateRoomSaleConfigValid(roomSaleConfig.getId(), ValidEnum.INVALID.getId());
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> do roomSaleConfig faild set roomSaleConfig id: [%s] vaild To F2", roomSaleConfig.getId()));
            throw new RuntimeException("initSaleRoomSaleConfigDto error");
        }

        logger.info(String.format("====================initSaleRoomSaleConfigDto >> end do roomSaleConfig id : [%s]",roomSaleConfig.getId()));
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

    @Transactional("ots")
    public int initRoomTypeDto(TRoomSaleConfigDto tRoomSaleConfigDto){
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto method begin===================="));
        Integer newRoomTypeId = null;

        try{
            TRoomTypeDto roomTypeModel=roomTypeService.findTRoomTypeById(tRoomSaleConfigDto.getRoomTypeId());
            if(roomTypeModel == null || roomTypeModel.getId() == null){
                throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto >> find TRoomTypeDto is null params roomTypeId[%s]===============", tRoomSaleConfigDto.getRoomTypeId()));
            }
            OtsRoomStateDto roomStateDto = this.roomService.getOtsRoomState(tRoomSaleConfigDto.getHotelId(),tRoomSaleConfigDto.getRoomTypeId(),null,null);
            BigDecimal mikePrice = roomStateDto.getPrice();
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto get roomSate mikePrice:[%s]",mikePrice));
            if(mikePrice == null){
                logger.info("====================initSaleRoomSaleConfigDto >> mikePrice is null");
                return -1;
            }
            //眯客价大于门市价
            BigDecimal basePrice = validRateTaskService.calaValue(
                    mikePrice, tRoomSaleConfigDto.getSaleValue(), ValueTypeEnum.getById(tRoomSaleConfigDto.getSaleType()));
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto get basePrice:[%s]", basePrice));
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto get roomTypeModel.getCost():[%s]", roomTypeModel.getCost()));
           if(basePrice == null){
               logger.info("====================initSaleRoomSaleConfigDto >> basePrice is null");
               return -1;
            }else if ( basePrice.compareTo(roomTypeModel.getCost()) > 0) {
                logger.info("====================initSaleRoomSaleConfigDto >> basePrice > roomType Cost continue");
                return -1;
            }
            //结算价大于原眯客价
            BigDecimal settleValue = validRateTaskService.calaValue(
                    mikePrice, tRoomSaleConfigDto.getSettleValue(), tRoomSaleConfigDto.getSettleType());
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto get settleValue:[%s]",settleValue));
            if (settleValue == null){
                logger.info("====================initSaleRoomSaleConfigDto >> settleValue is null");
                return -1;
            }else if (settleValue.compareTo(mikePrice) > 0) {
                logger.info("====================initSaleRoomSaleConfigDto >> settleValue > basePrice Cost continue");
                return -1;
            }

            int configRoomTypeId = tRoomSaleConfigDto.getRoomTypeId();

            TRoomSaleConfigDto param = new TRoomSaleConfigDto();
            param.setValid(ValidEnum.VALID.getId());
            param.setRoomTypeId(configRoomTypeId);
            param.setSaleRoomTypeIdIsNotNull(true);
            param.setSaleConfigInfoId(tRoomSaleConfigDto.getSaleConfigInfoId());
            List<TRoomSaleConfigDto> existsList =  this.roomSaleConfigService.queryRoomSaleConfigByParams(param);
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto get roomSaleConfig List size:[%s]",existsList.size()));

            if (existsList.isEmpty()){
                //初始化t_roomtype
                roomTypeModel.setName(tRoomSaleConfigDto.getSaleName());
                roomTypeModel.setRoomNum(tRoomSaleConfigDto.getNum());
                roomTypeService.saveTRoomType(roomTypeModel);
                newRoomTypeId = roomTypeModel.getId();
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto save new RoomType id:[%s]",newRoomTypeId));

                //初始化t_roomtype_info
                TRoomTypeInfo roomTypeInfo = roomTypeInfoMapper.findByRoomTypeId(configRoomTypeId);
                TRoomTypeInfoDto findRoomTypeInfo = new TRoomTypeInfoDto();
                 BeanUtils.copyProperties(roomTypeInfo, findRoomTypeInfo);;
                if(findRoomTypeInfo == null || findRoomTypeInfo.getId() == null){
                    throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto >> find findRoomTypeInfo is null params roomTypeId[%s]===============", configRoomTypeId));
                }
                findRoomTypeInfo.setRoomTypeId(newRoomTypeId);
                roomTypeInfoMapper.saveRoomTypeInfo(findRoomTypeInfo);
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto save new findRoomTypeInfo id:[%s]", findRoomTypeInfo.getId()));
                //初始化t_roomtype_facility
                List<TRoomTypeFacilityDto> roomTypeFacilityDtos = roomTypeFacilityService.findByRoomTypeId(configRoomTypeId);
//            if(CollectionUtils.isEmpty(roomTypeFacilityDtos)){
//                throw new RuntimeException(String.format("====================initSaleRoomSaleConfigDto >> find findRoomTypeInfo is null params roomTypeId[%s]===============", configRoomTypeId));
//            }
                for (TRoomTypeFacilityDto roomTypeFacilityDto : roomTypeFacilityDtos) {
                    roomTypeFacilityDto.setRoomTypeId(newRoomTypeId);
                    roomTypeFacilityService.saveRoomSaleConfig(roomTypeFacilityDto);
                    logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto save new roomTypeFacility id:[%s]", roomTypeFacilityDto.getId()));
                }
                //初始化t_roomtype_bed
                roomTypeBedService.createByRoomTypeId(Long.valueOf(configRoomTypeId), Long.valueOf(newRoomTypeId));
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto save new roomTypeBed"));

                //初始化t_baseprice
                TBasePriceDto basePriceDto = new TBasePriceDto();
                basePriceDto.setUpdatetime(new Date());
                basePriceDto.setRoomtypeid(newRoomTypeId.longValue());
                basePriceDto.setPrice(basePrice);
                basePriceService.saveBasePriceService(basePriceDto);
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto save new basePrice id:[%s]",basePriceDto.getId()));

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
