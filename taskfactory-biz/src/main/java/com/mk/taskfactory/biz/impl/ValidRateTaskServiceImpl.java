package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.ServiceUtils;
import com.mk.taskfactory.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@Service
public class ValidRateTaskServiceImpl implements ValidRateTaskService {
    private static Logger logger = LoggerFactory.getLogger(ValidRateTaskServiceImpl.class);

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomSaleConfigService roomSaleConfigService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomSettingService roomSettingService;
    @Autowired
    private RoomSaleService roomSaleService;
    @Autowired
    private BasePriceService basePriceService;
    @Autowired
    private ValidRateTaskLogicServiceImpl validRateTaskLogicService;


    public void validRateTaskRun(){
        logger.info(String.format("====================init sales config job >> validRateTaskRun method begin===================="));
        //当前时间是否在config中
        TRoomSaleConfigDto roomSaleConfigDto=new TRoomSaleConfigDto();
        String matchDate = DateUtils.format_yMd(org.apache.commons.lang3.time.DateUtils.addDays(new Date(), 1));
        roomSaleConfigDto.setMatchDate(matchDate);
        roomSaleConfigDto.setValid(ValidEnum.VALID.getId());
        roomSaleConfigDto.setSaleRoomTypeIdIsNull(true);
        //读取活动配置表数
        List<TRoomSaleConfigDto> list=roomSaleConfigService.queryRoomSaleConfigByParams(roomSaleConfigDto);
        if (CollectionUtils.isEmpty(list)){
            logger.info(String.format("====================init sales config job >> validRateTaskRun method list isEmpty end===================="));
            return;
        }
        //初始化数据
        HashMap<String,Map> executeRecordMap = new HashMap<String, Map>();
        executeRecordMap.put("roomTypeMap", new HashMap<Integer, Integer>());
        executeRecordMap.put("hotelMap", new HashMap<Integer, Integer>());
        for(TRoomSaleConfigDto tRoomSaleConfigDto : list){
            try{
                executeRecordMap = validRateTaskLogicService.initSaleRoomSaleConfigDto(tRoomSaleConfigDto, executeRecordMap);
            }catch (Exception e){
                e.printStackTrace();
                logger.info(String.format("====================init sales config job >>  initSaleRoomSaleConfigDto erroe ===================="));
            }
        }
        try {
            logger.info(String.format("====================init sales config job >> validRateTaskRun method call remote saleBegin begin ===================="));
            ServiceUtils.doPost(Constants.OTS_URL + "/roomsale/saleBegin", null, 40);
            logger.info(String.format("====================init sales config job >> validRateTaskRun method call remote saleBegin end ===================="));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(String.format("====================init sales config job >> validRateTaskRun method end===================="));
    }


    public void updateOnline(Date runTime) {
        //查询指定期间内的CONFIG
        TRoomSaleConfigDto roomSaleConfigDto = new TRoomSaleConfigDto();
        roomSaleConfigDto.setValid("T");
        List<TRoomSaleConfigDto> configDtoList = roomSaleConfigService.queryRoomSaleConfigByParams(roomSaleConfigDto);

        //按开始时间更新
        Time time = new Time(runTime.getTime());

        for (TRoomSaleConfigDto dto : configDtoList) {
            Time startTime = dto.getStartTime();
            if (time.after(startTime)) {
                int hotelId = dto.getHotelId();
                int roomTypeId = dto.getRoomTypeId();
                Integer saleRoomTypeId = dto.getSaleRoomTypeId();
                if (null == saleRoomTypeId) {
                    //数据错误，跳过
                    continue;
                }
                Integer roomId = dto.getRoomId();
                Integer num = dto.getNum();

                //OTS房态
                OtsRoomStateDto roomStateDto = this.roomService.getOtsRoomState(hotelId,roomTypeId,null,null);
                List<Integer> roomVCList = roomStateDto.getRoomIdList();
                int roomVCSize = roomVCList.size();

                List<Integer> onSaleRoomList = new ArrayList<Integer>();
                //若未指定房间，随机抽取
                if (null == roomId) {

                    if (num <= roomVCSize) {
                        onSaleRoomList.addAll(roomVCList);
                    } else {
                        while (onSaleRoomList.size() < num) {
                            //随机抽取
                            Random random = new Random();
                            int number = random.nextInt(roomVCList.size());
                            //加入后，减去
                            onSaleRoomList.add(roomVCList.get(number));
                            roomVCList.remove(number);
                        }
                    }
                } else {
                    if (roomVCList.contains(roomId)){
                        onSaleRoomList.add(roomId);
                    }
                }

                //
                TRoomTypeDto roomTypeDto = this.roomTypeService.findTRoomTypeById(roomTypeId);
                //
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
                for (Integer onSaleRoomId : onSaleRoomList) {
                    //get roomDto
                    TRoomDto roomDto = this.roomService.findRoomsById(onSaleRoomId);

                    //updateRoomType to saleRoomTypeId
                    TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
                    roomChangeTypeDto.setId(onSaleRoomId);
                    roomChangeTypeDto.setRoomTypeId(saleRoomTypeId);
                    this.roomService.updateRoomTypeByRoomType(roomChangeTypeDto);

                    //update roomSetting to saleRoomTypeId
                    TRoomSettingDto roomSettingParam = new TRoomSettingDto();
                    roomSettingParam.setRoomTypeId(roomTypeId);
                    roomSettingParam.setRoomNo(roomDto.getName());
                    TRoomSettingDto roomSettingDto = this.roomSettingService.selectByRoomTypeIdAndRoomNo(roomSettingParam);

                    roomSettingDto.setRoomTypeId(saleRoomTypeId);
                    this.roomSettingService.saveTRoomSetting(roomSettingDto);

                    //log roomSale
                    TRoomSaleDto roomSaleDto = new TRoomSaleDto();
                    roomSaleDto.setRoomTypeId(saleRoomTypeId);
                    roomSaleDto.setOldRoomTypeId(roomTypeId);
                    roomSaleDto.setRoomNo(roomDto.getName());
                    roomSaleDto.setPms(roomDto.getPms());
                    roomSaleDto.setCreateDate(dateFormat.format(new Date()));

                    TBasePriceDto basepriceDto = this.basePriceService.findByRoomtypeId(new Long(saleRoomTypeId));
                    roomSaleDto.setSalePrice(basepriceDto.getPrice());
                    roomSaleDto.setCostPrice(roomTypeDto.getCost());

                    roomSaleDto.setStartTime(timeFormat.format(dto.getStartDate()));
                    roomSaleDto.setEndTime(timeFormat.format(dto.getEndDate()));
                    roomSaleDto.setRoomId(roomId);
                    roomSaleDto.setConfigId(dto.getId());
                    roomSaleDto.setIsBack("F");
                    roomSaleDto.setSaleName(dto.getSaleName());
                    roomSaleDto.setSaleType(dto.getStyleType());
                    roomSaleDto.setHotelId(hotelId);

                    BigDecimal settleValue = this.calaValue(basepriceDto.getPrice(), dto.getSettleValue(), dto.getSettleType());
                    roomSaleDto.setSettleValue(settleValue);
                    this.roomSaleService.saveRoomSale(roomSaleDto);
                }
            }
        }
    }

    private BigDecimal calaValue(BigDecimal baseValue, BigDecimal value, ValueTypeEnum valueTypeEnum) {
        if (null == baseValue || null == value || null == valueTypeEnum) {
            return value;
        }

        if (ValueTypeEnum.TYPE_TO == valueTypeEnum) {
            return value;
        } else if (ValueTypeEnum.TYPE_ADD == valueTypeEnum) {
            return baseValue.subtract(value);
        } else if (ValueTypeEnum.TYPE_ADD == valueTypeEnum) {
            return baseValue.multiply(value).divide(new BigDecimal(100));
        } else {
            return baseValue;
        }
    }

    private void updateRoomType(Integer roomTypeId, Integer oldRoomTypeId) {
        TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
        roomChangeTypeDto.setRoomTypeId(roomTypeId);
        roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
//        this.roomService.updateRoomTypeByRoomType(roomChangeTypeDto);
    }

    private void updateRoomSetting(Integer roomTypeId, Integer oldRoomTypeId) {
        TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
        roomChangeTypeDto.setRoomTypeId(roomTypeId);
        roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
        this.roomSettingService.updateTRoomSettingByRoomTypeId(roomChangeTypeDto);

    }

    //数据回复
    public void dateReback() {
        List<TRoomSaleConfigDto> list = roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.VALID.getId());
        if (!CollectionUtils.isEmpty(list)) {
            for (TRoomSaleConfigDto dto : list) {
                java.sql.Date endDate = dto.getEndDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDate);
                cal.add(Calendar.DATE, 1);
                String endDateComp =(new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
                try {
                    //比较活动结束日期和当前日期
                    if (!DateUtils.getCompareResult(endDateComp, DateUtils.getStringDate("yyyy-MM-dd"), "yyyy-MM-dd")) {
                        //比较活动结束时间和当前时间
                        String endTimeComp = DateUtils.getStringDate("yyyy-MM-dd") + " " + dto.getEndTime();
                        String nowTimeComp = DateUtils.getStringDate("yyyy-MM-dd HH:mm");
                        if (DateUtils.getCompareResult(nowTimeComp, endTimeComp, "yyyy-MM-dd HH:mm")) {
                            reBackRoom(dto);
                        }
                    }
                } catch (ParseException e){
                }
            }
        }
    }

