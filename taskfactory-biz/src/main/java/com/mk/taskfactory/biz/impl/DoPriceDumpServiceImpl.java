package com.mk.taskfactory.biz.impl;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.dtos.ods.TRoomTypePriceDumpDto;
import com.mk.taskfactory.api.ods.RoomTypePriceDumpService;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        Integer pageSize=100;//100
        Integer pageNum=roomTypeCount/pageSize;
        logger.info(String.format("====================doPriceDump roomTypeCount=" + roomTypeCount + "===================="));
        for (int i=0;i<=pageNum;i++) {
            TRoomTypeDto roomTypeSearchBean = new TRoomTypeDto();
            roomTypeSearchBean.setPageIndex(i);
            roomTypeSearchBean.setPageSize(pageSize);
            List<TRoomTypeDto> roomTypeList = roomTypeService.queryJionThotel(roomTypeSearchBean);
            logger.info(String.format("====================queryJionThotel pageIndex={}&pageSize={}====================")
                    , i, pageSize);
            if (CollectionUtils.isEmpty(roomTypeList)) {
                logger.info(String.format("====================queryJionThotel pageIndex={}&pageSize={} is empty====================")
                        , i, pageSize);
                continue;
            }
            for (TRoomTypeDto roomType:roomTypeList){
                logger.info(String.format("====================doPriceDump roomType id={}&hotelId={}====================")
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
                TRoomSaleConfigDto checkRoomSaleConfigBean=new TRoomSaleConfigDto();
                checkRoomSaleConfigBean.setRoomTypeId(roomType.getId());
                checkRoomSaleConfigBean.setValid("T");
                List<TRoomSaleConfigDto> roomSaleConfigDtoList=roomSaleConfigService.queryRoomSaleConfigByParams(checkRoomSaleConfigBean);
                if (!CollectionUtils.isEmpty(roomSaleConfigDtoList)) {
                    for (TRoomSaleConfigDto roomSaleConfigDto:roomSaleConfigDtoList){
                        logger.info("============doPriceDump hotelId={}&roomTypeId={}&saleTypeId{}====================",
                                roomType.getThotelId(),roomType.getId(),roomSaleConfigDto.getSaleRoomTypeId());
                        roomTypePriceDumpDto.setIsPromo(true);
                        roomTypePriceDumpDto.setPromoId(roomSaleConfigDto.getSaleTypeId());
                        roomTypePriceDumpDto.setPromoPrice(roomSaleConfigDto.getSaleValue());
                        roomTypePriceDumpDto.setSettlePrice(roomSaleConfigDto.getSettleValue());
                        roomTypePriceDumpService.save(roomTypePriceDumpDto);
                    }
                }else {
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
}
