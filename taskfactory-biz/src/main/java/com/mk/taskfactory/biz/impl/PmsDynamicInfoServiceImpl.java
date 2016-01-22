package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.framework.manager.RedisCacheName;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.taskfactory.api.DynamicPriceService;
import com.mk.taskfactory.api.PmsDynamicInfoService;
import com.mk.taskfactory.api.RoomSaleAgreementPriceService;
import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypePmsDynamicInfoDto;
import com.mk.taskfactory.api.ots.RoomTypeDynamicPriceService;
import com.mk.taskfactory.api.ots.RoomTypePmsDynamicInfoService;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PmsDynamicInfoServiceImpl implements PmsDynamicInfoService {
    private static Logger logger = LoggerFactory.getLogger(PmsDynamicInfoServiceImpl.class);
    private final static String DYNAMICPRICE_URL = "/roomstate/vc";

    @Autowired
    private RoomTypePmsDynamicInfoService pmsDynamicInfoService;

    @Autowired
    private RoomSaleAgreementPriceService agreementPriceService;
    public void  pmsDynamicToLog(){
        TRoomTypePmsDynamicInfoDto dto = new TRoomTypePmsDynamicInfoDto();
        pmsDynamicToLog(dto);
    }

    public Map<String,Object>  pmsDynamicToLog(TRoomTypePmsDynamicInfoDto dto){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("pmsDynamicToLog", "pms动态信息备份", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================pmsDynamicToLog method begin time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        TRoomSaleAgreementPriceDto agreementPriceDto = new TRoomSaleAgreementPriceDto();
        agreementPriceDto.setIsLeZhu("T");
        agreementPriceDto.setValid("T");
        int  agreementConfigCount=agreementPriceService.countByPramas(agreementPriceDto);
        if (agreementConfigCount<=0){
            logger.info(String.format("\n====================size={}&pageSize={}&pageCount={}====================\n")
                    ,agreementConfigCount);
             resultMap.put("message","agrementPrice count is 0");
            resultMap.put("SUCCESS", false);
            return resultMap;
        }
        int pageSize=1000;
        int pageCount=agreementConfigCount/pageSize;
        String nowDate=DateUtils.format_yMdHms(new Date()).substring(0,13);
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
                    String postResult = HttpUtils.doPost(Constants.OTS_URL + DYNAMICPRICE_URL, params);
                    if (postResult == null) {
                        logger.info(String.format("====================postResult is null===================="));
                    }
                    Map<String, String> postMap = JsonUtils.jsonToMap(postResult);
                    if (postMap.get("success") != null&&postMap.get("success").equals("false")) {
                        logger.info(String.format("===================={}====================="),
                                postMap);
                        continue;
                    }
                    TRoomTypePmsDynamicInfoDto dynamicPrice = new TRoomTypePmsDynamicInfoDto();
                    dynamicPrice.setHotelId(agreementPrice.getHotelId());
                    dynamicPrice.setRoomTypeId(agreementPrice.getRoomTypeId());
                    if (postMap.get("currentnum")!=null)
                        dynamicPrice.setStoreCount(Integer.valueOf(postMap.get("currentnum")));
                    if (postMap.get("dynamicprice1")!=null)
                        dynamicPrice.setDynamicPrice1(new BigDecimal(postMap.get("dynamicprice1")));
                    if (postMap.get("dynamicprice2")!=null)
                        dynamicPrice.setDynamicPrice2(new BigDecimal(postMap.get("dynamicprice2")));
                    if (postMap.get("dynamicprice3")!=null)
                        dynamicPrice.setDynamicPrice3(new BigDecimal(postMap.get("dynamicprice3")));
                    if (postMap.get("phoneprice")!=null)
                        dynamicPrice.setPhonePrice(new BigDecimal(postMap.get("phoneprice")));
                    dynamicPrice.setTimePoint(nowDate);
                    dynamicPrice.setCreateTime(DateUtils.format_yMdHms(new Date()));
                    pmsDynamicInfoService.save(dynamicPrice);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

            }

        }
        resultMap.put("message","备份成功");
        resultMap.put("SUCCESS", true);
        Cat.logEvent("pmsDynamicToLog", "pms动态信息备份", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info(String.format("====================pmsDynamicToLog method end time{}====================")
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }

}
