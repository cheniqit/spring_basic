package com.mk.pms.hoteldetail.controller;

import com.mk.framework.net.HttpUtils;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.log.LogPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/pmshotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class PmsHotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private LogPushService logPushService;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelDetail() {
        String url = "http://api.fangbaba.cc/open/area/queryProvince";
        String resultStr = null;
        try {
            resultStr =  HttpUtils.doPost(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("hotel", resultStr);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


}

