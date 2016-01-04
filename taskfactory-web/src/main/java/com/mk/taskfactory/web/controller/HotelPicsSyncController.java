package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.HotelPicSyncService;
import com.mk.taskfactory.api.dtos.EHotelPicResDto;
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
@RequestMapping(value = "/hotelPicsSync", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelPicsSyncController {

    @Autowired
    private HotelPicSyncService hotelPicSyncService;


    @RequestMapping(value = "/hotelSync", method = RequestMethod.GET)
    @ResponseBody
    public void hotelSync() {
        hotelPicSyncService.hotelPicSync();
        return;
    }
    @RequestMapping(value = "/roomTypeInfoPicSync", method = RequestMethod.GET)
    @ResponseBody
    public void roomTypeInfoPicSync() {
        hotelPicSyncService.roomTypeInfoPicSync();
        return;
    }
    @RequestMapping(value = "/deleteByParams", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> deleteByParams(EHotelPicResDto bean) {
        Map<String,Object> result=hotelPicSyncService.deleteByParams(bean);
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/truncate", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String,Object>> truncate() {
        Map<String,Object> result=hotelPicSyncService.truncate();
        return new ResponseEntity<Map<String,Object>>(result, HttpStatus.OK);
    }
}
