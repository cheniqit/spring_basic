package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.crawer.*;
import com.mk.taskfactory.api.dtos.HotelScoreDto;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.THotelDto;
import com.mk.taskfactory.api.dtos.crawer.*;
import com.mk.taskfactory.api.ots.CityService;
import com.mk.taskfactory.api.ots.FacilityService;
import com.mk.taskfactory.api.ots.OtsHotelImageService;
import com.mk.taskfactory.biz.mapper.crawer.ValidPriceMapper;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.mapper.ots.HotelScoreMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.model.HotelScore;
import com.mk.taskfactory.model.THotel;
import com.mk.taskfactory.api.dtos.crawer.ValidPrice;
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
    private ValidPriceMapper validPriceMapper;
    @Autowired
    private CityService cityService;
    @Autowired
    private HotelScoreMapper hotelScoreMapper;
    private static ExecutorService pool = Executors.newFixedThreadPool(64);


    public Map<String,Object> qHotelToRedis(QHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qHotelToRedis","QHotel同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qHotelToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        dto.setPriceValid("T");
        int count = hotelService.count(dto);
        if (count<=0){
            resultMap.put("message","QHotel count is 0");
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
            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(hotelList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final QHotelDto hotelDto:hotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,hotelDto.getId());
                            if (hotelDto.getSourceId()!=null){
                                hotelDto.setHotelSource(2);
                                jedis.set(String.format("%s%s", RedisCacheName.HOTELJSONINFO,
                                        hotelDto.getId()), JsonUtils.toJSONString(hotelDto)
                                        );
                                TExHotelImageDto hotelImageDto = new TExHotelImageDto();
                                hotelImageDto.setHotelSourceId(hotelDto.getSourceId());
                                hotelImageDto.setTag("外观");
                                List<TExHotelImageDto> hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                int mark=0;
                                if(CollectionUtils.isEmpty(hotelImageDtoList)){
                                    hotelImageDto.setTag("客房");
                                    hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                    if(CollectionUtils.isEmpty(hotelImageDtoList)){
                                        hotelImageDto.setTag("大厅");
                                        hotelImageDtoList = otsHotelImageService.qureyByPramas(hotelImageDto);
                                        if(CollectionUtils.isEmpty(hotelImageDtoList)){
                                            return;
                                        }
                                    }
                                    mark++;
                                }
                                for (TExHotelImageDto imageDto:hotelImageDtoList){
                                    HotelImgDto hotelImgDto = new HotelImgDto();
                                    BeanUtils.copyProperties(imageDto, hotelImgDto);
                                    if (mark>0){
                                        break;
                                    }

                                    jedis.sadd(String.format("%s%s", RedisCacheName.HOTEL_PICTURE_INFOS_SET,
                                            hotelDto.getId()), JsonUtils.toJSONString(hotelImgDto)
                                    );
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelToRedis::error::hotelid:"+hotelDto.getSourceId());
                            logger.error("qHotelToRedis::error{}{}",hotelDto.getSourceId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qHotelToRedis", "QHotel同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qHotelToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> qCommentToRedis(QCommentDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qCommentToRedis","QComment同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qCommentToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));

        int count = commentService.count(dto);
        if (count<=0){
            resultMap.put("message","Comment count is 0");
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
            List<QCommentDto> commentList = commentService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(commentList)){
                logger.info(String.format("\n====================commentList is empty====================\n"));
                continue;
            }

            for (final QCommentDto commentDto:commentList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,commentDto.getHotelId());
                            if (commentDto.getHotelId()!=null){
                                jedis.set(String.format("%s%s", RedisCacheName.HOTELCOMMENTINFO,
                                        commentDto.getHotelId()), JsonUtils.toJSONString(commentDto)
                                );
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelToRedis::error::hotelid:"+commentDto.getHotelId());
                            logger.error("qHotelToRedis::error{}{}",commentDto.getHotelId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qCommentToRedis", "QComment同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qCommentToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> qCommentDetailToRedis(QCommentDetailDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qCommentDetailToRedis","QCommentDetail同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qCommentDetailToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));

        int count = commentDetailService.count(dto);
        if (count<=0){
            resultMap.put("message","QCommentDetail count is 0");
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
            List<QCommentDetailDto> commentDetailList = commentDetailService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(commentDetailList)){
                logger.info(String.format("\n====================commentDetailList is empty====================\n"));
                continue;
            }

            for (final QCommentDetailDto commentDetailDto:commentDetailList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,commentDetailDto.getHotelId());
                            if (commentDetailDto.getHotelId()!=null){
                                jedis.set(String.format("%s%s", RedisCacheName.HOTELCOMMENTDETAILINFO,
                                        commentDetailDto.getCommentId()), JsonUtils.toJSONString(commentDetailDto)
                                );
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qCommentDetailToRedis::error::commentId:"+commentDetailDto.getCommentId());
                            logger.error("qCommentDetailToRedis::error{}{}",commentDetailDto.getCommentId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qCommentDetailToRedis", "QCommentDetail同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qCommentDetailToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> qFacilityToRedis(QFacilityDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qFacilityToRedis","QFacility同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qFacilityToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));

        int count = facilityService.count(dto);
        if (count<=0){
            resultMap.put("message","QFacility count is 0");
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
            List<QFacilityDto> facilityList = facilityService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(facilityList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final QFacilityDto facilityDto:facilityList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================id={}====================\n")
                                    ,facilityDto.getId());
                            if (facilityDto.getId()!=null){
                                jedis.set(String.format("%s%s", RedisCacheName.FACILITYINFO,
                                        facilityDto.getId()), JsonUtils.toJSONString(facilityDto)
                                );
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelToRedis::error::id:"+facilityDto.getId());
                            logger.error("qHotelToRedis::error{}{}",facilityDto.getId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qFacilityToRedis", "QFacility同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qFacilityToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> qHotelFacilityToRedis(QHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qHotelFacilityToRedis","QHotelFacility同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qHotelFacilityToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));

        int count = hotelService.count(dto);
        if (count<=0){
            resultMap.put("message","QHotel count is 0");
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
            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(hotelList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final QHotelDto hotelDto:hotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,hotelDto.getId());
                            if (hotelDto.getId()!=null){
                                QHotelFacilityDto hotelFacilityDto = new QHotelFacilityDto();
                                hotelFacilityDto.setHotelId(hotelDto.getId());
                                List<QHotelFacilityDto> hotelFacilityDtoList = hotelFacilityService.qureyJionFacility(hotelFacilityDto);
                                if (CollectionUtils.isEmpty(hotelFacilityDtoList)){
                                    return;
                                }
                                for (QHotelFacilityDto facilityDto:hotelFacilityDtoList){
                                    if (StringUtils.isEmpty(facilityDto.getOtsId())){
                                        continue;
                                    }
                                    String facility=jedis.get(String.format("%s%s", RedisCacheName.LEZHUFACILITY,
                                            facilityDto.getOtsId()));
                                    jedis.sadd(String.format("%s%s", RedisCacheName.HOTELFACILITYINFOSET,
                                            hotelDto.getId()), facility
                                    );
                                }


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelFacilityToRedis::error::hotelid:"+hotelDto.getSourceId());
                            logger.error("qHotelFacilityToRedis::error{}{}",hotelDto.getSourceId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qHotelFacilityToRedis", "QHotelFacility同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qHotelFacilityToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> qHotelRoomTypeSetToRedis(QHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qHotelRoomTypeSetToRedis","QHotelRoomTypeSet同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qHotelRoomTypeSetToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        dto.setPriceValid("T");
        int count = hotelService.count(dto);
        if (count<=0){
            resultMap.put("message","QHotel count is 0");
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
            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(hotelList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final QHotelDto hotelDto:hotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,hotelDto.getId());
                            if (hotelDto.getSourceId()!=null){
                                QHotelRoomtypeDto hotelRoomtype = new QHotelRoomtypeDto();
                                hotelRoomtype.setHotelSourceId(hotelDto.getSourceId());
                                List<QHotelRoomtypeDto> hotelRoomtypeList = hotelRoomTypeService.qureyByPramas(hotelRoomtype);
                                if (CollectionUtils.isEmpty(hotelRoomtypeList)){
                                    return;
                                }
                                int p=0;
                                for (QHotelRoomtypeDto roomtypeDto:hotelRoomtypeList){
                                    RoomtypeToRedisDto bean = new RoomtypeToRedisDto();
                                    QHotelRoomtypeDto getImgBean = new QHotelRoomtypeDto();
                                    TExHotelImageDto hotelImageDto = new TExHotelImageDto();
                                    getImgBean.setHotelSourceId(roomtypeDto.getHotelSourceId());
                                    getImgBean.setRoomtypeKey(roomtypeDto.getRoomtypeKey());
                                    getImgBean=hotelRoomTypeService.getRoomtypeImg(getImgBean);
                                    if (getImgBean==null||getImgBean.getId()==null){
                                        hotelImageDto.setHotelSourceId(roomtypeDto.getHotelSourceId());
                                        hotelImageDto.setTag("客房");
                                        List<TExHotelImageDto> hotelImageDtoList=otsHotelImageService.qureyByPramas(hotelImageDto);
                                        if (!CollectionUtils.isEmpty(hotelImageDtoList)){
                                            if (hotelImageDtoList.size()<=p){
                                                p=0;
                                            }
                                            hotelImageDto=hotelImageDtoList.get(p);
                                            p++;
                                        }
                                    }else {
                                        hotelImageDto.setId(getImgBean.getId());
                                        hotelImageDto=otsHotelImageService.getByPramas(hotelImageDto);
                                    }
                                    BeanUtils.copyProperties(roomtypeDto, bean);
                                    bean.setImageUrl(hotelImageDto.getBig());
                                    bean.setSmallImageUrl(hotelImageDto.getUrl());
                                    jedis.sadd(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFOSET,
                                            hotelDto.getId()), JsonUtils.toJSONString(bean)
                                    );
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelRoomTypeSetToRedis::error::sourceId:"+hotelDto.getSourceId());
                            logger.error("qHotelRoomTypeSetToRedis::error{}{}",hotelDto.getSourceId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qHotelRoomTypeSetToRedis", "QHotelRoomTypeSet同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qHotelRoomTypeSetToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> qHotelRoomtypeToRedis(QHotelRoomtypeDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qHotelRoomtypeToRedis","QHotelRoomtype同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qHotelRoomtypeToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        dto.setPriceValid("T");
        int count = hotelRoomTypeService.count(dto);
        if (count<=0){
            resultMap.put("message","QHotel count is 0");
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
            List<QHotelRoomtypeDto> roomtypeDtoList = hotelRoomTypeService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(roomtypeDtoList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final QHotelRoomtypeDto roomtypeDto:roomtypeDtoList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================id={}====================\n")
                                    ,roomtypeDto.getId());
                            if (roomtypeDto.getId()!=null){
                                RoomtypeToRedisDto bean = new RoomtypeToRedisDto();
                                QHotelRoomtypeDto getImgBean = new QHotelRoomtypeDto();
                                TExHotelImageDto hotelImageDto = new TExHotelImageDto();

                                getImgBean.setHotelSourceId(roomtypeDto.getHotelSourceId());
                                getImgBean.setRoomtypeKey(roomtypeDto.getRoomtypeKey());
                                getImgBean=hotelRoomTypeService.getRoomtypeImg(getImgBean);
                                if (getImgBean==null||getImgBean.getId()==null){
                                    hotelImageDto.setHotelSourceId(roomtypeDto.getHotelSourceId());
                                    hotelImageDto.setTag("客房");
                                    List<TExHotelImageDto> hotelImageDtoList=otsHotelImageService.qureyByPramas(hotelImageDto);
                                    if (!CollectionUtils.isEmpty(hotelImageDtoList)){
                                        hotelImageDto=hotelImageDtoList.get(0);
                                    }
                                }else {
                                    hotelImageDto.setId(getImgBean.getId());
                                    hotelImageDto=otsHotelImageService.getByPramas(hotelImageDto);
                                }
                                hotelImageDto.setId(getImgBean.getId());
                                hotelImageDto=otsHotelImageService.getByPramas(hotelImageDto);
                                BeanUtils.copyProperties(roomtypeDto, bean);
                                bean.setImageUrl(hotelImageDto.getBig());
                                bean.setSmallImageUrl(hotelImageDto.getUrl());
                                jedis.set(String.format("%s%s", RedisCacheName.HOTELROOMTYPEINFO,
                                        roomtypeDto.getId()), JsonUtils.toJSONString(bean)
                                );
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelRoomtypeToRedis::error::id:"+roomtypeDto.getId());
                            logger.error("qHotelRoomtypeToRedis::error{}{}",roomtypeDto.getId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qHotelRoomtypeToRedis", "QHotelRoomtype同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qHotelRoomtypeToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }

    public Map<String,Object> qHotelSurroundSetToRedis(QHotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("qHotelSurroundToRedis","QHotelSurround同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================qHotelSurroundToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));

        int count = hotelService.count(dto);
        if (count<=0){
            resultMap.put("message","QHotel count is 0");
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
            List<QHotelDto> hotelList = hotelService.qureyByPramas(dto);
            if (CollectionUtils.isEmpty(hotelList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final QHotelDto hotelDto:hotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,hotelDto.getId());
                            if (hotelDto.getSourceId()!=null){
                                QHotelSurroundDto hotelSurroundDto = new QHotelSurroundDto();
                                hotelSurroundDto.setHotelId(hotelDto.getId().toString());
                                List<QHotelSurroundDto> hotelSurroundDtoList = hotelSurroundService.qureyByPramas(hotelSurroundDto);
                                if (CollectionUtils.isEmpty(hotelSurroundDtoList)){
                                    return;
                                }
                                jedis.set(String.format("%s%s", RedisCacheName.HOTELSURROUNDINFOSET,
                                        hotelDto.getId()), JsonUtils.toJSONString(hotelSurroundDtoList)
                                );
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("qHotelToRedis::error::hotelid:"+hotelDto.getId());
                            logger.error("qHotelToRedis::error{}{}",hotelDto.getId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("qHotelSurroundToRedis", "QHotelSurround同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================qHotelSurroundToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
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
    public Map<String,Object> tHotelToRedis(THotelDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("tHotelToRedis","THotel同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================tHotelToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = hotelMapper.countTHotel(dto);
        if (count<=0){
            resultMap.put("message","THotel count is 0");
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
            List<THotel> hotelList = hotelMapper.queryTHotel(dto);
            if (CollectionUtils.isEmpty(hotelList)){
                logger.info(String.format("\n====================hotelList is empty====================\n"));
                continue;
            }

            for (final THotel hotel:hotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,hotel.getId());
                            if (hotel.getId()!=null){
                                THotelDto tHotelDto = new THotelDto();
                                BeanUtils.copyProperties(hotel, tHotelDto);
                                tHotelDto.setHotelSource(1);
                                jedis.set(String.format("%s%s", RedisCacheName.HOTELJSONINFO,
                                        hotel.getId()), JsonUtils.toJSONString(tHotelDto)
                                );

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("tHotelToRedis::error::hotelid:"+hotel.getId());
                            logger.error("tHotelToRedis::error{}{}",hotel.getId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("tHotelToRedis", "THotel同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================tHotelToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public Map<String,Object> validPriceHotelToRedis(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("validPriceHotelToRedis","ValidPriceHotel同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================validPriceHotelToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = validPriceMapper.countValidPriceHotel();
        if (count<=0){
            resultMap.put("message","ValidPriceHotel count is 0");
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
            ValidPrice dto = new ValidPrice();
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<ValidPrice> validPriceHotelList = validPriceMapper.qureyValidPriceHotel(dto);
            if (CollectionUtils.isEmpty(validPriceHotelList)){
                logger.info(String.format("\n====================validPriceHotelList is empty====================\n"));
                continue;
            }

            for (final ValidPrice validPriceHotel:validPriceHotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,validPriceHotel.getHotelId());
                            if (validPriceHotel.getHotelId()!=null){
                                jedis.set(String.format("%s%s", RedisCacheName.LEZHU_VAILD_PRICE_HOTEL_INFO,
                                        validPriceHotel.getHotelId()), "1");

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("validPriceHotelToRedis::error::hotelid:"+validPriceHotel.getHotelId());
                            logger.error("validPriceHotelToRedis::error{}{}",validPriceHotel.getHotelId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("validPriceHotelToRedis", "ValidPriceHotel同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================validPriceHotelToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public Map<String,Object> validPriceRoomTypeToRedis(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("validPriceRoomTypeToRedis","ValidPriceRoomType同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================validPriceRoomTypeToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = validPriceMapper.countValidPriceRoomtype();
        if (count<=0){
            resultMap.put("message","ValidPriceRoomType count is 0");
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
            ValidPrice dto = new ValidPrice();
            dto.setPageSize(pageSize);
            dto.setPageIndex(i*pageSize);
            List<ValidPrice> validPriceHotelList = validPriceMapper.qureyValidPriceRoomType(dto);
            if (CollectionUtils.isEmpty(validPriceHotelList)){
                logger.info(String.format("\n====================validPriceHotelList is empty====================\n"));
                continue;
            }

            for (final ValidPrice validPriceHotel:validPriceHotelList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,validPriceHotel.getHotelId());
                            if (validPriceHotel.getHotelId()!=null){
                                jedis.set(String.format("%s%s", RedisCacheName.LEZHU_VAILD_PRICE_ROOMTYPE_INFO,
                                        validPriceHotel.getRoomTypeId()), "1");

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("validPriceRoomTypeToRedis::error::roomTypeId:"+validPriceHotel.getRoomTypeId());
                            logger.error("validPriceRoomTypeToRedis::error{}{}",validPriceHotel.getRoomTypeId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("validPriceRoomTypeToRedis", "ValidPriceRoomType同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================validPriceRoomTypeToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
    public Map<String,Object> cityHotelSetToRedis(TCityDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("cityHotelSetToRedis","cityHotelSet同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================cityHotelSetToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        List<TCityDto> cityDtoList = cityService.qureyByPramas(dto);
        if (CollectionUtils.isEmpty(cityDtoList)){
            logger.info(String.format("\n====================cityList is empty====================\n"));
            resultMap.put("message","cityList is empty");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }

        for (final TCityDto bean:cityDtoList){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = null;
                    try {
                        jedis =  RedisUtil.getJedis();
                        logger.info(String.format("\n====================cityCode={}&cityName={}====================\n")
                                ,bean.getCode(),bean.getCityName());
                        if (StringUtils.isEmpty(bean.getCode())){
                            return;
                        }
                        QHotelDto qHotelDto = new QHotelDto();
                        qHotelDto.setCityCode(Integer.valueOf(bean.getCode()));
                        List<QHotelDto> qHotelDtoList = hotelService.qureyByPramas(qHotelDto);
                        for (QHotelDto qBean : qHotelDtoList){
                            qBean.setHotelSource(2);
                            jedis.sadd(String.format("%s%s", RedisCacheName.CITYHOTELSET,
                                    bean.getCode()), JsonUtils.toJSONString(qBean));
                        }
                        THotelDto tHotelDto = new THotelDto();
                        tHotelDto.setCityCode(bean.getCode());
                        List<THotel> tHotelDtoList = hotelMapper.queryTHotel(tHotelDto);
                        for (THotel tHotel : tHotelDtoList){
                            QHotelDto temQHotel = new QHotelDto();
                            BeanUtils.copyProperties(tHotel,temQHotel);
                            temQHotel.setHotelSource(1);
                            jedis.sadd(String.format("%s%s", RedisCacheName.CITYHOTELSET,
                                    bean.getCode()), JsonUtils.toJSONString(temQHotel));
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("validPriceRoomTypeToRedis::error::cityCode:"+bean.getCode());
                        logger.error("validPriceRoomTypeToRedis::error{}{}",bean.getCode(),e.getMessage());
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
    public Map<String,Object> hotelScoreToRedis(HotelScoreDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("hotelScoreToRedis","hotelScore同步到Radis",Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date()));
        logger.info(String.format("\n====================hotelScoreToRedis begin time={}====================\n"),DateUtils.format_yMdHms(new Date()));
        int count = hotelScoreMapper.countTHotelScore(dto);
        if (count<=0){
            resultMap.put("message","HotelScore count is 0");
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
            List<HotelScore> hotelScoreList = hotelScoreMapper.queryTHotelScore(dto);
            if (CollectionUtils.isEmpty(hotelScoreList)){
                logger.info(String.format("\n====================hotelScoreList is empty====================\n"));
                continue;
            }

            for (final HotelScore hotelScore:hotelScoreList){
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Jedis jedis = null;
                        try {
                            jedis =  RedisUtil.getJedis();
                            logger.info(String.format("\n====================hotelId={}====================\n")
                                    ,hotelScore.getHotelId());
                            if (hotelScore.getHotelId()!=null){
                                jedis.set(String.format("%s%s", RedisCacheName.HOTEL_SCORE_INFO,
                                        hotelScore.getHotelId()), hotelScore.getGrade());

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("validPriceRoomTypeToRedis::error::hotelId:"+hotelScore.getHotelId());
                            logger.error("validPriceRoomTypeToRedis::error{}{}",hotelScore.getHotelId(),e.getMessage());
                        }finally {
                            if(null != jedis){
                                jedis.close();
                            }

                        }

                    }
                });

            }

        }
        Cat.logEvent("hotelScoreToRedis", "HotelScore同步到Radis", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("\n====================hotelScoreToRedis  endTime={}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        resultMap.put("message","执行结束");
        resultMap.put("SUCCESS", true);
        return resultMap;
    }
}
