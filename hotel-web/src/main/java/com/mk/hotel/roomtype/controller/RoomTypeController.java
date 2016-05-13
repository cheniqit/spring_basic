package com.mk.hotel.roomtype.controller;

import com.dianping.cat.Cat;
import com.mk.framework.proxy.http.JSONUtil;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.json.roomtype.RoomTypeJson;
import com.mk.hotel.roomtype.json.roomtypeprice.PriceInfoJson;
import com.mk.hotel.roomtype.json.roomtypeprice.RoomTypePriceJson;
import com.mk.hotel.roomtype.model.RoomTypePrice;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/roomtype", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private LogPushService logPushService;

    @RequestMapping(value = "/roomtype", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypePush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.roomType.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        RoomTypeJson roomTypeJson = JSONUtil.fromJson(body, RoomTypeJson.class);

        RoomTypeDto roomTypeDto = new RoomTypeDto();

        roomTypeDto.setHotelId(roomTypeJson.getHotelid());
        roomTypeDto.setName(roomTypeJson.getName());
        roomTypeDto.setArea(roomTypeJson.getArea());

        String strBedType = roomTypeJson.getBedtype();
        if (StringUtils.isNotBlank(strBedType)) {
            try {
                Integer bedType = Integer.valueOf(strBedType);
                roomTypeDto.setBedType(bedType);
            } catch (NumberFormatException e) {

            }
        }

        roomTypeDto.setRoomNum(roomTypeJson.getRoomnum());
        roomTypeDto.setPrepay(roomTypeJson.getPrepay());
        roomTypeDto.setBreakfast(roomTypeJson.getBreakfast());
        roomTypeDto.setRefund(roomTypeJson.getRefund());
        roomTypeDto.setMaxRoomNum(roomTypeJson.getMaxroomnum());
        roomTypeDto.setRoomTypePics(roomTypeJson.getRoomtypepics());

        this.roomTypeService.saveOrUpdateByFangId(roomTypeDto);

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("headers.token",headers.get("token"));
        result.put("body", body);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/roomtypeprice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypePricePush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.roomTypePrice.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //json
        RoomTypePriceJson roomTypePriceJson = JSONUtil.fromJson(body, RoomTypePriceJson.class);
        //fang-hotelId
        Long fangHotelId = roomTypePriceJson.getHotelid();

        //roomTypeJsonList
        List<com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson> roomTypeJsonList = roomTypePriceJson.getRoomtypes();
        for (com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson roomTypeJson : roomTypeJsonList) {
            //fang-roomTypeId
            Long fangRoomTypeId = roomTypeJson.getRoomtypeid();

            List<PriceInfoJson> priceInfoJsonList = roomTypeJson.getPriceinfo();
            for (PriceInfoJson priceInfoJson : priceInfoJsonList) {

                //day
                Date day = null;
                String strDay = priceInfoJson.getDay();
                if (StringUtils.isBlank(strDay)) {
                    continue;
                } else {
                    try {
                        day = format.parse(strDay);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }

                //price
                BigDecimal price = null;
                String strPrice = priceInfoJson.getPrice();
                if (StringUtils.isBlank(strPrice)) {
                    continue;
                } else {
                    try {
                        price = new BigDecimal(strPrice);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }


                RoomTypePriceDto roomTypePriceDto = new RoomTypePriceDto();
                roomTypePriceDto.setDay(day);
                roomTypePriceDto.setPrice(price);
                roomTypePriceDto.setFangHotelId(fangHotelId);
                roomTypePriceDto.setFangRoomTypeId(fangRoomTypeId);

            }
        }

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

}

