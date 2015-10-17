package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.util.*;

@Service
public class ValidRateTaskServiceImpl implements ValidRateTaskService {
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

    private final String otsUrl="http://smlt-ots.imike.cn/ots/";

    public void validRateTaskRun(){
        TRoomSaleConfigDto roomSaleConfigDto=new TRoomSaleConfigDto();
        //读取活动配置表数�?
        List<TRoomSaleConfigDto> list=roomSaleConfigService.queryRoomSaleConfigByParams(roomSaleConfigDto);
        if (list==null){
            return;
        }
        //获取配置表中对应可以做活动的房间信息
        Map<String,Object> saleRoomMap=getSaleRoom(list);
        //得到�?有符合做活动条件房间
        List<TRoomSaleDto>  saleRooms=(ArrayList)saleRoomMap.get("roomDtos");
        //得到�?有符合做活动对应的房�?
        List<TRoomTypeDto>  roomTypes=(ArrayList)saleRoomMap.get("roomTypeDtos");
        //将新roomTypeId和�?�roomTypeId对应起来
        Map<Integer,Integer> roomTypeMap=new HashMap<Integer, Integer>();
        //促销前价�?
        Map<Integer,BigDecimal> roomTypePriceMap=new HashMap<Integer, BigDecimal>();

        for (TRoomTypeDto roomTypeDto:roomTypes){
            Integer newRoomTypeId=0;
            TRoomTypeDto roomTypeModel=roomTypeService.findTRoomTypeById(roomTypeDto.getId());

            //将原价格存起�?
            roomTypePriceMap.put(roomTypeDto.getId(), roomTypeModel.getCost());
            roomTypeModel.setRoomNum(roomTypeDto.getRoomNum());
            roomTypeModel.setCost(roomTypeDto.getCost());
            roomTypeModel.setName(roomTypeDto.getName());
            //复制并创建活动房�?
            roomTypeService.saveTRoomType(roomTypeModel);
            newRoomTypeId = roomTypeModel.getId();
            if (newRoomTypeId == null) {
                continue;
            }
            roomTypeModel.setRoomNum(-roomTypeDto.getRoomNum());
            roomTypeService.updatePlusRoomNum(roomTypeModel);
            //将新roomTypeId和�?�roomTypeId对应起来
            roomTypeMap.put(roomTypeDto.getId(), newRoomTypeId);

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

        }
        //循环创建活动房间
        for (TRoomSaleDto roomDto : saleRooms) {
            Integer newRoomTypeId = roomTypeMap.get(roomDto.getOldRoomTypeId());
            Integer oldRoomTypeId = roomDto.getOldRoomTypeId();
            if (newRoomTypeId == null) {
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
            roomDto.setCostPrice(roomTypePriceMap.get(oldRoomTypeId));
            roomDto.setRoomTypeId(newRoomTypeId);
            roomDto.setIsBack("F");
            roomSaleService.saveRoomSale(roomDto);
        }
        ServiceUtils.postData(otsUrl + "/roomsale/saleBegin", "POST", "");

    }

    public Map<String,Object> getSaleRoom(List<TRoomSaleConfigDto>  list){
        Map<Integer,TRoomSaleDto> saleRooms=new HashMap<Integer, TRoomSaleDto>();
        Map<Integer,TRoomTypeDto> roomTypeList=new HashMap<Integer, TRoomTypeDto>();
        //循环配置表所有数�?
        for (TRoomSaleConfigDto roomSaleConfig:list){
            TRoomTypeDto roomTypeDto=new TRoomTypeDto();
            //如果房型不存在map中，则将房型put到map�?
            if (roomTypeList.get(roomSaleConfig.getRoomTypeId())==null){
                roomTypeDto.setId(roomSaleConfig.getRoomTypeId());
                roomTypeDto.setCost(roomSaleConfig.getSaleValue());
                roomTypeDto.setName(roomSaleConfig.getSaleName());
                roomTypeDto.setRoomNum(0);
                roomTypeList.put(roomSaleConfig.getRoomTypeId(), roomTypeDto);
            } else {
                roomTypeDto = roomTypeList.get(roomSaleConfig.getRoomTypeId());
            }
            //取得当前房型做活动的库存�?
            int saleNum=roomTypeDto.getRoomNum();
            //如果配置文件中roomId存在则直接将其加到活动列表中
            if (roomSaleConfig.getRoomId() != null && roomSaleConfig.getRoomId() != 0) {
                TRoomDto room = roomService.findRoomsById(roomSaleConfig.getRoomId());
                if (room != null && "F".equals(room.getIsLock())) {//判断房间是否上锁
                    TRoomSaleDto roomSale = new TRoomSaleDto();
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
                    saleRooms.put(room.getId(),roomSale);
                    saleNum++;
                }
                if (saleNum == roomSaleConfig.getNum()) {
                    break;
                }
            } else {
                //按房型取得对应的房间信息
                List<TRoomDto> rooms = roomService.findRoomsByRoomTypeId(roomSaleConfig.getRoomTypeId());
                for (TRoomDto room : rooms) {
                    //如果房间信息已经添加到活动房间列表则不继续添�?
                    if (saleRooms.get(room.getId()) != null) {
                        continue;
                    }
                    if ("F".equals(room.getIsLock())) {
                        TRoomSaleDto roomSale = new TRoomSaleDto();
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
                        saleRooms.put(room.getId(),roomSale);
                        saleNum++;
                        if (saleNum == roomSaleConfig.getNum()) {
                            break;
                        }
                    }
                }
            }
            roomTypeDto.setRoomNum(saleNum);
            //更新做活动房型map
            roomTypeList.put(roomSaleConfig.getRoomTypeId(), roomTypeDto);

        }
        List<TRoomSaleDto> roomDtos = new ArrayList<TRoomSaleDto>();
        for (Integer key : saleRooms.keySet()) {
            roomDtos.add(saleRooms.get(key));
        }
        List<TRoomTypeDto> roomTypeDtos = new ArrayList<TRoomTypeDto>();
        for (Integer key : roomTypeList.keySet()) {
            roomTypeDtos.add(roomTypeList.get(key));
        }
        Map<String, Object> rs = new HashMap<String, Object>(2);
        rs.put("roomDtos", roomDtos);
        rs.put("roomTypeDtos", roomTypeDtos);
        return rs;
    }

    public void updateOnline(Date runTime) {
        //查询指定期间内的CONFIG
        List<TRoomSaleConfigDto> configDtoList = null;
        //按开始时间更新
        for (TRoomSaleConfigDto dto : configDtoList) {
            Time startTime = dto.getStartTime();
        }
    }

    private void updateRoomType(Integer roomTypeId, Integer oldRoomTypeId) {
        TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
        roomChangeTypeDto.setRoomTypeId(roomTypeId);
        roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
        this.roomService.updateRoomTypeByRoomType(roomChangeTypeDto);
    }

    private void updateRoomSetting(Integer roomTypeId, Integer oldRoomTypeId) {
        TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
        roomChangeTypeDto.setRoomTypeId(roomTypeId);
        roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
        this.roomSettingService.updateTRoomSettingByRoomTypeId(roomChangeTypeDto);

    }

    public void dateBack() {
        TRoomSaleConfigDto troomSaleConfigDto = new TRoomSaleConfigDto();
        troomSaleConfigDto.setValid(ValidEnum.VALID.getId());
        List<TRoomSaleConfigDto> list = roomSaleConfigService.queryRoomSaleConfigByParams(troomSaleConfigDto);
        if (!CollectionUtils.isEmpty(list)) {
            for (TRoomSaleConfigDto dto : list) {
                String endDate = dto.getEndDate() + " " + dto.getEndTime();
                try {
                    //比较活动结束时间和当前时间
                    Boolean bl = DateUtils.getCompareResult(endDate, DateUtils.getStringDate(null), "yyyy-MM-dd HH:mm:ss");
                    if (!bl) {

                    }
                } catch (ParseException pe) {
                }
            }
        }
    }

    public Boolean reBackRoom(TRoomSaleConfigDto dto) {
        if (null == dto) {
            return false;
        }
        //根据配置id查询当前没有回复的数据
        List<TRoomSaleDto> saleDtoList = roomSaleService.queryByConfigAndBack(dto.getId() + "", "F");
        if (CollectionUtils.isEmpty(saleDtoList)) {
            return false;
        }
        for (TRoomSaleDto saleTo : saleDtoList) {
            //还原t_room表中的数据
            this.updateRoom(saleTo);
        }
        return true;
    }
    public Boolean  updateRoom(TRoomSaleDto  roomSaleDto){
          roomService.updateRoomTypeByRoomType(roomSaleDto.getRoomNo(),roomSaleDto.getPms(),roomSaleDto.getOldRoomTypeId());
          return true;
    }
}
