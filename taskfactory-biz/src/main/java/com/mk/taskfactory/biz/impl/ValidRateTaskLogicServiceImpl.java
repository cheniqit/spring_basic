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
    private RoomTypeInfoService roomTypeInfoService;
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
    public  HashMap<String,Map> initSaleRoomSaleConfigDto(TRoomSaleConfigDto roomSaleConfig, HashMap<String,Map> executeRecordMap){
        //将新roomTypeId和老的roomTypeId对应起来
        Map<Integer,Integer> roomTypeMap = executeRecordMap.get("roomTypeMap");
        Map<Integer,Integer> hotelMap = executeRecordMap.get("hotelMap");
        logger.info(String.format("====================init sales config job >> find data begin===================="));
        try{
            TRoomTypeDto roomTypeDto=new TRoomTypeDto();
            List<TRoomSaleDto> tRoomSaleDtoList = new ArrayList<TRoomSaleDto>();
            //如果房型不存在map中，则将房型put到map�?
            roomTypeDto.setId(roomSaleConfig.getRoomTypeId());
            roomTypeDto.setCost(roomSaleConfig.getSaleValue());
            roomTypeDto.setName(roomSaleConfig.getSaleName());
            //取得当前房型做活动的库存�?
            int saleNum= 0;
            //如果配置文件中roomId存在则直接将其加到活动列表中
            if(roomSaleConfig.getRoomId()!=null&&roomSaleConfig.getRoomId()!=0){
                TRoomDto room= roomService.findRoomsById(roomSaleConfig.getRoomId());
                if (room!=null&&"F".equals(room.getIsLock())){//判断房间是否上锁
                    TRoomSaleDto roomSale=new TRoomSaleDto();
                    roomSale.setOldRoomTypeId(room.getRoomTypeId());
                    roomSale.setRoomId(room.getId());
                    roomSale.setRoomNo(room.getName());
                    roomSale.setPms(room.getPms());
                    roomSale.setSalePrice(roomSaleConfig.getSaleValue());
                    roomSale.setStartTime(String.valueOf(roomSaleConfig.getStartTime()));
                    roomSale.setEndTime(String.valueOf(roomSaleConfig.getEndDate()));
                    roomSale.setConfigId(roomSaleConfig.getId());
                    roomSale.setSaleName(roomSaleConfig.getSaleName());
                    roomSale.setSaleType(roomSaleConfig.getStyleType());
                    tRoomSaleDtoList.add(roomSale);
                    saleNum++;
                }
            }else{
                //按房型取得对应的房间信息
                List<TRoomDto> rooms= roomService.findRoomsByRoomTypeId(roomSaleConfig.getRoomTypeId());
                for (TRoomDto room:rooms){
                    //如果房间信息已经添加到活动房间列表则不继续添�?
                    if ("F".equals(room.getIsLock())){
                        TRoomSaleDto roomSale=new TRoomSaleDto();
                        roomSale.setOldRoomTypeId(room.getRoomTypeId());
                        roomSale.setRoomId(room.getId());
                        roomSale.setRoomNo(room.getName());
                        roomSale.setPms(room.getPms());
                        roomSale.setSalePrice(roomSaleConfig.getSaleValue());
                        roomSale.setStartTime(String.valueOf(roomSaleConfig.getStartTime()));
                        roomSale.setEndTime(String.valueOf(roomSaleConfig.getEndDate()));
                        roomSale.setConfigId(roomSaleConfig.getId());
                        roomSale.setSaleName(roomSaleConfig.getSaleName());
                        roomSale.setSaleType(roomSaleConfig.getStyleType());
                        tRoomSaleDtoList.add(roomSale);
                        saleNum++;
                    }
                }
            }
            roomTypeDto.setRoomNum(saleNum);

            logger.info(String.format("====================init sales config job >> find data end===================="));
            int newRoomTypeId = initRoomTypeDto(roomSaleConfig, roomTypeDto);
            roomTypeMap.put(roomTypeDto.getId(), newRoomTypeId);
            executeRecordMap.put("roomTypeMap", roomTypeMap);

            initRoomSaleDto(roomSaleConfig,roomTypeDto, tRoomSaleDtoList, roomTypeMap);
            //刷新索引 如果刷过索引就不再刷了
            if(hotelMap.get(roomSaleConfig.getHotelId()) == null){
                logger.info(String.format("====================init sales config job >> updateMikePriceCache begin===================="));
                boolean updateCacheSuccessFlag = hotelRemoteService.updateMikePriceCache(roomSaleConfig.getHotelId().toString());
                if(updateCacheSuccessFlag){
                    hotelMap.put(roomSaleConfig.getHotelId(),roomSaleConfig.getHotelId());
                    executeRecordMap.put("hotelMap", hotelMap);
                }else {
                    throw new RuntimeException("updateMikePriceCache error");
                }
                logger.info(String.format("====================init sales config job >> updateMikePriceCache end updateCacheSuccessFlag[%s]==========",String.valueOf(updateCacheSuccessFlag)));
        }

        }catch (Exception e){
            throw new RuntimeException("initSaleRoomSaleConfigDto error");
        }
        return executeRecordMap;
    }


    private void saveBasePriceService(TRoomSaleConfigDto tRoomSaleConfigDto, TBasePriceDto tBasePriceDto) {
        if(tRoomSaleConfigDto == null){
            return;
        }
        if(ValueTypeEnum.TYPE_TO.getId() == tRoomSaleConfigDto.getType().getId()){
            tBasePriceDto.setPrice(tRoomSaleConfigDto.getSaleValue());
        }else if(ValueTypeEnum.TYPE_ADD.getId() == tRoomSaleConfigDto.getType().getId()) {
            //得到房型价格
            OtsRoomStateDto otsRoomStateDto = roomService.getOtsRoomState(tRoomSaleConfigDto.getHotelId(), tRoomSaleConfigDto.getRoomTypeId(),new Date(), new Date());
            BigDecimal roomTypePrice = otsRoomStateDto.getPmsPrice();
            BigDecimal price = roomTypePrice.subtract(tRoomSaleConfigDto.getSaleValue());
            tBasePriceDto.setPrice(price.compareTo(new BigDecimal(0)) < 1 ? new BigDecimal(0) : price);
        }else if(ValueTypeEnum.TYPE_OFF.getId() == tRoomSaleConfigDto.getType().getId()) {
            //得到房型价格
            //得到房型价格
            OtsRoomStateDto otsRoomStateDto = roomService.getOtsRoomState(tRoomSaleConfigDto.getHotelId(), tRoomSaleConfigDto.getRoomTypeId(),new Date(), new Date());
            BigDecimal roomTypePrice = otsRoomStateDto.getPmsPrice();
            BigDecimal price = roomTypePrice.multiply(tRoomSaleConfigDto.getSaleValue().divide(new BigDecimal(100)));
            tBasePriceDto.setPrice(price.compareTo(new BigDecimal(0)) < 1 ? new BigDecimal(0) : price);
            tBasePriceDto.setPrice(tRoomSaleConfigDto.getSaleValue());
        }
        basePriceService.saveBasePriceService(tBasePriceDto);
    }

    @Transactional
    public int initRoomTypeDto(TRoomSaleConfigDto tRoomSaleConfigDto, TRoomTypeDto roomTypeDto){
        logger.info(String.format("====================init sales config job >> initRoomTypeDto method begin===================="));
        Integer newRoomTypeId = null;
        try{
            TRoomTypeDto roomTypeModel=roomTypeService.findTRoomTypeById(roomTypeDto.getId());
            TBasePriceDto tBasePriceDto = basePriceService.findByRoomtypeId(new Long(roomTypeDto.getId()));
            //根据配置文件的价格规则算出价格设置到baseprice表中
            if(tBasePriceDto != null){
                saveBasePriceService(tRoomSaleConfigDto, tBasePriceDto);
            }
            //将原价格存起�?
            roomTypeModel.setRoomNum(roomTypeDto.getRoomNum());
            roomTypeModel.setCost(roomTypeDto.getCost());
            roomTypeModel.setName(roomTypeDto.getName());
            //复制并创建活动房�?
            roomTypeService.saveTRoomType(roomTypeModel);
            newRoomTypeId = roomTypeModel.getId();
            roomTypeModel.setRoomNum(-roomTypeDto.getRoomNum());
            roomTypeService.updatePlusRoomNum(roomTypeModel);
            //得到房型其他信息
            TRoomTypeInfoDto roomTypeInfo = roomTypeInfoService.findByRoomTypeId(roomTypeDto.getId());
            roomTypeInfo.setRoomTypeId(newRoomTypeId);
            //复制并创建房型其他信�?
            roomTypeInfoService.saveRoomTypeInfo(roomTypeInfo);
            //得到房价对应配置信息
            List<TRoomTypeFacilityDto> roomTypeFacilityDtos = roomTypeFacilityService.findByRoomTypeId(roomTypeDto.getId());
            for (TRoomTypeFacilityDto roomTypeFacilityDto : roomTypeFacilityDtos) {
                roomTypeFacilityDto.setRoomTypeId(newRoomTypeId);
                roomTypeFacilityService.saveRoomSaleConfig(roomTypeFacilityDto);
            }
            //回填
            tRoomSaleConfigDto.setSaleRoomTypeId(newRoomTypeId);
            roomSaleConfigService.updateRoomSaleConfig(tRoomSaleConfigDto);
        }catch (Exception e){
            throw new RuntimeException("initSaleRoomSaleConfigDto error");
        }
        logger.info(String.format("====================init sales config job >> initRoomTypeDto method end===================="));
        return newRoomTypeId;
    }

    @Transactional
    public void initRoomSaleDto(TRoomSaleConfigDto roomSaleConfig,TRoomTypeDto roomTypeDto , List<TRoomSaleDto> tRoomDtoList, Map<Integer,Integer>  roomTypeMap){
        logger.info(String.format("====================init sales config job >> initRoomSaleDto method begin===================="));
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
                throw new RuntimeException("initSaleRoomSaleConfigDto error");
            }
        }
        logger.info(String.format("====================init sales config job >> initRoomSaleDto method end===================="));
    }

}
