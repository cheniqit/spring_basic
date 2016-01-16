package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;
import com.mk.taskfactory.api.ods.RoomPriceContrastService;
import com.mk.taskfactory.api.ods.RoomTypePriceDumpService;
import com.mk.taskfactory.api.ots.RoomTypeDynamicPriceService;
import com.mk.taskfactory.api.ots.SyServDictItemService;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.FreeMarkerTemplateUtils;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.biz.utils.email.EmailSend;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.TRoomTypeDynamicPrice;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class DynamicPriceServiceImpl implements DynamicPriceService {
    private static Logger logger = LoggerFactory.getLogger(DynamicPriceServiceImpl.class);
    private final static String DYNAMICPRICE_URL = "/dynamicprice/base";

    @Autowired
    private RoomTypeDynamicPriceService dynamicPriceService;

    @Autowired
    private RoomSaleAgreementPriceService agreementPriceService;

    public Map<String,Object>  dynamicPriceToLog(TRoomTypeDynamicPriceDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("dynamicPriceToLog", "动态价格备份", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================dynamicPriceToLog method begin time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        TRoomSaleAgreementPriceDto agreementPriceDto = new TRoomSaleAgreementPriceDto();
        agreementPriceDto.setIsLeZhu("T");
        agreementPriceDto.setValid("T");
        int  agreementConfigCount=agreementPriceService.countByPramas(agreementPriceDto);
        if (agreementConfigCount<=0){
            resultMap.put("message","agrementPrice count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=agreementConfigCount/pageSize;
        String nowDate=DateUtils.format_yMdHms(new Date()).substring(0,13);
        String checkinoclock=nowDate.substring(11,13);
        logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                ,agreementConfigCount,pageSize,pageCount);
        for (int i=0;i<=pageCount;i++){
            logger.info(String.format("\n====================pageIndex={}====================\n")
                    ,i*pageSize);
            agreementPriceDto.setPageSize(pageSize);
            agreementPriceDto.setPageIndex(i*pageSize);
            List<TRoomSaleAgreementPriceDto> agreementPriceList = agreementPriceService.qureyByPramas(agreementPriceDto);
            if (CollectionUtils.isEmpty(agreementPriceList)){
                logger.info(String.format("\n====================agreementPriceList is empty====================\n"));
                continue;
            }

            for (TRoomSaleAgreementPriceDto agreementPrice:agreementPriceList){
                try {
                    logger.info(String.format("====================hotelId={}&roomTypeId={}===================="),
                            agreementPrice.getHotelId(), agreementPrice.getRoomTypeId());
                    if (agreementPrice.getHotelId() == null || agreementPrice.getRoomTypeId() == null) {
                        continue;
                    }
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("hotelid", agreementPrice.getHotelId().toString());
                    params.put("roomtypeid", agreementPrice.getRoomTypeId().toString());
                    params.put("checkinoclock", checkinoclock);
                    String postResult = HttpUtils.doPost(Constants.OTS_HUIDU + DYNAMICPRICE_URL, params);
                    if (postResult == null) {
                        logger.info(String.format("====================postResult is null===================="));
                    }
                    Map<String, String> postMap = JsonUtils.jsonToMap(postResult);
                    if (postMap.get("price") == null) {
                        logger.info(String.format("====================roomTypeId={} price is null===================="), agreementPrice.getRoomTypeId());
                        continue;
                    }
                    TRoomTypeDynamicPriceDto dynamicPrice = new TRoomTypeDynamicPriceDto();
                    dynamicPrice.setHotelId(agreementPrice.getHotelId());
                    dynamicPrice.setRoomTypeId(agreementPrice.getRoomTypeId());
                    dynamicPrice.setPrice(new BigDecimal(postMap.get("price")));
                    dynamicPrice.setTimePoint(nowDate);
                    dynamicPrice.setCreateTime(DateUtils.format_yMdHms(new Date()));
                    dynamicPriceService.save(dynamicPrice);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

            }

        }
        resultMap.put("message","备份成功");
        resultMap.put("SUCCESS", true);
        Cat.logEvent("dynamicPriceToLog", "动态价格备份", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("====================dynamicPriceToLog method end time{}====================")
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }
}
