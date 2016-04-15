package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.api.dtos.ht.OnlineHotelDto;
import com.mk.taskfactory.api.dtos.ht.OnlineHotelRecommendDto;
import com.mk.taskfactory.api.dtos.ods.OnlineHotelPriorityDto;
import com.mk.taskfactory.api.dtos.ht.OnlineHotelRoomTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/hoteltoredis", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelToRedisController {
    @Autowired
    private QHotelToRedisService qHotelToRedisService;

    @RequestMapping(value = "/tFacilityToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> tFacilityToRedis(TFacilityDto bean) {
        Map<String, Object> result = qHotelToRedisService.tFacilityToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/onlineCityToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> onlineCityToRedis(TCityListDto bean) {
        Map<String, Object> result = qHotelToRedisService.onlineCityToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelPriorityToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> hotelPriorityToRedis(OnlineHotelPriorityDto bean) {
        Map<String, Object> result = qHotelToRedisService.hotelPriorityToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/roomTypePicMappingToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> roomTypePicMappingToRedis(OnlineHotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.roomTypePicMappingToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/onlineHotelToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> onlineHotelToRedis(OnlineHotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.onlineHotelToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelResourceToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> hotelResourceToRedis(OnlineHotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.hotelResourceToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/onlineRoomTypeToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> onlineRoomTypeToRedis(OnlineHotelRoomTypeDto bean) {
        Map<String, Object> result = qHotelToRedisService.onlineRoomTypeToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/onlineHotelRecommendToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> onlineHotelRecommendToRedis(OnlineHotelRecommendDto bean) {
        Map<String, Object> result = qHotelToRedisService.onlineHotelRecommendToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/cityHotelSetToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> cityHotelSetToRedis(TCityListDto bean) {
        Map<String, Object> result = qHotelToRedisService.cityHotelSetToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/indexerjob", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> indexerjob() {
        qHotelToRedisService.indexerjob();
        return new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
    }


}