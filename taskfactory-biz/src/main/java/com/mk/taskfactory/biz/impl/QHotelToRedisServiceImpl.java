package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.crawer.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.dtos.crawer.*;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelDto;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelPriorityDto;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelRoomTypeDto;
import com.mk.taskfactory.api.enums.HotelSourceEnum;
import com.mk.taskfactory.api.enums.RoomTypePriceTypeEnum;
import com.mk.taskfactory.api.ods.OnlineHotelPriorityService;
import com.mk.taskfactory.api.ods.OnlineHotelRoomTypeService;
import com.mk.taskfactory.api.ods.OnlineHotelService;
import com.mk.taskfactory.api.ots.FacilityService;
import com.mk.taskfactory.api.ots.OtsHotelImageService;
import com.mk.taskfactory.api.ots.TCityListService;
import com.mk.taskfactory.biz.impl.ots.HotelRemoteService;
import com.mk.taskfactory.biz.mapper.crawer.TmpMappingRoomTypeIdMapper;
import com.mk.taskfactory.biz.mapper.crawer.ValidRoomTypeMapper;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.mapper.ots.HotelScoreMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.HotelScore;
import com.mk.taskfactory.model.THotel;
import com.mk.taskfactory.api.dtos.crawer.ValidRoomType;
import com.mk.taskfactory.model.crawer.TmpMappingRoomTypeId;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class QHotelToRedisServiceImpl implements QHotelToRedisService {
    private static Logger logger = LoggerFactory.getLogger(QHotelToRedisServiceImpl.class);

    @Autowired
    private QHotelService hotelService;
    @Autowired
    private QCommentService commentService;
    @Autowired
    private QCommentDetailService commentDetailService;
    @Autowired
    private QFacilityService facilityService;
    @Autowired
    private QHotelFacilityService hotelFacilityService;
    @Autowired
    private QHotelRoomTypeService hotelRoomTypeService;
    @Autowired
    private QHotelSurroundService hotelSurroundService;
    @Autowired
    private FacilityService otsFacilityService;
    @Autowired
    private OtsHotelImageService otsHotelImageService;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private ValidRoomTypeMapper validPriceMapper;
    @Autowired
    private HotelScoreMapper hotelScoreMapper;
    @Autowired
    private QHotelRoomTypeMinPriceService minPriceService;
    @Autowired
    private RoomTypePriceService roomTypePriceService;
    @Autowired
    private TmpMappingRoomTypeIdMapper tmpMappingRoomTypeIdMapper;
    @Autowired
    private TQunarHotelService qunarHotelService;
    @Autowired
    private TCityListService cityListService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private OnlineHotelService onlineHotelService;
    @Autowired
    private OnlineHotelRoomTypeService onlineHotelRoomTypeService;
    @Autowired
    private OnlineHotelPriorityService onlineHotelPriorityService;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    private static ExecutorService pool = Executors.newFixedThreadPool(64);

    public Map<String,Object> onlineHotelToRedis(OnlineHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("onlineHotelToRedis","OnlineHotel同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qHotelToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = onlineHotelService.count(dto);
        if (count<=0){
            resultMap.put("message","OnlineHotel count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<OnlineHotelDto> onlineHotelDtoList = onlineHotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(onlineHotelDtoList)){
                logger.info(String.format("\n====================onlineHotelDtoList is empty====================\n"));
                continue;
            }

            for (final OnlineHotelDto onlineHotelDto:onlineHotelDtoList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================id={}&hotelId={}====================\n")
                                    ,onlineHotelDto.getId(),onlineHotelDto.getHotelId());
                            if (onlineHotelDto.getHotelId()!=null&&"T".equals(onlineHotelDto.getIsVaild())) {
                                hotelScoreToRedis(onlineHotelDto);
                                if (HotelSourceEnum.OTA.getCode().equals(onlineHotelDto.getComefromtype())) {
                                    QHotelDto qHotelDto = new QHotelDto();
                                    qHotelDto.setId(onlineHotelDto.getHotelId());
                                    qHotelDto = hotelService.getByPramas(qHotelDto);
                                    if (qHotelDto == null || qHotelDto.getId() == null) {
                                        return;
                                    }
                                    qHotelDto.setHotelSource(HotelSourceEnum.OTA.getCode());
                                    jedis.set(String.format("%s%s", RedisCacheName.HOTELJSONINFO,
                                            qHotelDto.getId()), JsonUtils.toJSONString(qHotelDto)
                                    );
                                    qHotelFacilityToRedis(onlineHotelDto);
                                    TExHotelImageDto hotelImageDto = new TExHotelImageDto();
                                    hotelImageDto.setHotelSourceId(qHotelDto.getSourceId());
                                    hotelImageDto.setTag("外观");
                                    List<TExHotelImageDto> hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                    int mark = 0;
                                    if (CollectionUtils.isEmpty(hotelImageDtoList)) {
                                        hotelImageDto.setTag("客房");
                                        hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                        if (CollectionUtils.isEmpty(hotelImageDtoList)) {
                                            hotelImageDto.setTag("大厅");
                                            hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                            if (CollectionUtils.isEmpty(hotelImageDtoList)) {
                                                logger.info(String.format("\n====================hotelImageDtoList is empty====================\n"));
                                                return;
                                            }
                                        }
                                        mark++;
                                    }
                                    for (TExHotelImageDto imageDto : hotelImageDtoList) {
                                        HotelImgDto hotelImgDto = new HotelImgDto();
                                        BeanUtils.copyProperties(imageDto, hotelImgDto);

                                        jedis.sadd(String.format("%s%s", RedisCacheName.HOTEL_PICTURE_INFOS_SET,
                                                qHotelDto.getId()), JsonUtils.toJSONString(hotelImgDto)
                                        );
                                        if (mark > 0) {
                                            break;
                                        }
                                    }
                                } else {
                                    THotelDto tHotelDto = new THotelDto();
                                    tHotelDto.setId(onlineHotelDto.getHotelId().intValue());
                                    THotel hotel = hotelMapper.getByPramas(tHotelDto);
                                    if (hotel == null || hotel.getId() == null) {
                                        logger.info(String.format("\n====================Hotel={}====================\n")
                                                , hotel);
                                        return;
                                    }
                                    BeanUtils.copyProperties(hotel, tHotelDto);
                                    tHotelDto.setHotelSource(HotelSourceEnum.LEZHU.getCode());
                                    tHotelDto.setSourceId(tHotelDto.getId().toString());
                                    jedis.set(String.format("%s%s", RedisCacheName.HOTELJSONINFO,
                                            hotel.getId()), JsonUtils.toJSONString(tHotelDto)
                                    );

                                }
                                hotelRemoteService.hotelInit(onlineHotelDto.getHotelId().toString());
                            }else{
                                jedis.del(String.format("%s%s", RedisCacheName.HOTELJSONINFO,
                                        onlineHotelDto.getHotelId()));
                                jedis.del(String.format("%s%s", RedisCacheName.HOTEL_PICTURE_INFOS_SET,
                                        onlineHotelDto.getHotelId())
                                );
                                hotelRemoteService.indexerDrop(onlineHotelDto.getHotelId().toString());

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("onlineHotelToRedis", "OnlineHotel同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================onlineHotelToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    /**
     * 酒店来源
     */
    public Map<String,Object> hotelResourceToRedis(OnlineHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("hotelResourceToRedis","hotelResourceToRedis同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================hotelResourceToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = onlineHotelService.count(dto);
        if (count<=0){
            resultMap.put("message","OnlineHotel count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<OnlineHotelDto> onlineHotelDtoList = onlineHotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(onlineHotelDtoList)){
                logger.info(String.format("\n====================onlineHotelDtoList is empty====================\n"));
                continue;
            }

            for (final OnlineHotelDto onlineHotelDto:onlineHotelDtoList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================id={}&hotelId={}====================\n")
                                    ,onlineHotelDto.getId(),onlineHotelDto.getHotelId());
                            if (onlineHotelDto.getHotelId()!=null&&"T".equals(onlineHotelDto.getIsVaild())) {
                                if (HotelSourceEnum.OTA.getCode().equals(onlineHotelDto.getComefromtype())) {
                                    jedis.set(String.format("%s%s", RedisCacheName.HOTEL_SOURCE,
                                            onlineHotelDto.getHotelId()), HotelSourceEnum.OTA.getCode().toString()
                                    );
                                } else {
                                    jedis.set(String.format("%s%s", RedisCacheName.HOTEL_SOURCE,
                                            onlineHotelDto.getHotelId()), HotelSourceEnum.LEZHU.getCode().toString()
                                    );

                                }
                            }else{
                                jedis.del(String.format("%s%s", RedisCacheName.HOTEL_SOURCE,
                                        onlineHotelDto.getHotelId())
                                );
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("hotelResourceToRedis", "hotelResourceToRedis同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================hotelResourceToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    /**
     * 把roomTypeId he t_ex_hotel_hotel_img id 关联起来 即给每个房型加上图片
     * 此步骤意义在刷新酒店房型时给酒店房型分配不同图片
     */
    public Map<String,Object> roomTypePicMappingToRedis(OnlineHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        logger.info(String.format("\n====================roomTypePicMappingToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = onlineHotelService.count(dto);
        if (count<=0){
            resultMap.put("message","OnlineHotel count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<OnlineHotelDto> onlineHotelDtoList = onlineHotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(onlineHotelDtoList)){
                logger.info(String.format("\n====================onlineHotelDtoList is empty====================\n"));
                continue;
            }

            for (final OnlineHotelDto onlineHotelDto:onlineHotelDtoList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        logger.info(String.format("\n====================id={}&hotelId={}====================\n")
                                ,onlineHotelDto.getId(),onlineHotelDto.getHotelId());
                        if (onlineHotelDto.getHotelId()!=null) {
                            if (HotelSourceEnum.OTA.getCode()==onlineHotelDto.getComefromtype()) {
                                Jedis jedis = null;
                                try {
                                    jedis =  RedisUtil.getJedis();
                                    QHotelRoomtypeDto hotelRoomtype = new QHotelRoomtypeDto();
                                    hotelRoomtype.setHotelId(onlineHotelDto.getHotelId());
                                    List<QHotelRoomtypeDto> hotelRoomtypeList = hotelRoomTypeService.qureyByPramas(hotelRoomtype);
                                    if (CollectionUtils.isEmpty(hotelRoomtypeList)) {
                                        logger.info(String.format("\n====================hotelRoomtypeList isEmpty====================\n"));
                                        return;
                                    }
                                    int p = 0;
                                    for (QHotelRoomtypeDto roomtypeDto : hotelRoomtypeList) {
                                        if ("F".equals(onlineHotelDto.getIsVaild())) {
                                            jedis.del(String.format("%s%s", RedisCacheName.roomType_pic_mapping,
                                                    roomtypeDto.getId())
                                            );
                                            continue;
                                        }
                                        QHotelRoomtypeDto getImgBean = new QHotelRoomtypeDto();
                                        TExHotelImageDto hotelImageDto = new TExHotelImageDto();
                                        getImgBean.setHotelSourceId(roomtypeDto.getHotelSourceId());
                                        getImgBean.setRoomtypeKey(roomtypeDto.getRoomtypeKey());
                                        getImgBean = hotelRoomTypeService.getRoomtypeImg(getImgBean);
                                        if (getImgBean == null || getImgBean.getId() == null) {
                                            hotelImageDto.setHotelSourceId(roomtypeDto.getHotelSourceId());
                                            hotelImageDto.setTag("客房");
                                            List<TExHotelImageDto> hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                            if (!CollectionUtils.isEmpty(hotelImageDtoList)) {
                                                if (hotelImageDtoList.size() <= p) {
                                                    p = 0;
                                                }
                                                hotelImageDto = hotelImageDtoList.get(p);
                                                p++;
                                            }
                                        } else {
                                            hotelImageDto.setId(getImgBean.getId());
                                            hotelImageDto = otsHotelImageService.getByPramas(hotelImageDto);
                                        }
                                        if (hotelImageDto != null && hotelImageDto.getId() != null) {
                                            jedis.set(String.format("%s%s", RedisCacheName.roomType_pic_mapping,
                                                    roomtypeDto.getId()), hotelImageDto.getId().toString()
                                            );
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }finally {
                                    if(null != jedis){
                                        jedis.close();
                                    }

                                }
                            }
                        }



                    }
                });

            }

        }
        logger.info(String.format("\n====================roomTypePicMappingToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> onlineRoomTypeToRedis(OnlineHotelRoomTypeDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("onlineRoomTypeToRedis","OnlineRoomType同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================onlineRoomTypeToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = onlineHotelRoomTypeService.count(dto);
        if (count<=0){
            resultMap.put("message","OnlineHotelRoomType count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<OnlineHotelRoomTypeDto> roomTypeDtoList = onlineHotelRoomTypeService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(roomTypeDtoList)){
                logger.info(String.format("\n====================roomTypeDtoList is empty====================\n"));
                continue;
            }

            for (final OnlineHotelRoomTypeDto roomTypeDto:roomTypeDtoList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================id={}&hotelId={}&roomTypeId={}====================\n")
                                    ,roomTypeDto.getId(),roomTypeDto.getHotelId(),roomTypeDto.getRoomTypeId());
                            if (roomTypeDto.getHotelId()!=null) {
                                String hotelSource = jedis.get(String.format("%s%s", RedisCacheName.HOTEL_SOURCE, roomTypeDto.getHotelId().toString()));
                                if (StringUtils.isEmpty(hotelSource)) {
                                    logger.info(String.format("\n====================hotelSource is Empty====================\n"));
                                    return;
                                }
                                Map<String, String> priceMap = new HashMap<String, String>();
                                priceMap.put("price", roomTypeDto.getPrice().toString());
                                if ("T".equals(roomTypeDto.getIsOtaPrice())) {
                                    priceMap.put("type", RoomTypePriceTypeEnum.OTA.getCode().toString());

                                } else {
                                    priceMap.put("type", RoomTypePriceTypeEnum.DYNAMIC.getCode().toString());
                                }
                                if("T".equals(roomTypeDto.getIsVaild())) {
                                    jedis.set(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_DYNAMIC_PRICE,
                                            roomTypeDto.getRoomTypeId()), JsonUtils.toJSONString(priceMap)
                                    );
                                }else {
                                    jedis.del(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_DYNAMIC_PRICE,
                                            roomTypeDto.getRoomTypeId())
                                    );
                                }

                                if (HotelSourceEnum.OTA.getCode()==Integer.valueOf(hotelSource)) {
                                    Set<String> hotelRoomTypeSet = jedis.smembers(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
                                            roomTypeDto.getHotelId()));
                                    for (String roomType: hotelRoomTypeSet){
                                        RoomtypeToRedisDto roomtypeToRedisDto = JsonUtils.formatJson(roomType,RoomtypeToRedisDto.class);
                                        if (roomtypeToRedisDto==null||roomtypeToRedisDto.getId()==null){
                                            continue;
                                        }
                                        if (roomtypeToRedisDto.getId().equals(roomTypeDto.getRoomTypeId())){
                                            jedis.srem(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
                                                    roomTypeDto.getHotelId()), roomType
                                            );
                                        }
                                    }
                                    QHotelRoomtypeDto qHotelRoomtype = new QHotelRoomtypeDto();
                                    qHotelRoomtype.setId(roomTypeDto.getRoomTypeId());
                                    qHotelRoomtype = hotelRoomTypeService.getByPramas(qHotelRoomtype);
                                    if (qHotelRoomtype == null || qHotelRoomtype.getId() == null) {
                                        logger.info(String.format("\n====================qHotelRoomtype isEmpty====================\n"));
                                        return;
                                    }
                                    RoomtypeToRedisDto bean = new RoomtypeToRedisDto();
                                    QHotelRoomtypeDto getImgBean = new QHotelRoomtypeDto();
                                    TExHotelImageDto hotelImageDto = new TExHotelImageDto();
                                    String picId = jedis.get(String.format("%s%s", RedisCacheName.roomType_pic_mapping, qHotelRoomtype.getId()));
                                    if (StringUtils.isEmpty(picId)) {
                                        getImgBean.setHotelSourceId(qHotelRoomtype.getHotelSourceId());
                                        getImgBean.setRoomtypeKey(qHotelRoomtype.getRoomtypeKey());
                                        getImgBean = hotelRoomTypeService.getRoomtypeImg(getImgBean);
                                        if (getImgBean == null || getImgBean.getId() == null) {
                                            hotelImageDto.setHotelSourceId(qHotelRoomtype.getHotelSourceId());
                                            hotelImageDto.setTag("客房");
                                            List<TExHotelImageDto> hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                            if (!CollectionUtils.isEmpty(hotelImageDtoList)) {
                                                hotelImageDto = hotelImageDtoList.get(0);
                                            }
                                        } else {
                                            hotelImageDto.setId(getImgBean.getId());
                                            hotelImageDto = otsHotelImageService.getByPramas(hotelImageDto);
                                        }
                                    } else {
                                        hotelImageDto.setId(new Long(picId));
                                        hotelImageDto = otsHotelImageService.getByPramas(hotelImageDto);
                                    }
                                    BeanUtils.copyProperties(qHotelRoomtype, bean);
                                    bean.setImageUrl(hotelImageDto.getBig());
                                    bean.setSmallImageUrl(hotelImageDto.getUrl());
                                    if("T".equals(roomTypeDto.getIsVaild())) {
                                        jedis.sadd(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
                                                roomTypeDto.getHotelId()), JsonUtils.toJSONString(bean)
                                        );
                                        jedis.set(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFO,
                                                roomTypeDto.getRoomTypeId()), JsonUtils.toJSONString(bean)
                                        );
                                    }else{
                                        jedis.del(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFO,
                                                roomTypeDto.getRoomTypeId())
                                        );
                                    }

                                }else {
                                    if ("T".equals(roomTypeDto.getIsVaild())) {
                                        jedis.set(String.format("%s%s", RedisCacheName.LEZHU_ONLINE_ROOMTYPE,
                                                roomTypeDto.getRoomTypeId()), "1"
                                        );
                                    } else {
                                        jedis.del(String.format("%s%s", RedisCacheName.LEZHU_ONLINE_ROOMTYPE,
                                                roomTypeDto.getRoomTypeId())
                                        );
                                    }
                                }

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("onlineRoomTypeToRedis", "OnlineRoomType同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================onlineRoomTypeToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public void qHotelFacilityToRedis(OnlineHotelDto dto){
        Jedis jedis = null;
        try {
            jedis =  RedisUtil.getJedis();
            logger.info(String.format("\n====================hotelId={}====================\n")
                    ,dto.getHotelId());
            if (dto==null||dto.getHotelId()==null) {
                return;
            }
            if ("T".equals(dto.getIsVaild())) {
                QHotelFacilityDto hotelFacilityDto = new QHotelFacilityDto();
                hotelFacilityDto.setHotelId(dto.getHotelId());
                List<QHotelFacilityDto> hotelFacilityDtoList = hotelFacilityService.qureyJionFacility(hotelFacilityDto);
                if (CollectionUtils.isEmpty(hotelFacilityDtoList)) {
                    return;
                }
                for (QHotelFacilityDto facilityDto : hotelFacilityDtoList) {
                    if (StringUtils.isEmpty(facilityDto.getOtsId())) {
                        continue;
                    }
                    String facility = jedis.get(String.format("%s%s", RedisCacheName.LEZHUFACILITY,
                            facilityDto.getOtsId()));
                    if (StringUtils.isEmpty(facility)){
                        logger.info(String.format("\n====================facility on redis is empty====================\n"));
                        continue;
                    }
                    jedis.sadd(String.format("%s%s", RedisCacheName.HOTELFACILITYINFOSET,
                            dto.getHotelId()), facility
                    );
                }

            }else {
                jedis.del(String.format("%s%s", RedisCacheName.HOTELFACILITYINFOSET,
                        dto.getHotelId())
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
            }

        }

    }
    public Map<String,Object> tFacilityToRedis(TFacilityDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("tFacilityToRedis","TFacility同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================tFacilityToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));

        dto.setVisible("T");
        List<TFacilityDto> facilityDtoList= otsFacilityService.qureyByPramas(dto);
        if (CollectionUtils.isEmpty(facilityDtoList)){
            logger.info(String.format("\n====================facilityDtoList is empty====================\n"));
            resultMap.put("message","TFacility is empty");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        Jedis jedis = null;
        for (TFacilityDto facilityDto:facilityDtoList){
            try {
                jedis =  RedisUtil.getJedis();
                logger.info(String.format("\n====================id={}====================\n")
                        ,facilityDto.getId());
                if (facilityDto.getId()!=null){
                    jedis.set(String.format("%s%s", RedisCacheName.LEZHUFACILITY,
                            facilityDto.getId()), JsonUtils.toJSONString(facilityDto)
                    );
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("tFacilityToRedis::error::id:"+facilityDto.getId());
                logger.error("tFacilityToRedis::error{}{}",facilityDto.getId(),e.getMessage());
            }finally {
                if(null != jedis){
                    jedis.close();
                }

            }


        }
        Cat.logEvent("tFacilityToRedis", "TFacility同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================tFacilityToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public Map<String,Object> cityHotelSetToRedis(TCityListDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("cityHotelSetToRedis","cityHotelSet同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================cityHotelSetToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        List<TCityListDto> cityDtoList = cityListService.qureyByPramas(dto);
        if (CollectionUtils.isEmpty(cityDtoList)){
            logger.info(String.format("\n====================cityList is empty====================\n"));
            resultMap.put("message","cityList is empty");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }

        for (final TCityListDto bean:cityDtoList){
            Jedis jedis = null;
            try {
                jedis =  RedisUtil.getJedis();
                logger.info(String.format("\n====================cityCode={}&cityName={}====================\n")
                        ,bean.getCityCode(),bean.getCityName());
                jedis.del(String.format("%s%s", RedisCacheName.CITYHOTELSET,
                        bean.getCityCode()));
                if ("F".equals(bean.getValid())){
                    logger.info(String.format("\n====================cityCode={} valid=F continue====================\n")
                            ,bean.getCityCode(),bean.getCityName());
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(null != jedis){
                    jedis.close();
                }

            }
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = null;
                    try {
                        jedis =  RedisUtil.getJedis();
                        logger.info(String.format("\n====================cityCode={}&cityName={}====================\n")
                                ,bean.getCityCode(),bean.getCityName());
                        if (StringUtils.isEmpty(bean.getCityCode())){
                            return;
                        }
                        OnlineHotelDto onlineHotelDto = new OnlineHotelDto();
                        onlineHotelDto.setCityCode(Integer.valueOf(bean.getCityCode()));
                        onlineHotelDto.setIsVaild("T");
                        List<OnlineHotelDto> onlineHotelDtoList = onlineHotelService.qureyByPramas(onlineHotelDto);
                        for (OnlineHotelDto qBean : onlineHotelDtoList){
                            logger.info(String.format("\n====================id={}&cityCode={}&hotelId={}====================\n")
                                    ,qBean.getId(),bean.getCityCode(),qBean.getHotelId());
                            String hotelInfo = jedis.get(String.format("%s%s", RedisCacheName.HOTELJSONINFO,
                                    qBean.getHotelId()));
                            if (StringUtils.isNotEmpty(hotelInfo)){
                                jedis.sadd(String.format("%s%s", RedisCacheName.CITYHOTELSET,
                                        bean.getCityCode()), hotelInfo);
                            }

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("validPriceRoomTypeToRedis::error::cityCode:"+bean.getCityCode());
                        logger.error("validPriceRoomTypeToRedis::error{}{}",bean.getCityCode(),e.getMessage());
                    }finally {
                        if(null != jedis){
                            jedis.close();
                        }

                    }

                }
            });

        }

        Cat.logEvent("cityHotelSetToRedis", "cityHotelSet同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================cityHotelSetToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public void hotelScoreToRedis(OnlineHotelDto dto){
        if (dto==null||dto.getHotelId()==null){
            return;
        }
        HotelScoreDto hotelScoreDto = new HotelScoreDto();
        hotelScoreDto.setHotelId(dto.getHotelId());
        HotelScore hotelScore = hotelScoreMapper.getByPramas(hotelScoreDto);
        if (hotelScore==null||hotelScore.getHotelId()==null){
            logger.info(String.format("====================hotelId={} hotelScore is null===================="),dto.getHotelId());
            return;
        }
        Jedis jedis = null;
        try {
            jedis =  RedisUtil.getJedis();
            logger.info(String.format("\n====================hotelId={} set score====================\n"),hotelScore.getHotelId());

            if ("T".equals(dto.getIsVaild())){
                if (hotelScore.getHotelId()!=null&&StringUtils.isNotEmpty(hotelScore.getGrade())){
                    jedis.set(String.format("%s%s", RedisCacheName.HOTEL_SCORE_INFO,
                            hotelScore.getHotelId()), hotelScore.getGrade());

                }
            }else {
                jedis.del(String.format("%s%s", RedisCacheName.HOTEL_SCORE_INFO,
                        dto.getHotelId())
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
            }

        }

    }

    public void tQunarHotelScoreToRedis(OnlineHotelDto dto){
        QHotelDto qHotelDto = new QHotelDto();
        qHotelDto.setId(dto.getHotelId());
        qHotelDto = hotelService.getByPramas(qHotelDto);
        if (qHotelDto==null||qHotelDto.getId()==null){
            return;
        }
        TQunarHotelDto qunarHotelDto = new TQunarHotelDto();
        qunarHotelDto.setSourceId(qHotelDto.getSourceId());
        qunarHotelDto = qunarHotelService.getByPramas(qunarHotelDto);
        if (qunarHotelDto==null||qunarHotelDto.getId()==null){
            return;
        }
        Jedis jedis = null;
        try {
            jedis =  RedisUtil.getJedis();
            logger.info(String.format("\n====================hotelId={} set score====================\n")
                    ,dto.getHotelId());
            if ("T".equals(dto.getIsVaild())){
                jedis.set(String.format("%s%s", RedisCacheName.HOTEL_SCORE_INFO,
                        dto.getHotelId()), qunarHotelDto.getCommentScore().toString()
                );

            }else {
                jedis.del(String.format("%s%s", RedisCacheName.HOTEL_SCORE_INFO,
                        dto.getHotelId())
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
            }

        }

    }
    public void indexerjob(){
        String postResult= hotelRemoteService.indexerjob();
        logger.info(String.format("\n===================={}====================\n")
                , postResult);
    }
    public Map<String,Object> onlineCityToRedis(TCityListDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("onlineCityToRedis","OnlineCity同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================onlineCityToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        List<TCityListDto> cityDtoList = cityListService.qureyByPramas(dto);
        if (CollectionUtils.isEmpty(cityDtoList)){
            logger.info(String.format("\n====================hotelList is empty====================\n"));
            resultMap.put("message","hotelList is empty");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        Jedis jedis = null;
        try {
            jedis =  RedisUtil.getJedis();
            jedis.del(String.format("%s", RedisCacheName.CITY_INFO_SET));

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != jedis){
                jedis.close();
            }

        }
        for (final TCityListDto cityDto:cityDtoList){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = null;
                    try {
                        jedis =  RedisUtil.getJedis();

                        if (cityDto.getCityCode()==null) {
                            logger.info(String.format("\n====================cityCode isEmpty====================\n"));
                            return;
                        }
                        logger.info(String.format("\n====================cityName={}&cityCode={}====================\n")
                                ,cityDto.getCityName(),cityDto.getCityCode());
                        OnlineHotelDto onlineHotelDto = new OnlineHotelDto();
                        onlineHotelDto.setCityCode(Integer.valueOf(cityDto.getCityCode()));
                        onlineHotelDto.setIsVaild("T");
                        onlineHotelDto = onlineHotelService.getByPramas(onlineHotelDto);
                        TCityListDto cityListDto = new TCityListDto();
                        cityListDto.setId(cityDto.getId());
                        if (onlineHotelDto!=null&&onlineHotelDto.getId()!=null){
                            logger.info(String.format("\n====================set cityCode={}====================\n")
                                    ,cityDto.getCityCode());
                            jedis.sadd(String.format("%s", RedisCacheName.CITY_INFO_SET), JsonUtils.toJSONString(cityDto)
                            );
                            cityListDto.setValid("T");
                            cityListService.updateById(cityListDto);
                            return;
                        }else {
                            logger.info(String.format("\n====================remove cityCode={}====================\n")
                                    ,cityDto.getCityCode());
                            cityListDto.setValid("F");
                            cityListService.updateById(cityListDto);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(null != jedis){
                            jedis.close();
                        }

                    }

                }
            });

        }

        Cat.logEvent("onlineCityToRedis", "OnlineCity同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================onlineCityToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> hotelPriorityToRedis(OnlineHotelPriorityDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        hotelRemoteService.initcity();//刷新城市列表  此代码和job时间先后顺序有关
        logger.info(String.format("\n====================hotelPriorityToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = onlineHotelPriorityService.count(dto);
        if (count<=0){
            resultMap.put("message","OnlineHotelPriority count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=count/pageSize;
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,count,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<OnlineHotelPriorityDto> dataList = onlineHotelPriorityService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(dataList)){
                logger.info(String.format("\n====================dataList is empty====================\n"));
                continue;
            }

            for (final OnlineHotelPriorityDto hotelPriorityDto:dataList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================id={}&hotelId={}====================\n")
                                    ,hotelPriorityDto.getId(),hotelPriorityDto.getHotelId());
                            if (hotelPriorityDto.getHotelId()==null||hotelPriorityDto.getPriority()==null) {
                                return;
                            }
                            jedis.set(String.format("%s%s", RedisCacheName.HOTEL_PRIORITY,
                                    hotelPriorityDto.getHotelId()), hotelPriorityDto.getPriority().toString()
                            );
                             if (HotelSourceEnum.OTA.getCode()==hotelPriorityDto.getComefromtype()) {
                                 QHotelDto qHotelDto =new QHotelDto();
                                 qHotelDto.setId(hotelPriorityDto.getHotelId());
                                 qHotelDto = hotelService.getByPramas(qHotelDto);
                                 if (qHotelDto==null||qHotelDto.getId()==null){
                                     logger.info(String.format("\n====================qHotelDto is null====================\n"));
                                     return;
                                 }
                                 qHotelDto.setPriority(hotelPriorityDto.getPriority());
                                 hotelService.updateById(qHotelDto);

                            }else {
                                 THotelDto tHotelDto =new THotelDto();
                                 tHotelDto.setId(hotelPriorityDto.getHotelId().intValue());
                                 THotel hotel = hotelMapper.getByPramas(tHotelDto);
                                 if (hotel==null||hotel.getId()==null){
                                     logger.info(String.format("\n====================hotel is null====================\n"));
                                     return;
                                 }
                                 tHotelDto.setPriority(hotelPriorityDto.getPriority());
                                 hotelMapper.updateById(tHotelDto);
                             }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        logger.info(String.format("\n====================roomTypePicMappingToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
//    public Map<String,Object> qCommentToRedis(QCommentDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("qCommentToRedis","QComment同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================qCommentToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//
//        int count = commentService.count(dto);
//        if (count<=0){
//            resultMap.put("message","Comment count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QCommentDto> commentList = commentService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(commentList)){
//                logger.info(String.format("\n====================commentList is empty====================\n"));
//                continue;
//            }
//
//            for (final QCommentDto commentDto:commentList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================commentId={}&hotelId={}====================\n")
//                                    ,commentDto.getId(),commentDto.getHotelId());
//                            if (commentDto.getHotelId()!=null){
//                                jedis.set(String.format("%s%s", RedisCacheName.HOTELCOMMENTINFO,
//                                        commentDto.getHotelId()), JsonUtils.toJSONString(commentDto)
//                                );
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("qHotelToRedis::error::hotelid:"+commentDto.getHotelId());
//                            logger.error("qHotelToRedis::error{}{}",commentDto.getHotelId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("qCommentToRedis", "QComment同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================qCommentToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }
//
//    public Map<String,Object> qCommentDetailToRedis(QCommentDetailDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("qCommentDetailToRedis","QCommentDetail同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================qCommentDetailToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//
//        int count = commentDetailService.count(dto);
//        if (count<=0){
//            resultMap.put("message","QCommentDetail count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QCommentDetailDto> commentDetailList = commentDetailService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(commentDetailList)){
//                logger.info(String.format("\n====================commentDetailList is empty====================\n"));
//                continue;
//            }
//
//            for (final QCommentDetailDto commentDetailDto:commentDetailList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================hotelId={}====================\n")
//                                    ,commentDetailDto.getHotelId());
//                            if (commentDetailDto.getHotelId()!=null){
//                                jedis.set(String.format("%s%s", RedisCacheName.HOTELCOMMENTDETAILINFO,
//                                        commentDetailDto.getCommentId()), JsonUtils.toJSONString(commentDetailDto)
//                                );
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("qCommentDetailToRedis::error::commentId:"+commentDetailDto.getCommentId());
//                            logger.error("qCommentDetailToRedis::error{}{}",commentDetailDto.getCommentId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("qCommentDetailToRedis", "QCommentDetail同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================qCommentDetailToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }

//    public Map<String,Object> qFacilityToRedis(QFacilityDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("qFacilityToRedis","QFacility同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================qFacilityToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//
//        int count = facilityService.count(dto);
//        if (count<=0){
//            resultMap.put("message","QFacility count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QFacilityDto> facilityList = facilityService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(facilityList)){
//                logger.info(String.format("\n====================hotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final QFacilityDto facilityDto:facilityList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================id={}====================\n")
//                                    ,facilityDto.getId());
//                            if (facilityDto.getId()!=null){
//                                jedis.set(String.format("%s%s", RedisCacheName.FACILITYINFO,
//                                        facilityDto.getId()), JsonUtils.toJSONString(facilityDto)
//                                );
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("qHotelToRedis::error::id:"+facilityDto.getId());
//                            logger.error("qHotelToRedis::error{}{}",facilityDto.getId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("qFacilityToRedis", "QFacility同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================qFacilityToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }


//    public Map<String,Object> qHotelRoomtypeToRedis(QHotelRoomtypeDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("qHotelRoomtypeToRedis","QHotelRoomtype同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================qHotelRoomtypeToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = hotelRoomTypeService.count(dto);
//        if (count<=0){
//            resultMap.put("message","QHotel count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pages={}&pageIndex={}====================\n")
//                    ,i,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QHotelRoomtypeDto> roomtypeDtoList = hotelRoomTypeService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(roomtypeDtoList)){
//                logger.info(String.format("\n====================hotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final QHotelRoomtypeDto roomtypeDto:roomtypeDtoList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================id={}====================\n")
//                                    ,roomtypeDto.getId());
//                            if (roomtypeDto.getId()!=null&&"T".equals(roomtypeDto.getRoomTypeValid())){
//                                RoomtypeToRedisDto bean = new RoomtypeToRedisDto();
//                                QHotelRoomtypeDto getImgBean = new QHotelRoomtypeDto();
//                                TExHotelImageDto hotelImageDto = new TExHotelImageDto();
//
//                                getImgBean.setHotelSourceId(roomtypeDto.getHotelSourceId());
//                                getImgBean.setRoomtypeKey(roomtypeDto.getRoomtypeKey());
//                                getImgBean=hotelRoomTypeService.getRoomtypeImg(getImgBean);
//                                if (getImgBean==null||getImgBean.getId()==null){
//                                    hotelImageDto.setHotelSourceId(roomtypeDto.getHotelSourceId());
//                                    hotelImageDto.setTag("客房");
//                                    List<TExHotelImageDto> hotelImageDtoList=otsHotelImageService.qureyByPramas(hotelImageDto);
//                                    if (!CollectionUtils.isEmpty(hotelImageDtoList)){
//                                        hotelImageDto=hotelImageDtoList.get(0);
//                                    }
//                                }else {
//                                    hotelImageDto.setId(getImgBean.getId());
//                                    hotelImageDto=otsHotelImageService.getByPramas(hotelImageDto);
//                                }
//                                hotelImageDto.setId(getImgBean.getId());
//                                hotelImageDto=otsHotelImageService.getByPramas(hotelImageDto);
//                                BeanUtils.copyProperties(roomtypeDto, bean);
//                                bean.setImageUrl(hotelImageDto.getBig());
//                                bean.setSmallImageUrl(hotelImageDto.getUrl());
//                                jedis.set(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFO,
//                                        roomtypeDto.getId()), JsonUtils.toJSONString(bean)
//                                );
//                            }else {
//                                jedis.del(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFO,
//                                        roomtypeDto.getId())
//                                );
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("qHotelRoomtypeToRedis::error::id:"+roomtypeDto.getId());
//                            logger.error("qHotelRoomtypeToRedis::error{}{}",roomtypeDto.getId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("qHotelRoomtypeToRedis", "QHotelRoomtype同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================qHotelRoomtypeToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }

//    public Map<String,Object> qHotelSurroundSetToRedis(QHotelDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("qHotelSurroundToRedis","QHotelSurround同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================qHotelSurroundToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = hotelService.count(dto);
//        if (count<=0){
//            resultMap.put("message","QHotel count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(hotelList)){
//                logger.info(String.format("\n====================hotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final QHotelDto hotelDto:hotelList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================hotelId={}====================\n")
//                                    ,hotelDto.getId());
//                            if (hotelDto.getSourceId()!=null&&"T".equals(hotelDto.getRoomTypeValid())){
//                                QHotelSurroundDto hotelSurroundDto = new QHotelSurroundDto();
//                                hotelSurroundDto.setHotelId(hotelDto.getId().toString());
//                                List<QHotelSurroundDto> hotelSurroundDtoList = hotelSurroundService.qureyByPramas(hotelSurroundDto);
//                                if (CollectionUtils.isEmpty(hotelSurroundDtoList)){
//                                    return;
//                                }
//                                jedis.set(String.format("%s%s", RedisCacheName.HOTELSURROUNDINFOSET,
//                                        hotelDto.getId()), JsonUtils.toJSONString(hotelSurroundDtoList)
//                                );
//                            }else {
//                                jedis.del(String.format("%s%s", RedisCacheName.HOTELSURROUNDINFOSET,
//                                        hotelDto.getId())
//                                );
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("qHotelToRedis::error::hotelid:"+hotelDto.getId());
//                            logger.error("qHotelToRedis::error{}{}",hotelDto.getId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("qHotelSurroundToRedis", "QHotelSurround同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================qHotelSurroundToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }


//    public Map<String,Object> tHotelToRedis(ValidRoomType dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("tHotelToRedis","THotel同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================tHotelToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = validPriceMapper.countValidPriceHotel(dto);
//        if (count<=0){
//            resultMap.put("message","ValidPriceHotel count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<ValidRoomType> validPriceHotelList = validPriceMapper.qureyValidPriceHotel(dto);
//
//            if (CollectionUtils.isEmpty(validPriceHotelList)){
//                logger.info(String.format("\n====================validPriceHotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final ValidRoomType validPriceHotel:validPriceHotelList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================hotelId={}====================\n")
//                                    ,validPriceHotel.getHotelId());
//                            if (validPriceHotel.getHotelId()!=null){
//                                THotelDto tHotelDto = new THotelDto();
//                                tHotelDto.setId(new Integer(validPriceHotel.getHotelId().toString()));
//                                THotel hotel = hotelMapper.getByPramas(tHotelDto);
//                                if (hotel==null||hotel.getId()==null){
//                                    logger.info(String.format("\n====================Hotel={}====================\n")
//                                            ,hotel);
//                                    return;
//                                }
//                                BeanUtils.copyProperties(hotel, tHotelDto);
//                                tHotelDto.setHotelSource(1);
//                                tHotelDto.setSourceId(tHotelDto.getId().toString());
//                                jedis.set(String.format("%s%s", RedisCacheName.HOTELJSONINFO,
//                                        hotel.getId()), JsonUtils.toJSONString(tHotelDto)
//                                );
//                                jedis.set(String.format("%s%s", RedisCacheName.HOTEL_SOURCE,
//                                        hotel.getId()), "1"
//                                );
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("tHotelToRedis::error::hotelid:"+validPriceHotel.getHotelId());
//                            logger.error("tHotelToRedis::error{}{}",validPriceHotel.getHotelId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("tHotelToRedis", "THotel同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================tHotelToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }
//    public Map<String,Object> validPriceHotelToRedis(){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("validPriceHotelToRedis","ValidPriceHotel同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================validPriceHotelToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        ValidRoomType dto = new ValidRoomType();
//        int count = validPriceMapper.countValidPriceHotel(dto);
//        if (count<=0){
//            resultMap.put("message","ValidPriceHotel count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<ValidRoomType> validPriceHotelList = validPriceMapper.qureyValidPriceHotel(dto);
//            if (CollectionUtils.isEmpty(validPriceHotelList)){
//                logger.info(String.format("\n====================validPriceHotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final ValidRoomType validPriceHotel:validPriceHotelList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================hotelId={}====================\n")
//                                    ,validPriceHotel.getHotelId());
//                            if (validPriceHotel.getHotelId()!=null){
//                                jedis.set(String.format("%s%s", RedisCacheName.LEZHU_VAILD_PRICE_HOTEL_INFO,
//                                        validPriceHotel.getHotelId()), "1");
//
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("validPriceHotelToRedis::error::hotelid:"+validPriceHotel.getHotelId());
//                            logger.error("validPriceHotelToRedis::error{}{}",validPriceHotel.getHotelId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("validPriceHotelToRedis", "ValidPriceHotel同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================validPriceHotelToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }
//    public Map<String,Object> validPriceRoomTypeToRedis(){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("validPriceRoomTypeToRedis","ValidPriceRoomType同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================validPriceRoomTypeToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = validPriceMapper.countValidPriceRoomtype();
//        if (count<=0){
//            resultMap.put("message","ValidPriceRoomType count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            ValidRoomType dto = new ValidRoomType();
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<ValidRoomType> validPriceHotelList = validPriceMapper.qureyValidPriceRoomType(dto);
//            if (CollectionUtils.isEmpty(validPriceHotelList)){
//                logger.info(String.format("\n====================validPriceHotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final ValidRoomType validPriceHotel:validPriceHotelList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================roomTypeId={}====================\n")
//                                    ,validPriceHotel.getRoomTypeId());
//                            if (validPriceHotel.getRoomTypeId()!=null){
//                                jedis.set(String.format("%s%s", RedisCacheName.LEZHU_VAILD_PRICE_ROOMTYPE_INFO,
//                                        validPriceHotel.getRoomTypeId()), "1");
//                                RoomtypeToRedisDto bean = new RoomtypeToRedisDto();
//                                bean.setHotelId(validPriceHotel.getHotelId());
//                                bean.setId(validPriceHotel.getRoomTypeId());
//                                jedis.sadd(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
//                                        validPriceHotel.getHotelId()), JsonUtils.toJSONString(bean));
//
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("validPriceRoomTypeToRedis::error::roomTypeId:"+validPriceHotel.getRoomTypeId());
//                            logger.error("validPriceRoomTypeToRedis::error{}{}",validPriceHotel.getRoomTypeId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("validPriceRoomTypeToRedis", "ValidPriceRoomType同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================validPriceRoomTypeToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }

//    public Map<String,Object> qHotelRoomTypeMinPriceToRedis(QHotelRoomtypeMinPriceDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("qHotelRoomTypeMinPriceToRedis","QHotelRoomTypeMinPrice同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================qHotelRoomTypeMinPriceToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = minPriceService.count(dto);
//        if (count<=0){
//            resultMap.put("message","QHotelRoomTypePrice count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QHotelRoomtypeMinPriceDto> minPriceDtoList = minPriceService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(minPriceDtoList)){
//                logger.info(String.format("\n====================minPriceDtoList is empty====================\n"));
//                continue;
//            }
//
//            for (final QHotelRoomtypeMinPriceDto minPriceDto:minPriceDtoList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================hotelId={}&roomTypeId={}====================\n")
//                                    ,minPriceDto.getQnHotelId(),minPriceDto.getRoomtypeId());
//                            if (minPriceDto.getRoomtypeId()!=null){
//                                jedis.set(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_MIN_PRICE,
//                                        minPriceDto.getRoomtypeId()), minPriceDto.getMinPrice());
//
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("validPriceRoomTypeToRedis::error::hotelId:"+minPriceDto.getQnHotelId());
//                            logger.error("validPriceRoomTypeToRedis::error{}{}",minPriceDto.getQnHotelId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("qHotelRoomTypeMinPriceToRedis", "HotelRoomTypeMinPrice同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================qHotelRoomTypeMinPriceToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }
//    public Map<String,Object> otaPriceToRedis(QHotelDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("otaPriceToRedis","OTAPrice同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================otaPriceToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = hotelService.count(dto);
//        if (count<=0){
//            resultMap.put("message","QHotel count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(hotelList)){
//                logger.info(String.format("\n====================hotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final QHotelDto hotelDto:hotelList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//
//                            if (hotelDto.getSourceId()!=null){
//                                QHotelRoomtypeDto hotelRoomtype = new QHotelRoomtypeDto();
//                                hotelRoomtype.setHotelSourceId(hotelDto.getSourceId());
//                                List<QHotelRoomtypeDto> hotelRoomtypeList = hotelRoomTypeService.qureyByPramas(hotelRoomtype);
//                                if (CollectionUtils.isEmpty(hotelRoomtypeList)){
//                                    logger.info(String.format("\n====================hotelRoomtypeList isEmpty====================\n"));
//                                    return;
//                                }
//                                int p=0;
//                                for (QHotelRoomtypeDto roomtypeDto:hotelRoomtypeList){
//                                    if ("F".equals(hotelDto.getRoomTypeValid())||"F".equals(roomtypeDto.getRoomTypeValid())){
//                                        jedis.del(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_OTA_PRICE,
//                                                roomtypeDto.getId())
//                                        );
//                                        continue;
//                                    }
//                                    RoomTypePriceDto roomTypePriceDto = new RoomTypePriceDto();
//                                    roomTypePriceDto.setHotelSourceId(roomtypeDto.getHotelSourceId());
//                                    roomTypePriceDto.setRoomTypeKey(roomtypeDto.getRoomtypeKey());
//                                    roomTypePriceDto=roomTypePriceService.getLastMinOtaPrice(roomTypePriceDto);
//                                    if (roomTypePriceDto==null||roomTypePriceDto.getPrice()==null){
//                                        logger.info("===========roomTypePriceDto=null||roomTypePriceDto.getPrice()=============");
//                                        continue;
//                                    }
//                                    logger.info(String.format("\n====================hotelId={}&roomTypeId={}&price={}====================\n")
//                                            ,hotelDto.getId(),roomtypeDto.getId(),roomTypePriceDto.getPrice());
//                                    jedis.set(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_OTA_PRICE,
//                                            roomtypeDto.getId()), roomTypePriceDto.getOriginPrice().toString()
//                                    );
//                                }
//
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            System.out.println("qHotelRoomTypeSetToRedis::error::sourceId:"+hotelDto.getSourceId());
//                            logger.error("qHotelRoomTypeSetToRedis::error{}{}",hotelDto.getSourceId(),e.getMessage());
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("otaPriceToRedis", "OTAPrice同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================otaPriceToRedis  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }
//    public Map<String,Object> roomtypeOldIdToNew(Integer start){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("roomtypeOldIdToNew","RoomTypeOldIdToNew同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================roomtypeOldIdToNew begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = tmpMappingRoomTypeIdMapper.count();
//        if (count<=0){
//            resultMap.put("message","count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        if (start==null){
//            start=0;
//        }
//        for (int i=start;i<=pageCount;i++){
//            logger.info(String.format("\n====================page={}&pageIndex={}====================\n")
//                    ,i,i*pageSize);
//            TmpMappingRoomTypeId tmpMappingRoomTypeId = new TmpMappingRoomTypeId();
//            tmpMappingRoomTypeId.setPageSize(pageSize);
//            tmpMappingRoomTypeId.setPageIndex(i*pageSize);
//            List<TmpMappingRoomTypeId> list = tmpMappingRoomTypeIdMapper.qureyByPramas(tmpMappingRoomTypeId);
//            if (CollectionUtils.isEmpty(list)){
//                logger.info(String.format("\n====================list is empty====================\n"));
//                continue;
//            }
//
//            for (final TmpMappingRoomTypeId dataBean:list){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================oldId={}&newId={}====================\n")
//                                    ,dataBean.getOldId(),dataBean.getNewId());
//                            if (dataBean.getOldId()==null||dataBean.getNewId()==null){
//                                return;
//                            }
////                            String roomtypeStr =jedis.get(String.format("%s%s",RedisCacheName.HOTELROOMTYPEINFO,dataBean.getOldId()));
////                            if (StringUtils.isEmpty(roomtypeStr)){
////                                return;
////                            }
////                            RoomtypeToRedisDto saveBean = new RoomtypeToRedisDto();
////                            saveBean=JsonUtils.formatJson(roomtypeStr,RoomtypeToRedisDto.class);
////                            if (saveBean.getId()==null){
////                                return;
////                            }
////                            saveBean.setId(dataBean.getNewId());
////                            jedis.del(String.format("%s%s",RedisCacheName.HOTELROOMTYPEINFO,dataBean.getOldId()));
////                            jedis.set(String.format("%s%s",RedisCacheName.HOTELROOMTYPEINFO,dataBean.getNewId()),JsonUtils.toJSONString(saveBean));
////
////                            String minPrice =jedis.get(String.format("%s%s",RedisCacheName.HOTEL_ROOMTYPE_MIN_PRICE,dataBean.getOldId()));
////                            if (StringUtils.isEmpty(minPrice)){
////                                return;
////                            }
////                            jedis.set(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_MIN_PRICE,
////                                    dataBean.getNewId()), minPrice);
////                            jedis.del(String.format("%s%s",RedisCacheName.HOTEL_ROOMTYPE_MIN_PRICE,dataBean.getOldId()));
//
//                            String otsPrice =jedis.get(String.format("%s%s",RedisCacheName.HOTEL_ROOMTYPE_OTA_PRICE,dataBean.getOldId()));
//
//                            if (StringUtils.isEmpty(otsPrice)){
//                                return;
//                            }
//                            jedis.set(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_OTA_PRICE,
//                                    dataBean.getNewId()), otsPrice);
//                            jedis.del(String.format("%s%s",RedisCacheName.HOTEL_ROOMTYPE_OTA_PRICE,dataBean.getOldId()));
//
//                            String minPrice =jedis.get(String.format("%s%s",RedisCacheName.HOTEL_ROOMTYPE_MIN_PRICE,dataBean.getOldId()));
//                            if (StringUtils.isEmpty(minPrice)){
//                                return;
//                            }
//                            jedis.set(String.format("%s%s", RedisCacheName.HOTEL_ROOMTYPE_MIN_PRICE,
//                                    dataBean.getNewId()), minPrice);
//                            jedis.del(String.format("%s%s",RedisCacheName.HOTEL_ROOMTYPE_MIN_PRICE,dataBean.getOldId()));
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("roomtypeOldIdToNew", "RoomTypeOldIdToNew同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================roomtypeOldIdToNew  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }
//    public Map<String,Object> temMappingRoomTypeToRedis(Integer start){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        logger.info(String.format("\n====================temMappingRoomTypeToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        int count = tmpMappingRoomTypeIdMapper.count();
//        if (count<=0){
//            resultMap.put("message","count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        if (start==null){
//            start=0;
//        }
//        for (int i=start;i<=pageCount;i++){
//            logger.info(String.format("\n====================page={}&pageIndex={}====================\n")
//                    ,i,i*pageSize);
//            TmpMappingRoomTypeId tmpMappingRoomTypeId = new TmpMappingRoomTypeId();
//            tmpMappingRoomTypeId.setPageSize(pageSize);
//            tmpMappingRoomTypeId.setPageIndex(i*pageSize);
//            List<TmpMappingRoomTypeId> list = tmpMappingRoomTypeIdMapper.qureyByPramas(tmpMappingRoomTypeId);
//            if (CollectionUtils.isEmpty(list)){
//                logger.info(String.format("\n====================list is empty====================\n"));
//                continue;
//            }
//
//            for (final TmpMappingRoomTypeId dataBean:list){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================oldId={}&newId={}====================\n")
//                                    ,dataBean.getOldId(),dataBean.getNewId());
//                            if (dataBean.getOldId()==null||dataBean.getNewId()==null){
//                                return;
//                            }
//
//                            jedis.set(String.format("%s%s","TMP_MAPPING_ROOMTYE_ID_",dataBean.getOldId()),dataBean.getNewId().toString());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }
//    public Map<String,Object> roomtypeSetOldIdToNew(QHotelDto dto){
//        Map<String,Object> resultMap=new HashMap<String,Object>();
//        Cat.logEvent("roomtypeSetOldIdToNew","RoomTypeSet同步到Radis",Event.SUCCESS,
//                "beginTime=" + DateUtils.format_yMdHms(new Date()));
//        logger.info(String.format("\n====================roomtypeSetOldIdToNew begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
//        dto.setRoomTypeValid("T");
//        final int count = hotelService.count(dto);
//        if (count<=0){
//            resultMap.put("message"," count is 0");
//            resultMap.put("SUCCESS", false);
//            return resultMap;
//        }
//        int pageSize=1000;
//        int pageCount=count/pageSize;
//        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
//                ,count,pageSize,pageCount);
//        for (int i=0;i<=pageCount;i++){
//            logger.info(String.format("\n====================pageIndex={}====================\n")
//                    ,i*pageSize);
//            dto.setPageSize(pageSize);
//            dto.setPageIndex(i*pageSize);
//            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
//            if (CollectionUtils.isEmpty(hotelList)){
//                logger.info(String.format("\n====================hotelList is empty====================\n"));
//                continue;
//            }
//
//            for (final QHotelDto hotelDto:hotelList){
//                pool.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        Jedis jedis = null;
//                        try {
//                            jedis =  RedisUtil.getJedis();
//                            logger.info(String.format("\n====================hotelId={}====================\n")
//                                    ,hotelDto.getId());
//                            if (hotelDto.getSourceId()!=null){
//                                Set<String> roomtypeSet =jedis.smembers(String.format("%s%s",RedisCacheName.HOTELROOMTYPEINFOSET,hotelDto.getId()));
//                                if (CollectionUtils.isEmpty(roomtypeSet)){
//                                    return;
//                                }
//                                for (String roomtypeStr:roomtypeSet){
//                                    if (StringUtils.isEmpty(roomtypeStr)){
//                                        continue;
//                                    }
//                                RoomtypeToRedisDto saveBean = new RoomtypeToRedisDto();
//                                saveBean=JsonUtils.formatJson(roomtypeStr,RoomtypeToRedisDto.class);
//                                if (saveBean.getId()==null){
//                                    return;
//                                }
//                                    String newId=jedis.get(String.format("%s%s","TMP_MAPPING_ROOMTYE_ID_",saveBean.getId()));
//                                    if (StringUtils.isEmpty(newId)){
//                                        continue;
//                                    }
//                                    logger.info(String.format("\n====================oldId={}&newId====================\n")
//                                            ,saveBean.getId(),newId);
//                                saveBean.setId(Long.valueOf(newId));
//
//                                jedis.sadd(String.format("%s%s",RedisCacheName.HOTELROOMTYPEINFOSET,newId),JsonUtils.toJSONString(saveBean));
//
//
//                                }
//                                jedis.del(String.format("%s%s",RedisCacheName.HOTELROOMTYPEINFOSET,hotelDto.getId()));
//
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }finally {
//                            if(null != jedis){
//                                jedis.close();
//                            }
//
//                        }
//
//                    }
//                });
//
//            }
//
//        }
//        Cat.logEvent("roomtypeSetOldIdToNew", "RoomTypeSet同步到Radis", Event.SUCCESS,
//                "endTime=" + DateUtils.format_yMdHms(new Date())
//        );
//        logger.info(String.format("\n====================roomtypeSetOldIdToNew  endTime={}====================\n")
//                , DateUtils.format_yMdHms(new Date()));
//        resultMap.put("message","执行结束");
//        resultMap.put("SUCCESS", true);
//        return resultMap;
//    }


}
