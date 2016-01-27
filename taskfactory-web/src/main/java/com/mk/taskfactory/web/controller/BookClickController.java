package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.BookClickService;
import com.mk.taskfactory.api.HotelPicSyncService;
import com.mk.taskfactory.api.dtos.EHotelPicResDto;
import com.mk.taskfactory.api.dtos.HotelHourClickDto;
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
@RequestMapping(value = "/bookclick", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookClickController {

    @Autowired
    private BookClickService bookClickService;


    @RequestMapping(value = "/thotelHourStatistics", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> hotelSync(HotelHourClickDto bean) {
        Map<String,Object> result=bookClickService.thotelHourStatistics(bean);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}
