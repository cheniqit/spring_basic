package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.BookClickService;
import com.mk.taskfactory.api.CityListService;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.ots.CityService;
import com.mk.taskfactory.api.ots.HotelDayClickService;
import com.mk.taskfactory.api.ots.HotelHourClickService;
import com.mk.taskfactory.api.ots.TCityListService;
import com.mk.taskfactory.biz.mapper.ots.HotelMapper;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.PinYin4jUtil;
import com.mk.taskfactory.model.THotel;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CityListServiceImpl implements CityListService {
    private static Logger logger = LoggerFactory.getLogger(CityListServiceImpl.class);
    private static ExecutorService pool = Executors.newFixedThreadPool(40);

    @Autowired
    private CityService cityService;

    @Autowired
    private TCityListService cityListService;

    public Map<String,Object>  cityToCityList(TCityDto dto) {
        Map<String,Object> resultMap=new HashMap<String,Object>();
        List<TCityDto> cityList = cityService.qureyByPramas(dto);
        for (TCityDto cityDto: cityList){
            TCityListDto cityListCheckExist = new TCityListDto();
            cityListCheckExist.setId(cityDto.getCityId().intValue());
            cityListCheckExist  = cityListService.getByPramas(cityListCheckExist);
            if (cityListCheckExist!=null&&cityListCheckExist.getId()!=null){
                logger.info(String.format("\n====================cityCode={}&cityName={} is Exist====================\n")
                        , cityDto.getCode(),cityDto.getQueryCityName());
                continue;
            }
            logger.info(String.format("\n====================cityCode={}&cityName={}====================\n")
                    , cityDto.getCode(),cityDto.getQueryCityName());
            TCityListDto cityListDto = new TCityListDto();
            cityListDto.setId(cityDto.getCityId().intValue());
            if (cityDto.getQueryCityName().indexOf("市")!=-1){
                cityListDto.setCityName(cityDto.getQueryCityName().substring(0,cityDto.getQueryCityName().length()-1));

            }else {
                cityListDto.setCityName(cityDto.getQueryCityName());

            }
            cityListDto.setCityCode(cityDto.getCode());
            cityListDto.setPinYin(PinYin4jUtil.converterToSpell(cityListDto.getCityName()));
            cityListDto.setAcronym(PinYin4jUtil.converterToFirstSpell(cityListDto.getCityName()));
            cityListDto.setSort(cityDto.getCitySort());
            cityListDto.setLatitude(cityDto.getLatItude());
            cityListDto.setLongitude(cityDto.getLongItude());
            cityListDto.setIsHotCity(cityDto.getIsHotCity());
            cityListDto.setRange(cityDto.getRange());
            cityListDto.setValid("T");
            cityListDto.setLevel(cityDto.getLevel());
            cityListService.save(cityListDto);
        }
        resultMap.put("message","执行成功");
        resultMap.put("SUCCESS", true);
        logger.info(String.format("\n====================cityToCityList method end time{}====================\n")
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }

}

