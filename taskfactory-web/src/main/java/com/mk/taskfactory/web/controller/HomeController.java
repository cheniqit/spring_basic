package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.OnSaleFallbackService;
import com.mk.taskfactory.api.ValidRateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/roomSale", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @Autowired
    private OnSaleFallbackService onSaleFallbackService;
    @Autowired
    private ValidRateTaskService validRateTaskService;

    @RequestMapping(value = "/roomSaleBegin", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> test(HttpSession httpSession) {
        HashMap<String, Object> result = new HashMap<String, Object>();
       // result.put("check", "123");
       // Cat.logEvent("CHECK_TEST", "123");
        validRateTaskService.validRateTaskRun();
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/roomSaleBack", method = RequestMethod.GET)
    @ResponseBody
    public void testRoomSale(HttpSession httpSession) {

        this.onSaleFallbackService.onSaleFallback();
        return;
    }

}
