package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.OrderSendJobService;
import com.mk.taskfactory.api.QHotelToRedisService;
import com.mk.taskfactory.api.dtos.HotelScoreDto;
import com.mk.taskfactory.api.dtos.OrderToCsDto;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.api.dtos.TFacilityDto;
import com.mk.taskfactory.api.dtos.crawer.*;
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
@RequestMapping(value = "/orderSend", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderSendToCsController {
    @Autowired
    private OrderSendJobService orderSendJobService;

    @RequestMapping(value = "/orderSendToCs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> orderSendToCs(OrderToCsDto bean) {
        Map<String, Object> result = orderSendJobService.orderSendToCs(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}