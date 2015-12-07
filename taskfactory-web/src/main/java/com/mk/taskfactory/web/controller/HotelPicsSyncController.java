package com.mk.taskfactory.web.controller;


import com.dianping.cat.Cat;
import com.mk.taskfactory.api.HotelPicSyncService;
import com.mk.taskfactory.api.RoomTypeBedService;
import com.mk.taskfactory.api.ValidRateTaskService;
import com.mk.taskfactory.biz.cps.impl.CpsOrderDetailTaskServiceImpl;
import com.mk.taskfactory.biz.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

}
