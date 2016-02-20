package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.THotelDto;
import com.mk.taskfactory.api.dtos.crawer.*;
import com.mk.taskfactory.model.TFacility;
import com.mk.taskfactory.model.crawer.QComment;
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

    @RequestMapping(value = "/qHotelToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qHotelToRedis(QHotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.qHotelToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/qCommentToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qHotelToRedis(QCommentDto bean) {
        Map<String, Object> result = qHotelToRedisService.qCommentToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/qCommentDetailToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qCommentDetailToRedis(QCommentDetailDto bean) {
        Map<String, Object> result = qHotelToRedisService.qCommentDetailToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/qFacilityToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qFacilityToRedis(QFacilityDto bean) {
        Map<String, Object> result = qHotelToRedisService.qFacilityToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/qHotelFacilityToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qHotelFacilityToRedis(QHotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.qHotelFacilityToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/qHotelRoomTypeSetToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qHotelRoomTypeSetToRedis(QHotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.qHotelRoomTypeSetToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/qHotelRoomtypeToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qHotelToRedis(QHotelRoomtypeDto bean) {
        Map<String, Object> result = qHotelToRedisService.qHotelRoomtypeToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/qHotelSurroundSetToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> qHotelSurroundSetToRedis(QHotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.qHotelSurroundSetToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/tFacilityToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> tFacilityToRedis(TFacilityDto bean) {
        Map<String, Object> result = qHotelToRedisService.tFacilityToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/tHotelToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> tHotelToRedis(THotelDto bean) {
        Map<String, Object> result = qHotelToRedisService.tHotelToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/validPriceHotelToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> validPriceHotelToRedis() {
        Map<String, Object> result = qHotelToRedisService.validPriceHotelToRedis();
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/validPriceRoomTypeToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> validPriceRoomTypeToRedis() {
        Map<String, Object> result = qHotelToRedisService.validPriceRoomTypeToRedis();
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/cityHotelSetToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> cityHotelSetToRedis(TCityDto dto) {
        Map<String, Object> result = qHotelToRedisService.cityHotelSetToRedis(dto);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}