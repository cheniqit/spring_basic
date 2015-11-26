package com.mk.taskfactory.web.controller;


import com.dianping.cat.Cat;
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
@RequestMapping(value = "/roomSale", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @Autowired
    private ValidRateTaskService validRateTaskService;

    @Autowired
    private RoomTypeBedService roomTypeBedService;

    @Autowired
    private CpsOrderDetailTaskServiceImpl cpsOrderDetailTaskService;

    @RequestMapping(value = "/validRateTaskRun", method = RequestMethod.GET)
    @ResponseBody
    public void validRateTaskRun() {
        Cat.logEvent("validRateTaskRun ", "特价活动初始化 time="+DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        this.validRateTaskService.validRateTaskRun();
        return;
    }

    @RequestMapping(value = "/cpsinit", method = RequestMethod.GET)
    @ResponseBody
    public String cpsinit() {
        Cat.logEvent("cpsinit ", "cps 渠道信息job执行 time="+DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        cpsOrderDetailTaskService.cpsOrderProduce();
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        rtnMap.put("code", "222");
        return "ok";
    }

    @RequestMapping(value = "/validRateTaskRunToday", method = RequestMethod.GET)
    @ResponseBody
    public void validRateTaskRunToday() {
        Cat.logEvent("validRateTaskRunToday ", "特价活动初始化Today time="+DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
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
        Cat.logEvent("updateOnline ", "特价活动上线 time="+ DateUtils.dateToString(startTime,"yyyy-MM-dd HH:mm:ss"));
        this.validRateTaskService.updateOnline(startTime);
        return;
    }

    @RequestMapping(value = "/dateReback", method = RequestMethod.GET)
    @ResponseBody
    public void dateReback() {
        Cat.logEvent("dateReback ", "特价活动下线 time="+DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
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
        Cat.logEvent("updateRoomSalePrice ", "手动更新眯客价 time="+DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        this.validRateTaskService.updateRoomSalePrice();
    }

    @RequestMapping(value = "/initHotel", method = RequestMethod.GET)
    @ResponseBody
    public void initHotel(Boolean isInitValid, Long hotelId) {
        Cat.logEvent("initHotel ", "刷新索引" + hotelId.toString() + "  date=" + DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        return;
        // this.validRateTaskService.initHotel(isInitValid,hotelId);
    }
}
