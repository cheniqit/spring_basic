package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.dtos.RoomSaleToOtsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomSaleController {

    @RequestMapping(value = "/querySaleRoom", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<RoomSaleToOtsDto>> querySaleRoom(HttpSession httpSession) {
        List<RoomSaleToOtsDto>result=new ArrayList<RoomSaleToOtsDto>();

//        validRateTaskService.validRateTaskRun();
        return new ResponseEntity<List<RoomSaleToOtsDto>>(result, HttpStatus.OK);
    }

}
