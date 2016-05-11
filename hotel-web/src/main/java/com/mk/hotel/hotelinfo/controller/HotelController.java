package com.mk.hotel.hotelinfo.controller;

import com.dianping.cat.Cat;
import com.mk.framework.net.HttpUtils;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private LogPushService logPushService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> test() {
        String url = "http://api.fangbaba.cc/open/area/queryProvince";
        String resultStr = null;
        try {
            resultStr =  HttpUtils.doPost(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("hotel", resultStr);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findHotelById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findHotelById() {
        HotelDto hotel = hotelService.findById(1L);
        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("hotel", hotel);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelall", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelAllPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);

            //TODO
            logPushDto.setType(1l);

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);

            //TODO
            logPushDto.setType(1l);

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelfacility", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelFacilityPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);

            //TODO
            logPushDto.setType(1l);

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }
}

