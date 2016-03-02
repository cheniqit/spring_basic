package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.BookClickService;
import com.mk.taskfactory.api.CityListService;
import com.mk.taskfactory.api.dtos.HotelHourClickDto;
import com.mk.taskfactory.api.dtos.TCityDto;
import com.mk.taskfactory.api.dtos.TCityListDto;
import com.mk.taskfactory.api.ots.CityService;
import com.mk.taskfactory.api.ots.TCityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/city", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityListController {

    @Autowired
    private CityListService cityListService;

    @RequestMapping(value = "/cityToCityList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> cityToCityList(TCityDto bean) {
        Map<String,Object> result=cityListService.cityToCityList(bean);
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }

}
