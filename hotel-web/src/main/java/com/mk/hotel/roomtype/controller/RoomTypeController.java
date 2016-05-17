package com.mk.hotel.roomtype.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.mk.framework.proxy.http.JSONUtil;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.json.roomtype.RoomTypeJson;
import com.mk.hotel.roomtype.json.roomtypeprice.PriceInfoJson;
import com.mk.hotel.roomtype.json.roomtypeprice.RoomTypePriceJson;
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
import java.util.*;

@Controller
@RequestMapping(value = "/roomtype", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomTypePriceService roomTypePriceService;

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

        //
        JSONObject bodyJson = JSON.parseObject(body);
        String data = bodyJson.getString("data");

        //
        RoomTypeJson roomTypeJson = JSONUtil.fromJson(data, RoomTypeJson.class);

        RoomTypeDto roomTypeDto = new RoomTypeDto();

        roomTypeDto.setFangHotelId(roomTypeJson.getHotelid());
        roomTypeDto.setFangId(roomTypeJson.getId());
        roomTypeDto.setName(roomTypeJson.getName());

        Integer intArea = null;
        try {
            intArea = new BigDecimal(roomTypeJson.getArea()).intValue();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        roomTypeDto.setArea(intArea);

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
        roomTypeDto.setStatus(roomTypeJson.getStatus());
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

        //
        JSONObject bodyJson = JSON.parseObject(body);
        String data = bodyJson.getString("data");

        //json
        RoomTypePriceJson roomTypePriceJson = JSONUtil.fromJson(data, RoomTypePriceJson.class);
        //fang-hotelId
        Long fangHotelId = roomTypePriceJson.getHotelid();

        //roomTypeJsonList
        List<RoomTypePriceDto> roomTypePriceDtoList = new ArrayList<RoomTypePriceDto>();
        List<com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson> roomTypeJsonList = roomTypePriceJson.getRoomtypeprices();
        for (com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson roomTypeJson : roomTypeJsonList) {
            //fang-roomTypeId
            Long fangRoomTypeId = roomTypeJson.getRoomtypeid();

            List<PriceInfoJson> priceInfoJsonList = roomTypeJson.getPriceinfos();
            for (PriceInfoJson priceInfoJson : priceInfoJsonList) {

                //day
                Date day = null;
                String strDay = priceInfoJson.getDate();
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
                String strPrice = priceInfoJson.getCost();
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

                //dto
                RoomTypePriceDto roomTypePriceDto = new RoomTypePriceDto();
                roomTypePriceDto.setDay(day);
                roomTypePriceDto.setPrice(price);
                roomTypePriceDto.setFangHotelId(fangHotelId);
                roomTypePriceDto.setFangRoomTypeId(fangRoomTypeId);
                roomTypePriceDtoList.add(roomTypePriceDto);
            }
        }

        this.roomTypePriceService.saveOrUpdateByFangId(roomTypePriceDtoList);

        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/mergeRoomType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomType(Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            roomTypeService.mergeRoomType(pageNo);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/mergeRoomTypePrice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomTypePrice(Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            roomTypeService.mergeRoomTypePrice(pageNo);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/mergeRoomTypeStock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomTypeStock(Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            roomTypeService.mergeRoomTypeStock(pageNo);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

}