    //数据回复
    public void remove(){
        List<TRoomSaleConfigDto> list = roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.VALID.getId());
        if (!CollectionUtils.isEmpty(list)) {
            for (TRoomSaleConfigDto dto : list) {
                java.sql.Date endDate = dto.getEndDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDate);
                cal.add(Calendar.DATE, 1);
                String endDateComp =(new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime())+ " "+ dto.getEndTime();
                try {
                    //比较活动结束日期和当前日期
                    if (DateUtils.getCompareResult(endDateComp, DateUtils.getStringDate("yyyy-MM-dd HH:mm"), "yyyy-MM-dd HH:mm")) {
                           //先确保数据已经回复
                            reBackRoom(dto);
                          //删除房型
                            deleteRoomType(dto);
                        this.updateRoomSaleConfigValid(dto.getId(), ValidEnum.DISVALID.getId());
                    }
                } catch (ParseException e){
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
            //还原t_room_setting表中的数据
            this.updateRoomSetting(saleTo);
        }

        return true;
    }
    public Boolean  updateRoom(TRoomSaleDto  roomSaleDto){
          roomService.updateRoomTypeByRoomType(roomSaleDto.getRoomNo(),roomSaleDto.getPms(),roomSaleDto.getOldRoomTypeId());
          return true;
    }

    public Boolean  updateRoomSetting(TRoomSaleDto  roomSaleDto){
        roomSettingService.updateRoomTypeByRoomNo(roomSaleDto.getRoomNo(), roomSaleDto.getOldRoomTypeId(), roomSaleDto.getRoomTypeId());
        return true;
    }

    public Boolean  deleteRoomType(TRoomSaleConfigDto  troomSaleConfigDto){
        if(null==troomSaleConfigDto){
            return false;
        }
        //根据配置id查询当前没有回复的数据
        List<Integer>  newRoomTypeIdList =  roomSaleService.queryByConfigGroup(troomSaleConfigDto.getId(), "F");
        if (CollectionUtils.isEmpty(newRoomTypeIdList)) {
            return false;
        }
        for (Integer newRoomTypeId : newRoomTypeIdList) {
            roomTypeService.delTRoomTypeById(newRoomTypeId);
        }
        return true;
    }

    public Boolean  updateRoomSaleConfigValid(Integer id,String  valid){
        roomSaleConfigService.updateRoomSaleConfigValid(id,valid);
        return  true;
    }
}
