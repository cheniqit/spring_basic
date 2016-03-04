package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.crawer.*;
import com.mk.taskfactory.api.dtos.HotelScoreDto;
import com.mk.taskfactory.api.dtos.TCityListDto;
import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.THotelDto;
import com.mk.taskfactory.api.dtos.crawer.*;
import com.mk.taskfactory.api.ots.FacilityService;
import com.mk.taskfactory.api.ots.OtsHotelImageService;
import com.mk.taskfactory.api.ots.TCityListService;
import com.mk.taskfactory.biz.mapper.crawer.TmpMappingRoomTypeIdMapper;
import com.mk.taskfactory.biz.mapper.crawer.ValidRoomTypeMapper;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.mapper.ots.HotelScoreMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.model.HotelScore;
import com.mk.taskfactory.model.THotel;
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
public class JobStartServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(JobStartServiceImpl.class);

    @Autowired
    private QHotelToRedisService qHotelToRedisService;

    public void qHotelToRedis(){
        QHotelDto dto = new QHotelDto();
        qHotelToRedisService.qHotelToRedis(dto);
    }

    public void qCommentToRedis(){
        QCommentDto dto = new QCommentDto();
        qHotelToRedisService.qCommentToRedis(dto);
    }

    public void qCommentDetailToRedis(){
        QCommentDetailDto dto = new QCommentDetailDto();
        qHotelToRedisService.qCommentDetailToRedis(dto);
    }

    public void qFacilityToRedis(){
        QFacilityDto dto = new QFacilityDto();
        qHotelToRedisService.qFacilityToRedis(dto);
    }

    public void qHotelFacilityToRedis(){
        QHotelDto dto = new QHotelDto();
        qHotelToRedisService.qHotelFacilityToRedis(dto);
    }

    public void qHotelRoomTypeSetToRedis(){
        QHotelDto dto = new QHotelDto();
        qHotelToRedisService.qHotelRoomTypeSetToRedis(dto);
    }

    public void qHotelRoomtypeToRedis(){
        QHotelRoomtypeDto dto = new QHotelRoomtypeDto();
        qHotelToRedisService.qHotelRoomtypeToRedis(dto);
    }

    public void qHotelSurroundSetToRedis(){
        QHotelDto dto = new QHotelDto();
        qHotelToRedisService.qHotelSurroundSetToRedis(dto);
    }

    public void tFacilityToRedis(){
        TFacilityDto dto = new TFacilityDto();
        qHotelToRedisService.tFacilityToRedis(dto);
    }
    public void tHotelToRedis(){
        ValidRoomType dto = new ValidRoomType();
        qHotelToRedisService.tHotelToRedis(dto);
    }
    public void validPriceHotelToRedis(){
        qHotelToRedisService.validPriceHotelToRedis();
    }
    public void validPriceRoomTypeToRedis(){
        qHotelToRedisService.validPriceRoomTypeToRedis();
    }
    public void cityHotelSetToRedis(){
        TCityListDto dto = new TCityListDto();
        qHotelToRedisService.cityHotelSetToRedis(dto);
    }
    public void hotelScoreToRedis(){
        HotelScoreDto dto = new HotelScoreDto();
        qHotelToRedisService.hotelScoreToRedis(dto);
    }
    public void qHotelRoomTypeMinPriceToRedis(){
        QHotelRoomtypeMinPriceDto dto = new QHotelRoomtypeMinPriceDto();
        qHotelToRedisService.qHotelRoomTypeMinPriceToRedis(dto);
    }
    public void otaPriceToRedis(){
        QHotelDto dto = new QHotelDto();
        qHotelToRedisService.otaPriceToRedis(dto);
    }

    public void tQunarHotelScoreToRedis(){
        QHotelDto dto = new QHotelDto();
        qHotelToRedisService.tQunarHotelScoreToRedis(dto);
    }

    public void onlineCityToRedis(){
        TCityListDto dto = new TCityListDto();
        qHotelToRedisService.onlineCityToRedis(dto);
    }
}
