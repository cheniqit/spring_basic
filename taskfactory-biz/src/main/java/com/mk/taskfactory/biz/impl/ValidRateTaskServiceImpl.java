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
import java.math.RoundingMode;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private RoomSaleTypeService roomSaleTypeService;
    @Autowired
    private RoomSaleConfigInfoService roomSaleConfigInfoService;
    @Autowired
    private ValidRateTaskLogicServiceImpl validRateTaskLogicService;
    @Autowired
    private RoomTypeInfoService roomTypeInfoService;
    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;


    public void validRateTaskRun(){
        logger.info(String.format("====================init sales config job >> validRateTaskRun method begin===================="));
        //当前时间是否在config中
        TRoomSaleConfigInfoDto tRoomSaleConfigInfo = new TRoomSaleConfigInfoDto();
        String matchDate = DateUtils.format_yMd(org.apache.commons.lang3.time.DateUtils.addDays(new Date(), 1));
        tRoomSaleConfigInfo.setMatchDate(matchDate);
        tRoomSaleConfigInfo.setValid(ValidEnum.VALID.getId());
        List<TRoomSaleConfigInfo> tRoomSaleConfigInfos = roomSaleConfigInfoMapper.queryRoomSaleConfigInfoList(tRoomSaleConfigInfo);
        if(CollectionUtils.isEmpty(tRoomSaleConfigInfos)){
            logger.info(String.format("====================init sales config job >> validRateTaskRun method config info list isEmpty end===================="));
            return;
        }
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
            logger.info(String.format("====================init sales config job >> validRateTaskRun method config list isEmpty end===================="));
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

        //find valid roomSaleType
        TRoomSaleTypeDto typeParam = new TRoomSaleTypeDto();
        typeParam.setValid(ValidEnum.VALID.getId());
        List<TRoomSaleTypeDto> saleTypeDtoList = this.roomSaleTypeService.queryRoomSaleType(typeParam);
        logger.info("============sales online job >> find saleTypeDtoList:" + saleTypeDtoList.size());

        //按开始时间更新
        Date date = getDate(runTime);
        Time time = getTime(runTime);

        //查询指定期间内的CONFIG Info
        List<TRoomSaleConfigInfoDto> configInfoDtoStartList = getStartedRoomSaleConfigInfo(saleTypeDtoList, date, time);

        //查询指定的CONFIG
        for (TRoomSaleConfigInfoDto configInfoDto : configInfoDtoStartList) {
            logger.info("============sales online job >> while configInfoDto.id:"
                    + configInfoDto.getId() + " start");
            TRoomSaleConfigDto configParam = new TRoomSaleConfigDto();
            configParam.setValid(ValidEnum.VALID.getId());
            configParam.setSaleConfigInfoId(configInfoDto.getId());
            List<TRoomSaleConfigDto> configDtoList = roomSaleConfigService.queryRoomSaleConfigByParams(configParam);
            logger.info("============sales online job >> find RoomSaleConfigList:" + configDtoList.size());
            for (TRoomSaleConfigDto configDto : configDtoList) {
                updateConfigOnline(configInfoDto, configDto);
            }
        }

        logger.info("============sales online job >> validRateTaskRun method end===============");
    }

    private List<TRoomSaleConfigInfoDto> getStartedRoomSaleConfigInfo(List<TRoomSaleTypeDto> saleTypeDtoList, Date date, Time time) {
        List<TRoomSaleConfigInfoDto> configInfoDtoStartList = new ArrayList<TRoomSaleConfigInfoDto>();
        for (TRoomSaleTypeDto typeDto : saleTypeDtoList) {
            logger.info("============sales online job >> while TRoomSaleTypeDto.id:" + typeDto.getId());

            Integer typeId = typeDto.getId();
            TRoomSaleConfigInfoDto configInfoParam = new TRoomSaleConfigInfoDto();
            configInfoParam.setSaleTypeId(typeId);
            configInfoParam.setValid(ValidEnum.VALID.getId());

            List<TRoomSaleConfigInfoDto> configInfoDtoList =
                    roomSaleConfigInfoService.queryRoomSaleConfigInfoList(configInfoParam);
            logger.info("============sales online job >> find configInfoDtoList:" + configInfoDtoList.size());

            for (TRoomSaleConfigInfoDto configInfoDto : configInfoDtoList) {
                Date startDate = configInfoDto.getStartDate();
                Time startTime = configInfoDto.getStartTime();

                if (time.after(startTime) && date.after(startDate)) {
                    logger.info("============sales online job >> while configInfoDto.id:"
                            + configInfoDto.getId() + " start");
                    configInfoDtoStartList.add(configInfoDto);
                } else {
                    logger.info("============sales online job >> while configInfoDto.id:"
                            + configInfoDto.getId() + " not start");
                }
            }
        }
        return configInfoDtoStartList;
    }

    private Date getDate(Date runTime) {
        Date date = null;
        if (null == runTime) {
            date = new Date();
        } else {
            date = runTime;
        }
        return date;
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
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
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

    private void updateConfigOnline(TRoomSaleConfigInfoDto configInfoDto, TRoomSaleConfigDto configDto) {
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

        if (roomVCSize > 0) {
            //按num数量抽取房间
            List<Integer> onSaleRoomList = buildOnSaleRoomList(roomId, num, roomVCList, roomVCSize);
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " get onSaleRoomList:" + onSaleRoomList.size());

            //更新房间上线
            TRoomTypeDto roomTypeDto = this.roomTypeService.findTRoomTypeById(roomTypeId);
            for (Integer onSaleRoomId : onSaleRoomList) {
                updateRoomOnline(configInfoDto, configDto, roomTypeDto, onSaleRoomId);
            }
        }

        //更新config started
        configDto.setStarted(ValidEnum.VALID.getId());
        this.roomSaleConfigService.updateRoomSaleConfig(configDto);
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " to started");
    }

    private void updateRoomOnline(
            TRoomSaleConfigInfoDto configInfoDto, TRoomSaleConfigDto configDto, TRoomTypeDto roomTypeDto, Integer onSaleRoomId) {
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
        saveRoomSale(configInfoDto, configDto, roomTypeDto, roomDto);
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

    private void saveRoomSale(
            TRoomSaleConfigInfoDto configInfoDto, TRoomSaleConfigDto configDto, TRoomTypeDto roomTypeDto, TRoomDto roomDto) {

        TBasePriceDto basePriceDto = this.basePriceService.findByRoomtypeId(new Long(configDto.getSaleRoomTypeId()));
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " basePrice:" + basePriceDto.getPrice());

        TBasePriceDto oldBasePriceDto = this.basePriceService.findByRoomtypeId(new Long(configDto.getRoomTypeId()));
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " oldBasePriceDto:" + oldBasePriceDto.getPrice());

        BigDecimal settleValue =
                this.calaValue(basePriceDto.getPrice(), configDto.getSettleValue(), configDto.getSettleType());
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " settleValue:" + settleValue.toString());

        //
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = getDateTime(new Date(),configInfoDto.getStartTime());
        Date endDate = getDateTime(new Date(),configInfoDto.getEndTime());

        //log roomSale
        TRoomSaleDto roomSaleDto = new TRoomSaleDto();
        roomSaleDto.setRoomTypeId(configDto.getSaleRoomTypeId());
        roomSaleDto.setOldRoomTypeId(configDto.getRoomTypeId());
        roomSaleDto.setRoomNo(roomDto.getName());
        roomSaleDto.setPms(roomDto.getPms());
        roomSaleDto.setCreateDate(dateFormat.format(new Date()));

        roomSaleDto.setSalePrice(basePriceDto.getPrice());
        roomSaleDto.setCostPrice(oldBasePriceDto.getPrice());

        roomSaleDto.setStartTime(dateFormat.format(startDate));
        roomSaleDto.setEndTime(dateFormat.format(endDate));

        roomSaleDto.setRoomId(roomDto.getId());
        roomSaleDto.setConfigId(configDto.getId());
        roomSaleDto.setIsBack(ValidEnum.DISVALID.getId());
        roomSaleDto.setSaleName(configDto.getSaleName());
        roomSaleDto.setSaleType(configDto.getStyleType());
        roomSaleDto.setHotelId(configDto.getHotelId());

        roomSaleDto.setSettleValue(settleValue);
        this.roomSaleService.saveRoomSale(roomSaleDto);
    }

    private Date getDateTime(Date date, Time time) {
        if (null == date || null == time) {
            return  new Date();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String strDate = dateFormat.format(date);
        String strTime = timeFormat.format(time);
        String strDateTime = strDate + " " + strTime;
        Date dateTime = new Date();
        try {
            dateTime = dateTimeFormat.parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateTime;
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
            BigDecimal result = baseValue.subtract(value);
            if (result.compareTo(BigDecimal.ZERO) > 0 ) {
                return result;
            }else {
                return BigDecimal.ZERO;
            }
        } else if (ValueTypeEnum.TYPE_ADD == valueTypeEnum) {
            return baseValue.multiply(value).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        } else {
            return baseValue;
        }
    }
    //数据回复
    public void dateReback() {
        logger.info("============sales dateReback job >> start============");
        List<TRoomSaleConfigDto> list = roomSaleConfigService.queryRoomSaleConfigByStarted(ValidEnum.VALID.getId());

        if (!CollectionUtils.isEmpty(list)) {
            for (TRoomSaleConfigDto dto : list) {

                try {

                    //比较活动结束日期和当前日期

                        //比较活动结束时间和当前时间
                        String endTimeComp = DateUtils.getStringDate("yyyy-MM-dd") + " " + dto.getEndTime();
                        String nowTimeComp = DateUtils.getStringDate("yyyy-MM-dd HH:mm");
                        if (DateUtils.getCompareResult(endTimeComp,nowTimeComp , "yyyy-MM-dd HH:mm")) {
                            boolean   bl = reBackRoom(dto);
                            if(bl){
                                roomSaleConfigService.updateRoomSaleConfigStarted(dto.getId(), ValidEnum.DISVALID.getId());
                                String nowDate = DateUtils.getStringDate("yyyy-MM-dd");
                                SimpleDateFormat dafShort = new SimpleDateFormat("yyyy-MM-dd");

                                java.util.Date now=dafShort.parse(nowDate);
                                java.util.Date endDate = dafShort.parse(DateUtils.format_yMd(dto.getEndDate()));
                                if ( now.compareTo(endDate)>= 0) {
                                    roomTypeInfoService.deleteByRoomType(dto.getSaleRoomTypeId());
                                /*
                                 *（5）根据t_room_sale roomtypeid删除表t_roomtype_facilit中where roomtypeid=${roomtypeid}中数据
                                 */
                                    roomTypeFacilityService.deleteByRoomType(dto.getSaleRoomTypeId());

                                 /*
                                 *（5）根据t_room_sale roomtypeid删除表t_roomtype_facilit中where roomtypeid=${roomtypeid}中数据
                                 */
                                    roomTypeService.delTRoomTypeById(dto.getSaleRoomTypeId());
                                    basePriceService.deleteBasePriceByRoomType(dto.getSaleRoomTypeId());
                                }
                            }

                        }

                } catch (ParseException e){
                }
            }
        }
        logger.info("============sales dateReback job >> end============");
    }

    //数据清除
    public void remove(){
        List<TRoomSaleConfigDto> list = roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.VALID.getId());
        if (!CollectionUtils.isEmpty(list)) {
            for (TRoomSaleConfigDto dto : list) {
                java.sql.Date endDate = null;//dto.getEndDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDate);
                cal.add(Calendar.DATE, 1);
                String endDateComp = null;//(new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime())+ " "+ dto.getEndTime();
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
