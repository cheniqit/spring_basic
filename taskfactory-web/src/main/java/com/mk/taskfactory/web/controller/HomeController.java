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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/roomSale", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @Autowired
    private ValidRateTaskService validRateTaskService;

    @RequestMapping(value = "/validRateTaskRun", method = RequestMethod.GET)
    @ResponseBody
    public void validRateTaskRun() {
        this.validRateTaskService.validRateTaskRun();
        return;
    }
    @RequestMapping(value = "/updateOnline", method = RequestMethod.GET)
    @ResponseBody
    public void updateOnline() {
        this.validRateTaskService.updateOnline(new Date());
        return;
    }

    @RequestMapping(value = "/dateReback", method = RequestMethod.GET)
    @ResponseBody
    public void dateReback() {
        this.validRateTaskService.dateReback();
        return;
    }

}
