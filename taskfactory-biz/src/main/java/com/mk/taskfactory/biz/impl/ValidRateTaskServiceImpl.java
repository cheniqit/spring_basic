package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigInfoMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.ServiceUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private RoomSaleConfigInfoMapper roomSaleConfigInfoMapper;
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
        TRoomSaleConfigInfo tRoomSaleConfigInfo = new TRoomSaleConfigInfo();
        String matchDate = DateUtils.format_yMd(org.apache.commons.lang3.time.DateUtils.addDays(new Date(), 1));
        tRoomSaleConfigInfo.setMatchDate(matchDate);
        tRoomSaleConfigInfo.setValid(ValidEnum.VALID.getId());
        List<TRoomSaleConfigInfo> tRoomSaleConfigInfos = roomSaleConfigInfoMapper.queryRoomSaleConfigInfoList(tRoomSaleConfigInfo);
        for(TRoomSaleConfigInfo configInfo : tRoomSaleConfigInfos){
            initTRoomSaleConfigDtoList(configInfo);
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

    public void initTRoomSaleConfigDtoList(TRoomSaleConfigInfo configInfo){
        TRoomSaleConfigDto roomSaleConfigDto=new TRoomSaleConfigDto();
        roomSaleConfigDto.setSaleRoomTypeIdIsNull(true);
        roomSaleConfigDto.setSaleConfigInfoId(configInfo.getId());
        roomSaleConfigDto.setValid(ValidEnum.VALID.getId());
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
    }


    @Transactional
    public void updateOnline(Date runTime) {
        logger.info("============sales online job >> validRateTaskRun method start===============");

        //查询指定期间内的CONFIG
        List<TRoomSaleConfigDto> configDtoList = roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.VALID.getId());

        logger.info("============sales online job >> find configDtoList=======" + configDtoList.size());
        //按开始时间更新
        Time time = getTime(runTime);
        for (TRoomSaleConfigDto configDto : configDtoList) {
            updateConfigOnline(time, configDto);
        }
        logger.info("============sales online job >> validRateTaskRun method end===============");
    }

    private Time getTime(Date runTime) {
        //
        Date formatTime = null;
        if (null == runTime) {
            formatTime = new Date();
        } else {
            formatTime = runTime;
        }

        //
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String strTime = timeFormat.format(formatTime);

        //
        Time time = null;
        try {
            Date onlyTime = timeFormat.parse(strTime);
            time = new Time(onlyTime.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    private void updateConfigOnline(Time time, TRoomSaleConfigDto configDto) {
        logger.info("============sales online job >> for configDto.id======" + configDto.getId());
        Integer saleRoomTypeId = configDto.getSaleRoomTypeId();
        if (null == saleRoomTypeId) {
            logger.info("============sales online job >> configDto.id:" + configDto.getId() + " not init, continue");
            return;
        }

        //检查是否完成任务
        if (ValidEnum.VALID.getId().equals(configDto.getStarted())) {
            logger.info("============sales online job >> configDto.id:" + configDto.getId() + " is started, continue");
            return;
        }

        //检查是否开始
        Time startTime = configDto.getStartTime();
        if (time.after(startTime)) {
            logger.info("============sales online job >> configDto.id:" + configDto.getId() + " after startTime");
            int hotelId = configDto.getHotelId();
            int roomTypeId = configDto.getRoomTypeId();
            Integer roomId = configDto.getRoomId();
            Integer num = configDto.getNum();
            logger.info("============sales online job >> configDto.id:" + configDto.getId()
                    + " roomTypeId:" + roomTypeId + " saleRoomTypeId:" + saleRoomTypeId);

            //OTS房态
            OtsRoomStateDto roomStateDto = this.roomService.getOtsRoomState(hotelId,roomTypeId,null,null);
            List<Integer> roomVCList = roomStateDto.getRoomIdList();
            int roomVCSize = roomVCList.size();
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " get roomVCSize:"+roomVCSize);

            //按num数量抽取房间
            List<Integer> onSaleRoomList = buildOnSaleRoomList(roomId, num, roomVCList, roomVCSize);
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " get onSaleRoomList:" + onSaleRoomList.size());

            //更新房间上线
            TRoomTypeDto roomTypeDto = this.roomTypeService.findTRoomTypeById(roomTypeId);
            for (Integer onSaleRoomId : onSaleRoomList) {
                updateRoomOnline(configDto, roomTypeDto, onSaleRoomId);
            }
            //更新config started
            configDto.setStarted(ValidEnum.VALID.getId());
            this.roomSaleConfigService.updateRoomSaleConfig(configDto);
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " to started");
        } else {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " not after startTime, continue");
        }
    }

    private void updateRoomOnline(TRoomSaleConfigDto configDto, TRoomTypeDto roomTypeDto, Integer onSaleRoomId) {
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " for onSaleRoomId:" + onSaleRoomId);
        //get roomDto
        TRoomDto roomDto = this.roomService.findRoomsById(onSaleRoomId);
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " get onSaleRoomId:" + onSaleRoomId);

        //updateRoomType to saleRoomTypeId
        TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
        roomChangeTypeDto.setId(onSaleRoomId);
        roomChangeTypeDto.setRoomTypeId(configDto.getSaleRoomTypeId());
        this.roomService.updateRoomTypeByRoomType(roomChangeTypeDto);
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " update room id:" + onSaleRoomId + " online");

        //update roomSetting to saleRoomTypeId
        TRoomSettingDto roomSettingParam = new TRoomSettingDto();
        roomSettingParam.setRoomTypeId(configDto.getRoomTypeId());
        roomSettingParam.setRoomNo(roomDto.getName());
        TRoomSettingDto roomSettingDto = this.roomSettingService.selectByRoomTypeIdAndRoomNo(roomSettingParam);

        roomSettingDto.setRoomTypeId(configDto.getSaleRoomTypeId());
        this.roomSettingService.updateTRoomSetting(roomSettingDto);
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " update roomSetting id:" + roomSettingDto.getId() + " online");

        //saveRoomSale
        saveRoomSale(configDto, onSaleRoomId, roomTypeDto, roomDto);
    }

    private List<Integer> buildOnSaleRoomList(Integer roomId, Integer num, List<Integer> roomVCList, int roomVCSize) {
        List<Integer> onSaleRoomList = new ArrayList<Integer>();
        //若未指定房间，随机抽取
        if (null == roomId) {

            if (num >= roomVCSize) {
                onSaleRoomList.addAll(roomVCList);
            } else {
                //随机抽取
                Random random = new Random();
                Set<Integer> randomSet = new HashSet<Integer>();
                while (onSaleRoomList.size() < num) {
                    int number = random.nextInt(roomVCList.size());
                    if (randomSet.contains(number)) {
                        continue;
                    }
                    //加入
                    onSaleRoomList.add(roomVCList.get(number));
                    randomSet.add(number);
                }
            }
        } else {
            if (roomVCList.contains(roomId)){
                onSaleRoomList.add(roomId);
            }
        }
        return onSaleRoomList;
    }

    private void saveRoomSale(TRoomSaleConfigDto configDto, Integer roomId, TRoomTypeDto roomTypeDto, TRoomDto roomDto) {

        TBasePriceDto basePriceDto = this.basePriceService.findByRoomtypeId(new Long(configDto.getSaleRoomTypeId()));
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " basePrice:" + basePriceDto.getPrice());

        BigDecimal settleValue =
                this.calaValue(basePriceDto.getPrice(), configDto.getSettleValue(), configDto.getSettleType());
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " settleValue:" + settleValue.toString());

        //
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        //log roomSale
        TRoomSaleDto roomSaleDto = new TRoomSaleDto();
        roomSaleDto.setRoomTypeId(configDto.getSaleRoomTypeId());
        roomSaleDto.setOldRoomTypeId(configDto.getRoomTypeId());
        roomSaleDto.setRoomNo(roomDto.getName());
        roomSaleDto.setPms(roomDto.getPms());
        roomSaleDto.setCreateDate(dateFormat.format(new Date()));

        roomSaleDto.setSalePrice(basePriceDto.getPrice());
        roomSaleDto.setCostPrice(roomTypeDto.getCost());

        roomSaleDto.setStartTime(timeFormat.format(configDto.getStartDate()));
        roomSaleDto.setEndTime(timeFormat.format(configDto.getEndDate()));
        roomSaleDto.setRoomId(roomId);
        roomSaleDto.setConfigId(configDto.getId());
        roomSaleDto.setIsBack(ValidEnum.DISVALID.getId());
        roomSaleDto.setSaleName(configDto.getSaleName());
        roomSaleDto.setSaleType(configDto.getStyleType());
        roomSaleDto.setHotelId(configDto.getHotelId());

        roomSaleDto.setSettleValue(settleValue);
        this.roomSaleService.saveRoomSale(roomSaleDto);
    }

    private BigDecimal calaValue(BigDecimal baseValue, BigDecimal value, ValueTypeEnum valueTypeEnum) {
        if (null == baseValue || null == valueTypeEnum) {
            return baseValue;
        }

        if (null == value) {
            return baseValue;
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
    //数据回复
    public void dateReback() {
        List<TRoomSaleConfigDto> list = roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.VALID.getId());
        if (!CollectionUtils.isEmpty(list)) {
            for (TRoomSaleConfigDto dto : list) {
                java.sql.Date endDate = dto.get;
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
                        if (!DateUtils.getCompareResult(nowTimeComp, endTimeComp, "yyyy-MM-dd HH:mm")) {
                            boolean   bl = reBackRoom(dto);
                            if(bl){
                                roomSaleConfigService.updateRoomSaleConfigStarted(dto.getId(),ValidEnum.DISVALID.getId());
                            }
                        }
                    }
                } catch (ParseException e){
                }
            }
        }
    }

    //数据清除
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
                        boolean   bl =    reBackRoom(dto);
                        if(bl){
                            //删除房型
                            deleteRoomType(dto);
                        }
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
        List<TRoomSaleDto> saleDtoList = roomSaleService.queryByConfigAndBack(dto.getId() + "", ValidEnum.DISVALID.getId());
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
        List<Integer>  newRoomTypeIdList =  roomSaleService.queryByConfigGroup(troomSaleConfigDto.getId(), ValidEnum.DISVALID.getId());
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
