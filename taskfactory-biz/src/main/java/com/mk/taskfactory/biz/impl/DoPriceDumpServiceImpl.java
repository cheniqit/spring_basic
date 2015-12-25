package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;
import com.mk.taskfactory.api.ods.RoomPriceContrastService;
import com.mk.taskfactory.api.ods.RoomTypePriceDumpService;
import com.mk.taskfactory.api.ots.SyServDictItemService;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.FreeMarkerTemplateUtils;
import com.mk.taskfactory.biz.utils.email.EmailSend;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DoPriceDumpServiceImpl implements DoPriceDumpService {
    private static Logger logger = LoggerFactory.getLogger(DoPriceDumpServiceImpl.class);

    @Autowired
    private RoomTypePriceDumpService roomTypePriceDumpService;
    @Autowired
    private RoomSaleConfigService roomSaleConfigService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomPriceContrastService roomPriceContrastService;
    @Autowired
    private SyServDictItemService syServDictItemService;
    @Autowired
    private CityService cityService;

    public Map<String,Object>  doPriceDump(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("doPriceDump", "酒店价格备份", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================doPriceDump method begin time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        String statisticDate=DateUtils.format_yMd(new Date());
        TRoomTypePriceDumpDto checkIsDoBean = new TRoomTypePriceDumpDto();
        checkIsDoBean.setStatisticDate(statisticDate);
        checkIsDoBean.setPageIndex(0);
        checkIsDoBean.setPageSize(1);
        List<TRoomTypePriceDumpDto> checkIsDoList=roomTypePriceDumpService.queryByParams(checkIsDoBean);
        if (!CollectionUtils.isEmpty(checkIsDoList)){
            resultMap.put("message", statisticDate+"数据已dump");
            resultMap.put("SUCCESS", false);
            Cat.logEvent("doPriceDump", "数据已同步", Event.SUCCESS, "endTime=" + DateUtils.format_yMdHms(new Date()));
            logger.info("====================doPriceDump method isDomped====================");
            return resultMap;
        }
        Integer roomTypeCount=roomTypeService.count();
        if (roomTypeCount==null||roomTypeCount<=0){
            resultMap.put("message", statisticDate+"数据已dump");
            resultMap.put("SUCCESS", false);
            Cat.logEvent("doPriceDump", "roomTypeCount is null", Event.SUCCESS, "endTime=" + DateUtils.format_yMdHms(new Date()));
            logger.info("====================doPriceDump method roomTypeCount is null====================");
            return resultMap;
        }
        Integer pageSize=1000;//100
        Integer pageNum=roomTypeCount/pageSize;
        logger.info("====================doPriceDump roomTypeCount=" + roomTypeCount + "====================");
        for (int i=0;i<=pageNum;i++) {
            TRoomTypeDto roomTypeSearchBean = new TRoomTypeDto();
            roomTypeSearchBean.setPageIndex(i*pageSize);
            roomTypeSearchBean.setPageSize(pageSize);
            List<TRoomTypeDto> roomTypeList = roomTypeService.queryJionThotel(roomTypeSearchBean);
            logger.info("====================queryJionThotel pageIndex={}&pageSize={}===================="
                    , i, pageSize);
            if (CollectionUtils.isEmpty(roomTypeList)) {
                logger.info("====================queryJionThotel pageIndex={}&pageSize={} is empty===================="
                        , i, pageSize);
                continue;
            }
            for (TRoomTypeDto roomType:roomTypeList){
                logger.info("====================doPriceDump roomType id={}&hotelId={}===================="
                        , roomType.getId(), roomType.getThotelId());
                if(roomType==null||roomType.getThotelId()==null||roomType.getId()==null){
                    continue;
                }
                //OTS房态
                OtsRoomStateDto roomStateDto = roomService.getOtsRoomState(roomType.getThotelId(), roomType.getId(), null, null);
                BigDecimal mkPrice = roomStateDto.getPrice();
                BigDecimal marketPrice = roomStateDto.getPmsPrice();
                logger.info("============doPriceDump hotelId={}&roomTypeId={}&mkPrice={}&marketPrice={}====================",
                        roomType.getThotelId(),roomType.getId(),mkPrice,marketPrice);
                if (null == mkPrice || null == marketPrice) {
                    continue;
                }
                TRoomTypePriceDumpDto roomTypePriceDumpDto=new TRoomTypePriceDumpDto();
                roomTypePriceDumpDto.setHotelId(BigInteger.valueOf(roomType.getThotelId()));
                roomTypePriceDumpDto.setRoomTypeId(BigInteger.valueOf(roomType.getId()));
                roomTypePriceDumpDto.setHotelName(roomType.getHotelName());
                roomTypePriceDumpDto.setRoomTypeName(roomType.getName());
                roomTypePriceDumpDto.setMarketPrice(marketPrice);
                roomTypePriceDumpDto.setMkPrice(mkPrice);
                roomTypePriceDumpDto.setStatisticDate(statisticDate);
                roomTypePriceDumpDto.setCreateDate(DateUtils.format_yMdHms(new Date()));
                roomTypePriceDumpDto.setCityCode(roomType.getCityCode());
                TRoomSaleConfigDto checkRoomSaleConfigBean=new TRoomSaleConfigDto();
                checkRoomSaleConfigBean.setRoomTypeId(roomType.getId());
                checkRoomSaleConfigBean.setValid("T");
                List<TRoomSaleConfigDto> roomSaleConfigDtoList=roomSaleConfigService.queryRoomSaleConfigByParams(checkRoomSaleConfigBean);
                Map<Integer,Integer> saleTypeMap;
                if (!CollectionUtils.isEmpty(roomSaleConfigDtoList)) {
                    saleTypeMap=new HashMap<Integer, Integer>();
                    for (TRoomSaleConfigDto roomSaleConfigDto:roomSaleConfigDtoList){
                        logger.info("============doPriceDump hotelId={}&roomTypeId={}&saleTypeId{}====================",
                                roomType.getThotelId(), roomType.getId(), roomSaleConfigDto.getSaleRoomTypeId());
                        if (saleTypeMap.get(roomSaleConfigDto.getSaleTypeId())==null) {
                            roomTypePriceDumpDto.setIsPromo(true);
                            roomTypePriceDumpDto.setPromoId(roomSaleConfigDto.getSaleTypeId());
                            roomTypePriceDumpDto.setPromoPrice(roomSaleConfigDto.getSaleValue());
                            roomTypePriceDumpDto.setSettlePrice(roomSaleConfigDto.getSettleValue());
                            roomTypePriceDumpService.save(roomTypePriceDumpDto);
                            saleTypeMap.put(roomSaleConfigDto.getSaleTypeId(), roomSaleConfigDto.getSaleTypeId());
                        }
                    }
                }else {
                    roomTypePriceDumpDto.setPromoId(0);
                    roomTypePriceDumpService.save(roomTypePriceDumpDto);
                }
            }

        }
        resultMap.put("message","备份成功");
        resultMap.put("SUCCESS", true);
        Cat.logEvent("doPriceDump", "酒店价格备份", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================doPriceDump method end time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }
    public Map<String,Object> priceContrast(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("priceContrast", "酒店价格备份", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================priceContrast method begin time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        String statisticDate=DateUtils.format_yMd(new Date());
        TRoomPriceContrastDto checkIsDoBean = new TRoomPriceContrastDto();
        checkIsDoBean.setContrastDate(statisticDate);
        checkIsDoBean.setPageIndex(0);
        checkIsDoBean.setPageSize(1);
        List<TRoomPriceContrastDto> checkIsDoList= roomPriceContrastService.queryByParams(checkIsDoBean);
        if (!CollectionUtils.isEmpty(checkIsDoList)){
            resultMap.put("message", statisticDate+"数据已存在");
            resultMap.put("SUCCESS", false);
            Cat.logEvent("doPriceDump", "数据已存在", Event.SUCCESS, "endTime=" + DateUtils.format_yMdHms(new Date()));
            logger.info("====================priceContrast method data is exist====================");
            return resultMap;
        }
        Date today = new Date();
        Date yesterday = org.apache.commons.lang3.time.DateUtils.addDays(today, -1);
        TRoomPriceContrastDto getDoBean = new TRoomPriceContrastDto();
        getDoBean.setStatisticDate1(DateUtils.format_yMd(today));
        getDoBean.setStatisticDate2(DateUtils.format_yMd(yesterday));
        List<TRoomPriceContrastDto> contrastList= roomPriceContrastService.getRoomPriceContrast(getDoBean);
        if (CollectionUtils.isEmpty(contrastList)){
            resultMap.put("message","对比结果为空");
            resultMap.put("SUCCESS", false);
            Cat.logEvent("doPriceDump", "对比结果为空", Event.SUCCESS, "endTime=" + DateUtils.format_yMdHms(new Date()));
            logger.info("====================priceContrast contrastList is empty====================");
            return resultMap;
        }
        logger.info("====================priceContrast contrastList size=" + contrastList.size()+ "====================");
        for (TRoomPriceContrastDto contrastDto:contrastList) {
            contrastDto.setCreateDate(DateUtils.format_yMdHms(new Date()));
            roomPriceContrastService.save(contrastDto);
        }
        resultMap.put("message","操作成功");
        resultMap.put("SUCCESS", true);
        Cat.logEvent("priceContrast", "操作成功", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================priceContrast method end time={}===================="
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }
    public Map<String,Object> sendEmail(TRoomPriceContrastDto bean){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        String sendDate="";
        if (StringUtils.isNotEmpty(bean.getContrastDate())){
            sendDate=bean.getContrastDate();
        }else {
            sendDate=DateUtils.format_yMd(new Date());
        }
        Cat.logEvent("sendEmail", "发送邮件", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())+"&sendDate="+sendDate
        );
        logger.info("====================sendEmail method begin time={}&sendDate={}===================="
                , DateUtils.format_yMdHms(new Date()), sendDate);
        TRoomPriceContrastDto getDoBean = new TRoomPriceContrastDto();
        getDoBean.setContrastDate(sendDate);
        List<TRoomPriceContrastDto> contrastList= roomPriceContrastService.queryByParams(getDoBean);
        if(CollectionUtils.isEmpty(contrastList)){
            resultMap.put("message","没有数据");
            resultMap.put("SUCCESS", false);
            Cat.logEvent("sendEmail", "没有数据", Event.SUCCESS, "data is empty");
            logger.info("====================sendEmail  data is empty====================");
            return resultMap;
        }
        List<TRoomPriceContrastDto> emailList=new ArrayList<TRoomPriceContrastDto>();
        Map<Integer,String>cityMap=new HashMap<Integer, String>();
        for (TRoomPriceContrastDto contrastBean:contrastList){
            if (cityMap.get(contrastBean.getCityCode())==null){
                TCityDto cityDto=cityService.getByCode(contrastBean.getCityCode().toString());
                cityMap.put(contrastBean.getCityCode(),cityDto.getQueryCityName());
            }
            contrastBean.setCityName(cityMap.get(contrastBean.getCityCode()));
            emailList.add(contrastBean);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", emailList);
        String mailContent=null;
        try {
            mailContent = FreeMarkerTemplateUtils.process(map, "mail_priceContrast.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        SyServDictItemDto getDictBean=new SyServDictItemDto();
        getDictBean.setDictId("mail_price_contrast");
        getDictBean.setItemFlag("1");
        List<SyServDictItemDto> dictList=syServDictItemService.queryByParams(getDictBean);
        for (SyServDictItemDto dict:dictList) {
            logger.info("************send email " + dict.getItemName() + "***************");
            System.out.println(mailContent);
            EmailSend.emailSend(null, mailContent, "酒店房价变更记录" + DateUtils.format_yMd(new Date()), dict.getItemName());
        }
//        Map<String,String> emailList=new HashMap<String, String>();
//        emailList.put("kxl","654195681@qq.com");
//        emailList.put("figo","rongwei.yang@imike.com");
//        emailList.put("bq","bingqiu.yuan@imike.com");
//        emailList.put("sj","jun.shi@imike.com");
//        for (String email:emailList.keySet()) {
//            logger.info("************send email " + email + "***************");
//            EmailSend.emailSend(null, mailContent, "酒店房价变更记录" + DateUtils.format_yMd(new Date()), emailList.get(email));
//        }

        resultMap.put("message","邮件发送成功");
        resultMap.put("SUCCESS", true);
        Cat.logEvent("doPriceDump", "邮件发送成功", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================doPriceDump method end time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        return resultMap;
    }
    public Map<String,Object> jobRun(){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        Cat.logEvent("doPriceDump", "酒店价格备份", Event.SUCCESS,
                "beginTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================doPriceDump method begin time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        Map<String,Object> dumpMap= doPriceDump();
        if ((Boolean)dumpMap.get("SUCCESS")){
            Map<String,Object> contrastMap=priceContrast();
            if ((Boolean)contrastMap.get("SUCCESS")) {
                Map<String,Object> sendMap=sendEmail(new TRoomPriceContrastDto());
                if ((Boolean)contrastMap.get("SUCCESS")){
                    resultMap.put("message","SUCCESS");
                    resultMap.put("SUCCESS", true);
                }else {
                    resultMap.put("message","send Email fail");
                    resultMap.put("SUCCESS", false);
                }
            }else {
                resultMap.put("message","price contrast fail");
                resultMap.put("SUCCESS", false);
            }
        }else {
            resultMap.put("message","hotel room price dump fail");
            resultMap.put("SUCCESS", false);
        }

        Cat.logEvent("doPriceDump", "SUCCESS", Event.SUCCESS,
                "endTime=" + DateUtils.format_yMdHms(new Date())
        );
        logger.info("====================doPriceDump method end time{}===================="
                , DateUtils.format_yMdHms(new Date()));
        priceContrast();
        return resultMap;
    }
}
