package com.mk.taskfactory.web.cps.controller;


import com.mk.taskfactory.api.RoomTypeBedService;
import com.mk.taskfactory.api.ValidRateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping(value = "/roomSale", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class CpsOrderDetailController {

    @Autowired
    private ValidRateTaskService validRateTaskService;

    @Autowired
    private RoomTypeBedService roomTypeBedService;

    @RequestMapping(value = "/validRateTaskRun", method = RequestMethod.GET)
    @ResponseBody
    public void validRateTaskRun() {
        this.validRateTaskService.validRateTaskRun();
        return;
    }

    @RequestMapping(value = "/validRateTaskRunToday", method = RequestMethod.GET)
    @ResponseBody
    public void validRateTaskRunToday() {
        this.validRateTaskService.validRateTaskRunToday();
        return;
    }

    @RequestMapping(value = "/updateOnline", method = RequestMethod.GET)
    @ResponseBody
    public void updateOnline() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.HOUR, 1);
        Date startTime = cal.getTime();
        System.out.println(startTime);
        this.validRateTaskService.updateOnline(startTime);
        return;
    }

    @RequestMapping(value = "/dateReback", method = RequestMethod.GET)
    @ResponseBody
    public void dateReback() {
        this.validRateTaskService.dateReback();
        return;
    }
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public void test() {
////        this.roomTypeBedService.createByRoomTypeId(314l,1l);
//        this.roomTypeBedService.deleteByRoomTypeId(1L);
//    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public void updateRoomSalePrice() {
        this.validRateTaskService.updateRoomSalePrice();
    }

    @RequestMapping(value = "/initHotel", method = RequestMethod.GET)
    @ResponseBody
    public void initHotel(Boolean isInitValid, Long hotelId) {
        this.validRateTaskService.initHotel(isInitValid,hotelId);
    }
}
