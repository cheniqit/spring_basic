package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.PriceToRedisService;
import com.mk.taskfactory.api.dtos.TRoomSaleAgreementPriceDto;
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
@RequestMapping(value = "/redis", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceToRedisController {
    @Autowired
    private PriceToRedisService priceToRedisService;

    @RequestMapping(value = "/pricetoredis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> priceToRedis(TRoomSaleAgreementPriceDto bean) {
        Map<String,Object> result=priceToRedisService.priceToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/updatedealcount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateDealCountToRedis(TRoomSaleAgreementPriceDto bean) {
        Map<String,Object> result=priceToRedisService.updateDealCountToRedis(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteredis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteRedis(Integer id, String key) {
        Map<String,Object> result=priceToRedisService.deleteRedis(id,key);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
