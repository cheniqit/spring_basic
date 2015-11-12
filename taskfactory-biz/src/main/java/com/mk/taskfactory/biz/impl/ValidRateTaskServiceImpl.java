package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.enums.ValidEnum;
import com.mk.taskfactory.biz.mapper.HotelMapper;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigInfoMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.THotel;
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
    @Autowired
    private RoomTypeBedService roomTypeBedService;
    @Autowired
    private HotelRemoteService hotelRemoteService;


    public void validRateTaskRun(){
        Date startDate = org.apache.commons.lang3.time.DateUtils.addDays(new Date(), 1);
        validRateTaskRun(startDate);
    }

    public void validRateTaskRunToday(){
        Date startDate = new Date();
        validRateTaskRun(startDate);
    }

    public void validRateTaskRun(Date startDate){
        logger.info(String.format("====================init sales config job >> validRateTaskRun method begin===================="));
        //当前时间是否在config中
        TRoomSaleConfigInfoDto tRoomSaleConfigInfo = new TRoomSaleConfigInfoDto();
        String matchDate = DateUtils.format_yMd(startDate);
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
            Integer tag = tRoomSaleConfigDto.getTag();
            if (null == tag || 0 == tag) {
                try{
                    executeRecordMap = validRateTaskLogicService.initSaleRoomSaleConfigDto(tRoomSaleConfigDto, executeRecordMap);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.info(String.format("====================init sales config job >>  initSaleRoomSaleConfigDto erroe ===================="));
                }
            } else {
                Map<Integer, Integer> hotelMap = executeRecordMap.get("hotelMap");
                hotelMap.put(tRoomSaleConfigDto.getHotelId(),tRoomSaleConfigDto.getHotelId());
            }
        }
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> remote begin===================="));

        Map<Integer, Integer> hotelMap = executeRecordMap.get("hotelMap");
        //刷新缓存及索引
        if (!hotelMap.keySet().isEmpty()) {
            this.hotelRemoteService.initHotel(hotelMap.keySet());
        }
        for (Integer hotelId : hotelMap.keySet()) {
            this.roomSaleConfigService.updatePriceCache(hotelId.longValue());
        }
        logger.info(String.format("====================initSaleRoomSaleConfigDto >> remote end"));
    }


    public void updateOnline(Date runTime) {
        logger.info("============sales online job >> validRateTaskRun method start===============");
        //find valid roomSaleType
        TRoomSaleTypeDto typeParam = new TRoomSaleTypeDto();
        typeParam.setValid(ValidEnum.VALID.getId());
        List<TRoomSaleTypeDto> saleTypeDtoList = this.roomSaleTypeService.queryRoomSaleType(typeParam);
        logger.info("============sales online job >> find saleTypeDtoList:" + saleTypeDtoList.size());

        //查询指定期间内的CONFIG Info
        List<TRoomSaleConfigInfoDto> configInfoDtoStartList = getStartedRoomSaleConfigInfo(saleTypeDtoList, runTime);

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
                updateConfigOnline(configInfoDto, configDto, runTime);
            }
        }

        logger.info("============sales online job >> validRateTaskRun method end===============");
    }

    private List<TRoomSaleConfigInfoDto> getStartedRoomSaleConfigInfo(List<TRoomSaleTypeDto> saleTypeDtoList, Date runTime) {
        List<TRoomSaleConfigInfoDto> configInfoDtoStartList = new ArrayList<TRoomSaleConfigInfoDto>();
        if (null == saleTypeDtoList || null == runTime) {
            return configInfoDtoStartList;
        }
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

                Time startTime = configInfoDto.getStartTime();
                Time endTime = configInfoDto.getEndTime();

                Date[] startEndDate = this.getStartEndDate(runTime, startTime, endTime);
                Date startDate = startEndDate[0];
                Date endDate = startEndDate[1];

                //若是开始结束时间未跨日，活动必须在开始结束时间范围内
                if (!runTime.before(startDate) && !runTime.after(endDate)) {
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

    @Transactional
    private void updateConfigOnline(TRoomSaleConfigInfoDto configInfoDto, TRoomSaleConfigDto configDto, Date runTime) {
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

        BigDecimal price = roomStateDto.getPrice();
        BigDecimal pmsPrice = roomStateDto.getPmsPrice();
        if (null == price || null == pmsPrice) {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " get roomPrice:null continue" );
            return;
        }

        //眯客价大于门市价
        BigDecimal saleValue =
                this.calaValue(price, configDto.getSaleValue(), ValueTypeEnum.getById(configDto.getSaleType()));
        if (pmsPrice.compareTo(saleValue) < 0) {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " get roomPrice>pmsPrice continue" );
            return;
        }
        //结算价大于原眯客价
        BigDecimal settleValue =
                this.calaValue(price, configDto.getSettleValue(), configDto.getSettleType());
        if (price.compareTo(settleValue) <= 0) {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " get settleValue>roomPrice continue" );
            return;
        }
        //
        int roomVCSize = roomVCList.size();
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " get roomVCSize:" + roomVCSize);

        if (roomVCSize > 0) {
            //按num数量抽取房间
            List<Integer> onSaleRoomList = buildOnSaleRoomList(roomId, num, roomVCList, roomVCSize);
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " get onSaleRoomList:" + onSaleRoomList.size());

            //更新房间上线
            TRoomTypeDto roomTypeDto = this.roomTypeService.findTRoomTypeById(roomTypeId);
            for (Integer onSaleRoomId : onSaleRoomList) {
                updateRoomOnline(configInfoDto, configDto, onSaleRoomId, runTime, roomStateDto);
            }
        }

        //更新config started
        configDto.setStarted(ValidEnum.VALID.getId());
        this.roomSaleConfigService.updateRoomSaleConfig(configDto);
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " to started");
    }

    private void updateRoomOnline(
            TRoomSaleConfigInfoDto configInfoDto, TRoomSaleConfigDto configDto,
            Integer onSaleRoomId, Date runTime, OtsRoomStateDto roomStateDto) {
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " for onSaleRoomId:" + onSaleRoomId);

        //get roomDto
        TRoomDto roomDto = this.roomService.findRoomsById(onSaleRoomId);
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " get onSaleRoomId:" + onSaleRoomId);

        //saveRoomSale
        saveRoomSale(configInfoDto, configDto, roomDto, runTime, roomStateDto);

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

        if (null != roomSettingDto) {
            roomSettingDto.setRoomTypeId(configDto.getSaleRoomTypeId());
            this.roomSettingService.updateTRoomSetting(roomSettingDto);
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " update roomSetting id:" + roomSettingDto.getId() + " online");
        }

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
            TRoomSaleConfigInfoDto configInfoDto, TRoomSaleConfigDto configDto,
            TRoomDto roomDto, Date runTime,OtsRoomStateDto roomStateDto) {

        BigDecimal basePrice = roomStateDto.getPrice();
        logger.info("============sales online job >> configDto.id:"
                + configDto.getId() + " basePrice: " + basePrice);

        BigDecimal saleValue = this.calaValue(basePrice, configDto.getSaleValue(), ValueTypeEnum.getById(configDto.getSaleType()));
        if (null == saleValue) {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " saleValue:null continue" );
            return;
        } else {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " saleValue:" + saleValue.toString());
        }

        BigDecimal settleValue =
                this.calaValue(basePrice, configDto.getSettleValue(), configDto.getSettleType());
        if (null == settleValue) {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " settleValue:null continue" );
            return;
        } else {
            logger.info("============sales online job >> configDto.id:"
                    + configDto.getId() + " settleValue:" + settleValue.toString());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //
        Date[] startEndDate = this.getStartEndDate(runTime, configInfoDto.getStartTime(), configInfoDto.getEndTime());
        Date startDate = startEndDate[0];
        Date endDate = startEndDate[1];

        //log roomSale
        TRoomSaleDto roomSaleDto = new TRoomSaleDto();
        roomSaleDto.setRoomTypeId(configDto.getSaleRoomTypeId());
        roomSaleDto.setOldRoomTypeId(configDto.getRoomTypeId());
        roomSaleDto.setRoomNo(roomDto.getName());
        roomSaleDto.setPms(roomDto.getPms());
        roomSaleDto.setCreateDate(dateFormat.format(new Date()));

        roomSaleDto.setSalePrice(saleValue);
        roomSaleDto.setCostPrice(basePrice);

        roomSaleDto.setStartTime(dateFormat.format(startDate));
        roomSaleDto.setEndTime(dateFormat.format(endDate));

        roomSaleDto.setRoomId(roomDto.getId());
        roomSaleDto.setConfigId(configDto.getId());
        roomSaleDto.setIsBack(ValidEnum.DISVALID.getId());
        roomSaleDto.setSaleName(configDto.getSaleName());
        roomSaleDto.setSaleType(configDto.getSaleType());
        roomSaleDto.setHotelId(configDto.getHotelId());

        roomSaleDto.setSettleValue(settleValue);
        this.roomSaleService.saveRoomSale(roomSaleDto);
    }

    public static void main(String[] arg ) {
//        ValidRateTaskServiceImpl t = new ValidRateTaskServiceImpl();
//        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
//        try {
//            Date[] d= t.getStartEndDate(new Date(), timeFormat.parse("8:00:00"), timeFormat.parse("20:00:00"));
//            System.out.println(d);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        BigDecimal cost = new BigDecimal(378);
        BigDecimal price = new BigDecimal(223);

        if(cost.compareTo(price) > 0){
            System.out.printf("1");
        }else
        {
            System.out.printf("2");
        }
    }

    private Date[] getStartEndDate (Date runTime, Date startTime, Date endTime) {
        if (null == runTime || null == startTime || null == endTime) {
            return new Date[]{new Date(),new Date()};
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            String strMidTime = dateFormat.format(runTime);
            Date midTime = datetimeFormat.parse(strMidTime + " 12:00:00");

            Date startDate = null;
            Date endDate = null;
            //若当前时间晚于中午12点,住房时间为今日到明日。若早于12点，住房时间为昨日到今日
            if (runTime.before(midTime)) {
                endDate = runTime;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.add(calendar.DATE,-1);
                startDate = calendar.getTime();
            } else {
                startDate = runTime;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                calendar.add(calendar.DATE,1);
                endDate = calendar.getTime();
            }

            //若开始时间早于endTime，当日时间
            if (startTime.before(endTime)) {
                String strStartDate = dateFormat.format(runTime) + " " + timeFormat.format(startTime);
                startDate = datetimeFormat.parse(strStartDate);

                String strEndDate = dateFormat.format(runTime) +  " " + timeFormat.format(endTime);
                endDate = datetimeFormat.parse(strEndDate);
            } else {
                String strStartDate = dateFormat.format(startDate) +  " " + timeFormat.format(startTime);
                startDate = datetimeFormat.parse(strStartDate);

                String strEndDate = dateFormat.format(endDate) +  " " + timeFormat.format(endTime);
                endDate = datetimeFormat.parse(strEndDate);
            }
            return new Date[]{startDate,endDate};
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Date[]{runTime,runTime};
    }
    public BigDecimal calaValue(BigDecimal baseValue, BigDecimal value, ValueTypeEnum valueTypeEnum) {
        if (null == baseValue || null == valueTypeEnum) {
            return baseValue;
        }

        if (null == value) {
            return baseValue;
        }

        logger.info("============sales online job >> calaValue");
        if (ValueTypeEnum.TYPE_TO == valueTypeEnum) {
            logger.info("============sales online job >> calaValue：TYPE_TO：" + value);
            return value;
        } else if (ValueTypeEnum.TYPE_ADD == valueTypeEnum) {
            BigDecimal result = baseValue.subtract(value);
            if (result.compareTo(BigDecimal.ZERO) > 0 ) {
                logger.info("============sales online job >> calaValue：TYPE_ADD：" + value);
                return result;
            }else {
                logger.info("============sales online job >> calaValue：TYPE_ADD：" + BigDecimal.ZERO);
                return BigDecimal.ZERO;
            }
        } else if (ValueTypeEnum.TYPE_OFF == valueTypeEnum) {
            logger.info("============sales online job >> calaValue：TYPE_OFF：" + value);
            return baseValue.multiply(value).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        } else {
            logger.info("============sales online job >> calaValue：else：" + baseValue);
            return baseValue;
        }
    }
    @Autowired
    private HotelMapper hotelMapper;

    //数据回复
    public void dateReback() {
        logger.info("============sales dateReback job >> start============");
        List<TRoomSaleConfigDto> list = roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.VALID.getId());
        logger.info("============sales dateReback job >> get TRoomSaleConfigDto size:" + list.size());
        Set<Integer> hotelSet=new HashSet<Integer>();
        if (!CollectionUtils.isEmpty(list)) {
            for (TRoomSaleConfigDto dto : list) {
                logger.info("============sales dateReback job >> do saleConfigDto id:" + dto.getId());
                //
                hotelSet.add(dto.getHotelId());
                try {
                    //比较活动结束日期和当前日期
                    //比较活动结束时间和当前时间
                    if (checkCanDown(dto.getStartTime(),dto.getEndTime())) {
                        logger.info("============sales dateReback job >> saleConfigDto id:" + dto.getId() + " end,");
                    //if (DateUtils.getCompareResult(endTimeComp,nowTimeComp , "yyyy-MM-dd HH:mm")) {
                        if (null != dto.getSaleRoomTypeId()) {
                            reBackRoom(dto);
                        }

                        logger.info("============sales dateReback job >>update saleConfigDto id:" + dto.getId() + " START DISVALID");
                        roomSaleConfigService.updateRoomSaleConfigStarted(dto.getId(), ValidEnum.DISVALID.getId());
                        String nowDate = DateUtils.getStringDate("yyyy-MM-dd HH:mm:ss");
                        SimpleDateFormat dafShort = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        java.util.Date now=dafShort.parse(nowDate);
                        java.util.Date endDate = dafShort.parse(dto.getEndDate()+" "+dto.getEndTime());
                        if ( now.compareTo(endDate)>= 0) {
                            logger.info("============sales dateReback job >>saleConfigDto id:" + dto.getId() + " start close job");
                            if (dto.getSaleRoomTypeId()==null){
                                logger.info("============sales dateReback job >>saleConfigDto id:" + dto.getId() + " getSaleRoomTypeId IS NULL continue");
                                continue;
                            }
                            //活动结束后把配置表置为F
                            logger.info("============sales dateReback job >>update saleConfigDto id:" + dto.getId() + " VALID DISVALID");
                            roomSaleConfigService.updateRoomSaleConfigValid(dto.getId(), ValidEnum.DISVALID.getId());
                            logger.info("============sales dateReback job >>saleConfigDto id:" + dto.getId()
                                    + " delete roomTypeInfo roomTypeId:" + dto.getSaleRoomTypeId());
                            roomTypeInfoService.deleteByRoomType(dto.getSaleRoomTypeId());
                        /*
                         *（5）根据t_room_sale roomtypeid删除表t_roomtype_facilit中where roomtypeid=${roomtypeid}中数据
                         */
                            logger.info("============sales dateReback job >>saleConfigDto id:" + dto.getId()
                                    + " delete roomTypeFacility roomTypeId:" + dto.getSaleRoomTypeId());
                            roomTypeFacilityService.deleteByRoomType(dto.getSaleRoomTypeId());

                         /*
                         *（5）根据t_room_sale roomtypeid删除表t_roomtype_facilit中where roomtypeid=${roomtypeid}中数据
                         */
                            logger.info("============sales dateReback job >>saleConfigDto id:" + dto.getId()
                                    + " delete roomType roomTypeId:" + dto.getSaleRoomTypeId());
                            roomTypeService.delTRoomTypeById(dto.getSaleRoomTypeId());

                            logger.info("============sales dateReback job >>saleConfigDto id:" + dto.getId()
                                    + " delete BasePrice roomTypeId:" + dto.getSaleRoomTypeId());
                            basePriceService.deleteBasePriceByRoomType(dto.getSaleRoomTypeId());

                            logger.info("============sales dateReback job >>saleConfigDto id:" + dto.getId()
                                    + " delete roomTypeBed roomTypeId:" + dto.getSaleRoomTypeId());
                            roomTypeBedService.deleteByRoomTypeId(dto.getSaleRoomTypeId().longValue());

                        }
                    } else {
                        logger.info("============sales dateReback job >> saleConfigDto id:" + dto.getId() + " not end");
                    }
                } catch (ParseException e){
                    e.printStackTrace();
                    logger.info("==================== 返回异常 ====================");
                }
            }
        }

        //刷新缓存及索引
        if (!hotelSet.isEmpty()) {
            this.hotelRemoteService.initHotel(hotelSet);
        }
        for (Integer hotelId : hotelSet) {
            this.roomSaleConfigService.updatePriceCache(hotelId.longValue());
        }
        logger.info("============sales dateReback job >> end============");
    }
    public Boolean checkCanDown(Time startTime,Time endTime){
        String nowTimeComp=DateUtils.getStringDate("HH:mm:ss");
        Time nowTime = Time.valueOf(nowTimeComp) ;
        if (startTime.compareTo(endTime)==-1){
            if (nowTime.compareTo(endTime)>=0||nowTime.compareTo(startTime)<1){
                return true;
            }else{
                return  false;
            }
        }else{
            if (nowTime.compareTo(startTime)>0){
                return false;
            }else if (nowTime.compareTo(endTime)==-1){
                return  false;
            }else{
                return true;
            }
        }

    }


    public void reBackRoom(TRoomSaleConfigDto dto) {
        if (null == dto) {
            return;
        }
        //根据配置id查询当前没有回复的数据
        List<TRoomSaleDto> saleDtoList = roomSaleService.queryByConfigAndBack(dto.getId() + "", ValidEnum.DISVALID.getId());
        if (CollectionUtils.isEmpty(saleDtoList)) {
            return;
        }
        for (TRoomSaleDto saleTo : saleDtoList) {
            //还原t_room表中的数据
            this.updateRoom(saleTo);
            //还原t_room_setting表中的数据
            this.updateRoomSetting(saleTo);
            //还原roomSaleDto数据
            TRoomSaleDto roomSaleDto = new TRoomSaleDto();
            roomSaleDto.setId(saleTo.getId());
            roomSaleDto.setIsBack("T");
            this.roomSaleService.updateRoomSaleBack(roomSaleDto);
        }

    }
    public Boolean  updateRoom(TRoomSaleDto  roomSaleDto){
          roomService.updateRoomTypeByRoomType(roomSaleDto.getRoomId(), roomSaleDto.getOldRoomTypeId());
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

    public void updateRoomSalePrice(){
        List<TRoomSaleDto> allList = this.roomSaleService.queryAll();

        logger.info("==================== allList.size:" + allList.size());
        for (TRoomSaleDto dto  : allList) {
            logger.info("==================== update TRoomSaleDto start");
            Integer oldRoomTypeId = dto.getOldRoomTypeId();
            Integer hotelId = dto.getHotelId();

            logger.info("==================== oldRoomTypeId:" +oldRoomTypeId + "  hotelId:"+ hotelId);
            OtsRoomStateDto stateDto = this.roomService.getOtsRoomState(hotelId, oldRoomTypeId, null, null);
            logger.info("==================== stateDto:" + stateDto.getPmsPrice());

            TRoomTypeDto roomType =  this.roomTypeService.findTRoomTypeById(oldRoomTypeId);
            BigDecimal cost = roomType.getCost();
            //
            Integer configId = dto.getConfigId();
            TRoomSaleConfigDto configDto = this.roomSaleConfigService.queryRoomSaleConfigById(configId);
            logger.info("==================== configDto:" +configId + " obj: " + configDto);

            BigDecimal price = stateDto.getPrice();
            BigDecimal steeleValue = this.calaValue(price, configDto.getSettleValue(), configDto.getSettleType());
            logger.info("==================== steeleValue:" + steeleValue);

            //
            if (cost.compareTo(price) > 0) {
                dto.setSettleValue(steeleValue);
            } else {
                BigDecimal costPrice = this.calaValue(cost, configDto.getSettleValue(), configDto.getSettleType());
                dto.setSettleValue(costPrice);
            }
            int i = this.roomSaleService.updateRoomSale(dto);
            logger.info("==================== save TRoomSaleDto:" + i);
            logger.info("==================== update TRoomSaleDto end");
        }
        logger.info("==================== update allList end");
    }


    public void initHotel(Boolean isInitValid, Long paramHotelId) {
        logger.info(String.format("====================initHotel >> start"));

        Set<Integer> hotelSet = new HashSet<Integer>();

        if (null != paramHotelId) {
            hotelSet.add(paramHotelId.intValue());
        } else {
            List<TRoomSaleConfigDto> dtoList = null;
            if (null == isInitValid) {
                dtoList = this.roomSaleConfigService.queryRoomSaleConfigByValid(null);
            } else if (isInitValid) {
                dtoList = this.roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.VALID.getId());
            } else {
                dtoList = this.roomSaleConfigService.queryRoomSaleConfigByValid(ValidEnum.DISVALID.getId());
            }
            logger.info(String.format("====================initHotel >> get list size [%s]", dtoList.size()));

            //收集hotelid
            for (TRoomSaleConfigDto dto : dtoList) {
                logger.info(String.format("====================initHotel >> get hotel [%s]", dto.getId()));
                Integer hotelId = dto.getHotelId();
                hotelSet.add(hotelId);
            }
        }

        //update
        this.hotelRemoteService.initHotel(hotelSet);

        for (Integer hotelId : hotelSet) {
            this.roomSaleConfigService.updatePriceCache(hotelId.longValue());
        }
        logger.info(String.format("====================initHotel >> end"));
    }
}
