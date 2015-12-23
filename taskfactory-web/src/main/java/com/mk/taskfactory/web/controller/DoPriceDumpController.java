package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.DoPriceDumpService;
import com.mk.taskfactory.api.dtos.ods.TRoomPriceContrastDto;
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
@RequestMapping(value = "/hotelPriceDump", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoPriceDumpController {

    @Autowired
    private DoPriceDumpService doPriceDumpService;

    @RequestMapping(value = "/doPriceDump", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> doPriceDump() {
        Map<String,Object> result=doPriceDumpService.doPriceDump();
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/priceContrast", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> priceContrast() {
        Map<String,Object> result=doPriceDumpService.priceContrast();
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> sendEmail(TRoomPriceContrastDto bean) {
        Map<String,Object> result=doPriceDumpService.sendEmail(bean);
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
}
