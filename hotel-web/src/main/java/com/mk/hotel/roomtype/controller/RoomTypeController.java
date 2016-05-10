package com.mk.hotel.roomtype.controller;

import com.mk.hotel.roomtype.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
@RequestMapping(value = "/roomtype", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping(value = "/roomtype", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypePush(@RequestHeader HttpHeaders headers) {

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("headers.token",headers.get("token"));
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

}

