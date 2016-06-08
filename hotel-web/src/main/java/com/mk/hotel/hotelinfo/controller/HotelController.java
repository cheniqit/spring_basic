package com.mk.hotel.hotelinfo.controller;

import com.dianping.cat.Cat;
import com.mk.execution.pushinfo.JobManager;
import com.mk.framework.Constant;
import com.mk.framework.JsonUtils;
import com.mk.hotel.common.bean.PageBean;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.hotelinfo.service.impl.HotelPicServiceImpl;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private RoomTypeStockService roomTypeStockService;
    @Autowired
    private HotelPicServiceImpl hotelPicService;


    @RequestMapping(value = "/hotelall", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> hotelAllPush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.hotelAll.getId());

            this.logPushService.save(logPushDto);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }


        //
        JobManager.addPushInfoToRefreshJob(body, LogPushTypeEnum.hotelAll);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
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
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        //
        JobManager.addPushInfoToRefreshJob(body, LogPushTypeEnum.hotelDelete);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
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
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        //
        JobManager.addPushInfoToRefreshJob(body, LogPushTypeEnum.hotelFacility);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/mergePmsHotel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergePmsHotel(Long hotelId, Integer pageNo) {
        try {
            if (pageNo == null) {
                pageNo = 1;
            }
            if (hotelId == null) {
                hotelService.mergePmsHotel(pageNo);
            } else {
                hotelService.mergePmsHotelByHotelId(hotelId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/mergeHotelFacility", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeHotelFacility(Integer pageNo) {
        try {
            if (pageNo == null) {
                pageNo = 1;
            }
            hotelFacilityService.mergeHotelFacility(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/findHotelById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findHotelById(Long hotelId) {
        try {
            HotelDto hotelDto = hotelService.findById(hotelId);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "T");
            result.put("hotel", hotelDto);
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/findHotelByPage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findHotelByName(Integer pageNo, Integer pageSize) {
        try {
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = Constant.DEFAULT_REMOTE_PAGE_SIZE;
            }
            //酒店分页
            HotelExample hotelExample = new HotelExample();
            int count = hotelMapper.countByExample(hotelExample);
            PageBean pageBean = new PageBean(pageNo, count, pageSize);
            HotelExample example = new HotelExample();
            example.setStart(pageBean.getStart());
            example.setPageCount(pageBean.getPageCount());
            List<Hotel> hotelList = hotelMapper.selectByExample(example);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "T");
            result.put("data", hotelList);
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/updatePromoRedisStock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updatePromoRedisStock(Long hotelId, Long roomTypeId, Integer promoNum) {

        try {
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            roomTypeStockService.updatePromoRedisStock(hotelId, roomTypeId, promoNum);
            result.put("success", "T");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/savepicture", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> savePicture(Long hotelId, Long roomTypeId, String picType, String url, String updateBy) {

        try {
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            hotelPicService.saveHotelPic(hotelId, roomTypeId, picType, url, updateBy);
            result.put("success", "T");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/delpicture", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> delPicture(Long hotelId, Long roomTypeId, String picType, String url, String updateBy) {

        try {
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            hotelPicService.delPicture(hotelId, roomTypeId, picType, url, updateBy);
            result.put("success", "T");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            result.put("errmsg", e.getMessage());
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/mergeFanqieHotel")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeFanqieHotel() {

        this.hotelService.mergeFanqieHotel();
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

}
