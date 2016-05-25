package com.mk.hotel.hotelinfo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.JSONUtil;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
import com.mk.hotel.hotelinfo.json.facility.FacilityJson;
import com.mk.hotel.hotelinfo.json.facility.HotelFacilityJson;
import com.mk.hotel.hotelinfo.json.facility.RoomTypeFacilityJson;
import com.mk.hotel.hotelinfo.json.hotel.HotelJson;
import com.mk.hotel.hotelinfo.json.hotelall.HotelAllJson;
import com.mk.hotel.hotelinfo.json.hotelall.RoomTypeJson;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

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


        List<HotelAllJson> hotelJsonList = new ArrayList<HotelAllJson>();
        try {
            //
            JSONArray hotelAllArray = JSON.parseArray(body);
            int arraySize = hotelAllArray.size();

            for (int i = 0; i < arraySize; i++) {
                String strHotelALLJson = hotelAllArray.get(i).toString();

                //
                HotelAllJson hotelJson = JSONUtil.fromJson(strHotelALLJson, HotelAllJson.class);
                hotelJsonList.add(hotelJson);
            }
        } catch (Exception e) {
            throw new MyException("-99", "-99", "格式错误");
        }

        for (HotelAllJson hotelJson : hotelJsonList) {
            //
            HotelDto hotelDto = new HotelDto();
            hotelDto.setFangId(hotelJson.getId());
            hotelDto.setName(hotelJson.getHotelname());
            hotelDto.setAddr(hotelJson.getDetailaddr());
            hotelDto.setPhone(hotelJson.getHotelphone());
            hotelDto.setLat(hotelJson.getLatitude());
            hotelDto.setLon(hotelJson.getLongitude());
            hotelDto.setDefaultLeaveTime(hotelJson.getDefaultleavetime());
            hotelDto.setHotelType(String.valueOf(hotelJson.getHoteltype()));
            hotelDto.setRetentionTime(hotelJson.getRetentiontime());
            hotelDto.setRepairTime(hotelJson.getRepairtime());
            hotelDto.setRegTime(hotelJson.getRegtime());
            hotelDto.setIntroduction(hotelJson.getIntroduction());
            hotelDto.setProvCode(String.valueOf(hotelJson.getProvcode()));
            hotelDto.setCityCode(String.valueOf(hotelJson.getCitycode()));
            hotelDto.setDisCode(String.valueOf(hotelJson.getDiscode()));
            hotelDto.setIsValid("T");
            hotelDto.setOpenTime(hotelJson.getOpentime());
            hotelDto.setPic(hotelJson.getHotelpic());
            hotelDto.setPics(hotelJson.getHotelpics());

            //
            List<RoomTypeJson> roomTypeJsonList = hotelJson.getRoomtypes();
            if (null != roomTypeJsonList) {

                List<RoomTypeDto> roomTypeDtoList = new ArrayList<RoomTypeDto>();
                for (RoomTypeJson roomTypeJson : roomTypeJsonList) {
                    //
                    Integer intArea = null;
                    try {
                        intArea = new BigDecimal(roomTypeJson.getArea()).intValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        throw new MyException("-99", "-99", "area格式错误");
                    }

                    //
                    Integer intPrePay = null;
                    try {
                        intPrePay = Integer.parseInt(roomTypeJson.getPrepay());
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                        Cat.logError(e);
                        throw new MyException("-99", "-99", "prepay格式错误");
                    }

                    //
                    Integer intBreakfast = null;
                    try {
                        intBreakfast = Integer.parseInt(roomTypeJson.getBreakfast());
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                        Cat.logError(e);
                        throw new MyException("-99", "-99", "breakfast格式错误");
                    }

                    //
                    String isValid = null;
                    Integer status = roomTypeJson.getStatus();

                    if (null == status) {
                        isValid = "T";
                    } else {
                        if (0 == roomTypeJson.getStatus()) {
                            isValid = "T";
                        } else {
                            isValid = "F";
                        }
                    }

                    //
                    RoomTypeDto roomTypeDto = new RoomTypeDto();
                    roomTypeDto.setFangHotelId(hotelJson.getId());
                    roomTypeDto.setFangId(roomTypeJson.getId());
                    roomTypeDto.setName(roomTypeJson.getName());
                    roomTypeDto.setArea(intArea);
                    roomTypeDto.setBedType(roomTypeJson.getBedtype());
                    roomTypeDto.setBedSize(roomTypeJson.getBedsize());
                    roomTypeDto.setRoomNum(roomTypeJson.getRoomnum());
                    roomTypeDto.setPrepay(intPrePay);
                    roomTypeDto.setBreakfast(intBreakfast);
                    roomTypeDto.setStatus(roomTypeJson.getStatus());
                    roomTypeDto.setRefund(roomTypeJson.getRefund());
                    roomTypeDto.setMaxRoomNum(roomTypeJson.getMaxroomnum());
                    roomTypeDto.setRoomTypePics(roomTypeJson.getRoomtypepics());

                    roomTypeDto.setIsValid(isValid);

                    roomTypeDtoList.add(roomTypeDto);
                }

                hotelDto.setRoomTypeDtoList(roomTypeDtoList);
            }

            this.hotelService.saveOrUpdateByFangId(hotelDto);

            //
            OtsInterface.initHotel(hotelJson.getId());
        }

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelDeletePush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.hotelDelete.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        /*
        {
            "hotelid": "2811, 2311,22333"
        }
         */
        try {
            //
            String hotelIds = JSONObject.parseObject(body).get("hotelid").toString();
            String[] ids = hotelIds.split(",");

            for (String strId : ids) {
                Long.parseLong(strId);
            }

        } catch (Exception e) {
            throw new MyException("-99", "-99", "格式错误");
        }

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
        HotelFacilityJson facilityJson = null;
        try {
            //
            facilityJson = JSONUtil.fromJson(body, HotelFacilityJson.class);
        } catch (Exception e) {
            throw new MyException("-99", "-99", "格式错误");
        }

        Long fangHotelId = facilityJson.getHotelid();
        List<FacilityJson> facilityJsonList = facilityJson.getTags();

        if (null != facilityJsonList && !facilityJsonList.isEmpty()) {

            List<HotelFacilityDto> hotelFacilityDtoList = new ArrayList<HotelFacilityDto>();
            for (FacilityJson json : facilityJsonList) {

                HotelFacilityDto dto = new HotelFacilityDto();
                dto.setFangHotelId(fangHotelId);
                dto.setFacilityId(json.getId());
                dto.setFacilityName(json.getTagname());
                dto.setFacilityType(json.getTaggroupid());
                hotelFacilityDtoList.add(dto);
            }

            this.hotelFacilityService.saveOrUpdateByFangId(hotelFacilityDtoList);
        }

        //
        List<RoomTypeFacilityJson> roomTypeFacilityJsonList = facilityJson.getRoomtypeTags();
        if (null != roomTypeFacilityJsonList) {

            for (RoomTypeFacilityJson roomTypeFacilityJson : roomTypeFacilityJsonList) {
                Long fangRoomTypeId = roomTypeFacilityJson.getRoomtypeid();
                List<FacilityJson> roomTypeTag = roomTypeFacilityJson.getTags();

                if (null != roomTypeTag && !roomTypeTag.isEmpty()) {

                    List<RoomTypeFacilityDto> roomTypeFacilityDtoList = new ArrayList<RoomTypeFacilityDto>();
                    for(FacilityJson json: roomTypeTag) {

                        //
                        RoomTypeFacilityDto dto = new RoomTypeFacilityDto();
                        dto.setFangHotelId(fangHotelId);
                        dto.setFangRoomTypeId(fangRoomTypeId);
                        dto.setFacilityId(json.getId());
                        dto.setFacilityName(json.getTagname());
                        dto.setFacilityType(json.getTaggroupid());

                        roomTypeFacilityDtoList.add(dto);
                    }

                    this.roomTypeFacilityService.saveOrUpdateByFangid(roomTypeFacilityDtoList);
                }
            }
        }

        //
        HotelDto dbHotel = this.hotelService.findByFangId(fangHotelId);
        if (null != dbHotel) {
            //
            OtsInterface.initHotel(dbHotel.getId());
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
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/mergeHotelFacility", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeHotelFacility(Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            hotelFacilityService.mergeHotelFacility(pageNo);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findHotelById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findHotelById(Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.findById(hotelId);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "T");
            result.put("hotel", hotelDto);
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }

    }


}

