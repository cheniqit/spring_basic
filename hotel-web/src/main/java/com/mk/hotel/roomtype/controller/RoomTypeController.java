package com.mk.hotel.roomtype.controller;

import com.dianping.cat.Cat;
import com.mk.framework.proxy.http.JSONUtil;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.json.roomtype.RoomTypeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
@RequestMapping(value = "/roomtype", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomTypeStockService roomTypeStockService;

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

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/roomtypestock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypeStockPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.roomTypeStock.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 2);

        this.roomTypeStockService.updateStock("","",);
        this.roomTypeStockService.lockRoomType("123","456",new Date(), calendar.getTime(), 2);

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }
}

