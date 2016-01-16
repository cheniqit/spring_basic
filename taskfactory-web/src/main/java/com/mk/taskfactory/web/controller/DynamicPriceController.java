package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.DynamicPriceService;
import com.mk.taskfactory.api.PriceToRedisService;
import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.model.TRoomTypeDynamicPrice;
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
@RequestMapping(value = "/dynamic", produces = MediaType.APPLICATION_JSON_VALUE)
public class DynamicPriceController {
    @Autowired
    private DynamicPriceService dynamicPriceService;

    @RequestMapping(value = "/dynamicPriceToLog", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> dynamicPriceToLog(TRoomTypeDynamicPriceDto bean) {
        Map<String,Object> result=dynamicPriceService.dynamicPriceToLog(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}
