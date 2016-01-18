package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.DynamicPriceService;
import com.mk.taskfactory.api.PmsDynamicInfoService;
import com.mk.taskfactory.api.dtos.TRoomTypeDynamicPriceDto;
import com.mk.taskfactory.api.dtos.TRoomTypePmsDynamicInfoDto;
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
@RequestMapping(value = "/pmsdynamicinfo", produces = MediaType.APPLICATION_JSON_VALUE)
public class PmsDynamicInfoController {
    @Autowired
    private PmsDynamicInfoService pmsDynamicInfoService;

    @RequestMapping(value = "/sync", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> pmsDynamicInfoToLog(TRoomTypePmsDynamicInfoDto bean) {
        Map<String,Object> result=pmsDynamicInfoService.pmsDynamicToLog(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
