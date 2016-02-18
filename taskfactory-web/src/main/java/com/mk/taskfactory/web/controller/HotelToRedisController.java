package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.dtos.crawer.QHotelDto;
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
        Map<String,Object> result=qHotelToRedisService.qHotelToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
