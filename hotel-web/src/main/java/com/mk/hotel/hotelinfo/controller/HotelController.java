package com.mk.hotel.hotelinfo.controller;

import com.dianping.cat.Cat;
import com.mk.framework.net.HttpUtils;
import com.mk.framework.proxy.http.JSONUtil;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
import com.mk.hotel.hotelinfo.json.facility.HotelFacilityJson;
import com.mk.hotel.hotelinfo.json.facility.RoomTypeFacilityJson;
import com.mk.hotel.hotelinfo.json.hotel.HotelJson;
import com.mk.hotel.hotelinfo.json.hotelall.HotelAllJson;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelFacilityService hotelFacilityService;

    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;

    @Autowired
    private LogPushService logPushService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> test() {
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

    @RequestMapping(value = "/findHotelById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findHotelById() {
        HotelDto hotel = hotelService.findById(1L);
        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("hotel", hotel);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelall", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelAllPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.hotelAll.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        HotelAllJson hotelAllJson = JSONUtil.fromJson(body, HotelAllJson.class);

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.hotel.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        HotelJson hotelJson = JSONUtil.fromJson(body, HotelJson.class);

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/hotelfacility", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelFacilityPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.hotelFacility.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        //
        HotelFacilityJson facilityJson = JSONUtil.fromJson(body, HotelFacilityJson.class);
        Long fangHotelId = facilityJson.getHotelid();
        String hotelTag = facilityJson.getTagid();

        if (StringUtils.isNotBlank(hotelTag)) {
            String[] holelTags =  hotelTag.split(",");

            List<HotelFacilityDto> hotelFacilityDtoList = new ArrayList<HotelFacilityDto>();
            for (String strTag : holelTags) {
                Long tag = null;
                try {
                    tag = Long.parseLong(strTag);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Cat.logError(e);
                    continue;
                }

                HotelFacilityDto dto = new HotelFacilityDto();
                dto.setFangHotelId(fangHotelId);
                dto.setFacilityId(tag);
                hotelFacilityDtoList.add(dto);
            }

            this.hotelFacilityService.saveOrUpdateByFangId(hotelFacilityDtoList);
        }

        //
        List<RoomTypeFacilityJson> roomTypeFacilityJsonList = facilityJson.getRoomtype();
        for (RoomTypeFacilityJson json : roomTypeFacilityJsonList) {
            Long fangRoomTypeId = json.getRoomtypeid();
            String roomTypeTag = json.getRoomtypetagid();

            if (StringUtils.isNotBlank(roomTypeTag)) {
                String[] roomTypeTags = roomTypeTag.split(",");

                List<RoomTypeFacilityDto> roomTypeFacilityDtoList = new ArrayList<RoomTypeFacilityDto>();
                for(String strTag : roomTypeTags) {
                    Long tag = null;
                    try {
                        tag = Long.parseLong(strTag);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }

                    //
                    RoomTypeFacilityDto dto = new RoomTypeFacilityDto();
                    dto.setFangHotelId(fangHotelId);
                    dto.setFangRoomTypeId(fangRoomTypeId);
                    dto.setFacilityId(tag);
                    roomTypeFacilityDtoList.add(dto);
                }

                this.roomTypeFacilityService.saveOrUpdateByFangid(roomTypeFacilityDtoList);
            }

        }

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/mergePmsHotel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergePmsHotel(Integer pageNo) {
        try {
           if(pageNo == null){
               pageNo = 1;
           }
           hotelService.mergePmsHotel(pageNo);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }
}

