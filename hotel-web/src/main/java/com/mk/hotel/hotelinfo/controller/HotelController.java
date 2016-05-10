package com.mk.hotel.hotelinfo.controller;

import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/findHotelById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LinkedHashMap<String, Object>> findHotelById() {
        HotelDto hotel = hotelService.findById(1L);
        LinkedHashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("hotel", hotel);
        return new ResponseEntity<LinkedHashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelAll", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LinkedHashMap<String, Object>> hotelAllPush() {


        LinkedHashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<LinkedHashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LinkedHashMap<String, Object>> hotelPush() {


        LinkedHashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<LinkedHashMap<String, Object>>(result, HttpStatus.OK);
    }

}

