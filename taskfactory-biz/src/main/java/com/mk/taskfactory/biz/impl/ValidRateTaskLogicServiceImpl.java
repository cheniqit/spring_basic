package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.biz.utils.DateUtils;
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
    private RoomSettingService roomSettingService;
    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;
    @Autowired
    private RoomSaleService roomSaleService;
    @Autowired
    private BasePriceService basePriceService;
    @Autowired
    private HotelRemoteService hotelRemoteService;

    @Transactional
    public HashMap<String, Map> initSaleRoomSaleConfigDto(TRoomSaleConfigDto roomSaleConfig, HashMap<String, Map> executeRecordMap) {
        //将新roomTypeId和老的roomTypeId对应起来
        Map<Integer, Integer> roomTypeMap = executeRecordMap.get("roomTypeMap");
        Map<Integer, Integer> hotelMap = executeRecordMap.get("hotelMap");
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> find data begin===================="));
        try {
            TRoomTypeDto roomTypeDto = new TRoomTypeDto();
            //如果房型不存在map中，则将房型put到map�?
            roomTypeDto.setId(roomSaleConfig.getRoomTypeId());
            roomTypeDto.setCost(roomSaleConfig.getSaleValue());
            roomTypeDto.setName(roomSaleConfig.getSaleName());
            roomTypeDto.setRoomNum(0);
            logger.info(String.format("====================initSaleRoomSaleConfigDto >> find data end===================="));
            if(roomTypeMap.get(roomTypeDto.getId()) == null){
                int newRoomTypeId = initRoomTypeDto(roomSaleConfig, roomTypeDto);
                roomTypeMap.put(roomTypeDto.getId(), newRoomTypeId);
                executeRecordMap.put("roomTypeMap", roomTypeMap);
            }
            //刷新价格
            if (hotelMap.get(roomSaleConfig.getHotelId()) == null) {
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> updateMikePriceCache begin===================="));
                boolean updateCacheSuccessFlag = hotelRemoteService.updateMikePriceCache(roomSaleConfig.getHotelId().toString());
                if (updateCacheSuccessFlag) {
                    hotelMap.put(roomSaleConfig.getHotelId(), roomSaleConfig.getHotelId());
                    executeRecordMap.put("hotelMap", hotelMap);
                } else {
                    throw new RuntimeException("updateMikePriceCache error");
                }
                logger.info(String.format("====================initSaleRoomSaleConfigDto >> updateMikePriceCache end updateCacheSuccessFlag[%s]==========", String.valueOf(updateCacheSuccessFlag)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("initSaleRoomSaleConfigDto error");
        }
        return executeRecordMap;
    }

    private void saveBasePrice(TRoomSaleConfigDto tRoomSaleConfigDto, TBasePriceDto tBasePriceDto){
        saveOrUpdateBasePrice(tRoomSaleConfigDto, tBasePriceDto, false);
    }

    private void updateBasePrice(TRoomSaleConfigDto tRoomSaleConfigDto, TBasePriceDto tBasePriceDto){
        saveOrUpdateBasePrice(tRoomSaleConfigDto,tBasePriceDto, true);
    }

    private void saveOrUpdateBasePrice(TRoomSaleConfigDto tRoomSaleConfigDto, TBasePriceDto tBasePriceDto, boolean isUpdate) {
        if(tRoomSaleConfigDto == null){
            return;
        }
        if(ValueTypeEnum.TYPE_TO.getId() == tRoomSaleConfigDto.getType()){
            tBasePriceDto.setPrice(tRoomSaleConfigDto.getSaleValue());
        }else if(ValueTypeEnum.TYPE_ADD.getId() == tRoomSaleConfigDto.getType()) {
            //得到房型价格
            OtsRoomStateDto otsRoomStateDto = roomService.getOtsRoomState(tRoomSaleConfigDto.getHotelId(), tRoomSaleConfigDto.getRoomTypeId(),new Date(), new Date());
            BigDecimal roomTypePrice = otsRoomStateDto.getPmsPrice();
            if(roomTypePrice == null){
                throw new RuntimeException(String.format("saveBasePriceService roomTypePrice id is null >> tRoomSaleConfigDto HotelId[%s]", tRoomSaleConfigDto.getHotelId()));
            }
            BigDecimal price = roomTypePrice.subtract(tRoomSaleConfigDto.getSaleValue());
            tBasePriceDto.setPrice(price.compareTo(new BigDecimal(0)) < 1 ? new BigDecimal(0) : price);
        }else if(ValueTypeEnum.TYPE_OFF.getId() == tRoomSaleConfigDto.getType()) {
            //得到房型价格
            OtsRoomStateDto otsRoomStateDto = roomService.getOtsRoomState(tRoomSaleConfigDto.getHotelId(), tRoomSaleConfigDto.getRoomTypeId(),new Date(), new Date());
            BigDecimal roomTypePrice = otsRoomStateDto.getPmsPrice();
            if(roomTypePrice == null){
                throw new RuntimeException(String.format("saveBasePriceService roomTypePrice id is null >> tRoomSaleConfigDto HotelId[%s]", tRoomSaleConfigDto.getHotelId()));
            }
            BigDecimal price = roomTypePrice.multiply(tRoomSaleConfigDto.getSaleValue().divide(new BigDecimal(100)));
            tBasePriceDto.setPrice(price.compareTo(new BigDecimal(0)) < 1 ? new BigDecimal(0) : price);
        }
        if(isUpdate){
            basePriceService.updateBasePriceService(tBasePriceDto);
        }else{
            basePriceService.saveBasePriceService(tBasePriceDto);
        }

    }

    @Transactional
    public int initRoomTypeDto(TRoomSaleConfigDto tRoomSaleConfigDto, TRoomTypeDto roomTypeDto){
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto method begin===================="));
        Integer newRoomTypeId = null;
        try{
            TRoomTypeDto roomTypeModel=roomTypeService.findTRoomTypeById(roomTypeDto.getId());
            //将原价格存起�?
            roomTypeModel.setRoomNum(roomTypeDto.getRoomNum());
            roomTypeModel.setCost(roomTypeDto.getCost());
            roomTypeModel.setName(roomTypeDto.getName());
            //复制并创建活动房�?
            roomTypeService.saveTRoomType(roomTypeModel);
            newRoomTypeId = roomTypeModel.getId();
            //得到房价对应配置信息
            List<TRoomTypeFacilityDto> roomTypeFacilityDtos = roomTypeFacilityService.findByRoomTypeId(roomTypeDto.getId());
            for (TRoomTypeFacilityDto roomTypeFacilityDto : roomTypeFacilityDtos) {
                roomTypeFacilityDto.setRoomTypeId(newRoomTypeId);
                roomTypeFacilityService.saveRoomSaleConfig(roomTypeFacilityDto);
            }
            //处理价格
            //先查找新房间类型的价格是否有，如果有则只需要更新
            TBasePriceDto newBasePriceDto = basePriceService.findByRoomtypeId(newRoomTypeId.longValue());
            if(newBasePriceDto == null){
                TBasePriceDto oldBasePriceDto = basePriceService.findByRoomtypeId(new Long(roomTypeDto.getId()));
                //根据配置文件的价格规则算出价格设置到baseprice表中
                if(oldBasePriceDto == null || oldBasePriceDto.getId() == null){
                    throw new RuntimeException(String.format("saveBasePriceService tBasePriceDto id is null >> tRoomSaleConfigDto roomTypeId[%s]", tRoomSaleConfigDto.getRoomTypeId()));
                }
                oldBasePriceDto.setRoomtypeid(newRoomTypeId.longValue());
                saveBasePrice(tRoomSaleConfigDto, oldBasePriceDto);
            }else {
                updateBasePrice(tRoomSaleConfigDto, newBasePriceDto);
            }

            //回填
            tRoomSaleConfigDto.setSaleRoomTypeId(newRoomTypeId);
            roomSaleConfigService.updateRoomSaleConfig(tRoomSaleConfigDto);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("initSaleRoomSaleConfigDto error");
        }
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomTypeDto method end===================="));
        return newRoomTypeId;
    }

    @Transactional
    public void initRoomSaleDto(TRoomSaleConfigDto roomSaleConfig,TRoomTypeDto roomTypeDto , List<TRoomSaleDto> tRoomDtoList, Map<Integer,Integer>  roomTypeMap){
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomSaleDto method begin===================="));
        if(CollectionUtils.isEmpty(tRoomDtoList)){
            return;
        }
        for(TRoomSaleDto roomDto : tRoomDtoList){
            try{
                Integer newRoomTypeId = roomTypeMap.get(roomDto.getOldRoomTypeId());
                Integer oldRoomTypeId = roomDto.getOldRoomTypeId();
                if (newRoomTypeId == null) {
                    logger.info(String.format("initRoomSaleDto ----没有生成新房间类型---- oldRoomTypeID[%s]", oldRoomTypeId));
                    continue;
                }
                TRoomSettingDto roomSettingBean = new TRoomSettingDto();
                roomSettingBean.setRoomTypeId(oldRoomTypeId);
                roomSettingBean.setRoomNo(roomDto.getRoomNo());
                //取得房间配置信息
                TRoomSettingDto roomSetting = roomSettingService.selectByRoomTypeIdAndRoomNo(roomSettingBean);
                if(roomSetting == null || roomSetting.getId() == null){
                    throw new RuntimeException(String.format("find roomSetting si null oldRoomTypeId[%s] roomNo[%s]", oldRoomTypeId, roomDto.getRoomNo()));
                }
                roomSetting.setRoomTypeId(newRoomTypeId);
                //更新房间配置信息
                roomSettingService.updateTRoomSetting(roomSetting);
                //更新房间信息
                TRoomDto room = roomService.findRoomsById(roomDto.getRoomId());
                room.setRoomTypeId(newRoomTypeId);
                roomService.saveTRoom(room);
                //保存今日特价房间信息
                roomDto.setCreateDate(DateUtils.format_yMd(new Date()));
                roomDto.setCostPrice(roomTypeDto.getCost());
                roomDto.setRoomTypeId(newRoomTypeId);
                roomDto.setSalePrice(roomSaleConfig.getSaleValue());
                roomDto.setIsBack("F");
                roomSaleService.saveRoomSale(roomDto);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("initSaleRoomSaleConfigDto error");
            }
        }
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> initRoomSaleDto method end===================="));
    }
}
