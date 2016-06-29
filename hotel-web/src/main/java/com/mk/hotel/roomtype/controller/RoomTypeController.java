package com.mk.hotel.roomtype.controller;

import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.mk.execution.pushinfo.JobManager;
import com.mk.framework.DateUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.model.RoomTypePrice;
import com.mk.hotel.roomtype.service.impl.RoomTypePriceServiceImpl;
import com.mk.hotel.roomtype.service.impl.RoomTypeServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/roomtype", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController {

    @Autowired
    private RoomTypeStockService roomTypeStockService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private RoomTypePriceServiceImpl roomTypePriceService;

    @Autowired
    private LogPushService logPushService;

    private static final Logger logger = LoggerFactory.getLogger(RoomTypeController.class);

    @RequestMapping(value = "/roomtype", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypePush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.roomType.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        //
        JobManager.addPushInfoToRefreshJob(body, LogPushTypeEnum.roomType);

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypeDeletePush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.roomTypeDelete.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        JobManager.addPushInfoToRefreshJob(body, LogPushTypeEnum.roomTypeDelete);

        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/online", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypeOnlinePush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.roomTypeDelete.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
        /*
        { 
            "hotelid":9999,
            "roomtypeid":"444,5555,333"
        }

         */
        try {
            //
            JSONObject bodyJson = JSONObject.parseObject(body);
            String strHotelId = bodyJson.get("hotelid").toString();
            String roomTypeId = bodyJson.get("roomtypeid").toString();

            Long hotelId = null;
            try {
                hotelId = Long.parseLong(strHotelId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException("-99", "-99", "hotelid 格式错误");
            }
            HotelDto hotelDto = this.hotelService.findByFangId(hotelId, HotelSourceEnum.LEZHU);
            if (null == hotelDto) {
                throw new MyException("-99", "-99", "hotel未找到");
            }

            //
            String[] ids = roomTypeId.split(",");

            List<Long> idList = new ArrayList<Long>();
            for (String strId : ids) {
                Long id = Long.parseLong(strId);
                idList.add(id);
            }

            //
            this.roomTypeService.updateOnlineByHotelId(hotelDto.getId(), idList);

            //
            OtsInterface.initHotel(hotelDto.getId());

        } catch (Exception e) {
            throw new MyException("-99", "-99", "格式错误");
        }
        HashMap<String,Object> result= new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/roomtypeprice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> roomTypePricePush(@RequestHeader HttpHeaders headers, @RequestBody String body) {

        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(body);
            logPushDto.setType(LogPushTypeEnum.roomTypePrice.getId());

            this.logPushService.save(logPushDto);
        }catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }

        JobManager.addPushInfoToRefreshJob(body, LogPushTypeEnum.roomTypePrice);

        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/mergeRoomType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomType(Long hotelId, Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            if(hotelId != null){
                roomTypeService.mergeRoomTypeByHotelId(hotelId);
            }else{
                roomTypeService.mergeRoomType(pageNo);
            }

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

    @RequestMapping(value = "/mergeRoomTypePrice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomTypePrice(Long hotelId, Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            if(hotelId != null){
                roomTypeService.mergeRoomTypePriceByHotelId(hotelId);
            }else{
                roomTypeService.mergeRoomTypePrice(pageNo);
            }

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

    @RequestMapping(value = "/mergeRoomTypePriceOnlyOne", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomTypePriceOnlyOne(Long hotelId) {
        try {
            if(hotelId == null){
                throw new MyException("-99", "-99", "hotelId必填");
            }
            roomTypeService.mergeRoomTypeByHotelId(hotelId);
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


    @RequestMapping(value = "/mergeRoomTypeStock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomTypeStock(Integer pageNo, Long hotelId) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            if(hotelId == null){
                roomTypeService.mergeRoomTypeStock(pageNo);
            }else{
                roomTypeService.mergeRoomTypeStockByHotel(hotelId);
            }
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

    @RequestMapping(value = "/mergeRoomTypeDayStock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomTypeDayStock(Integer pageNo, Long hotelId) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            if(hotelId == null){
                roomTypeService.mergeRoomTypeDayStock(pageNo);
            }else{
                roomTypeService.mergeRoomTypeDayStockByHotel(hotelId);
            }
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



    @RequestMapping(value = "/findRoomTypeByName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findRoomTypeByName(Long hotelId, String name) {
        try {
            if(StringUtils.isBlank(name) || hotelId == null){
                HashMap<String,Object> result = new LinkedHashMap<String, Object>();
                result.put("success", "F");
                result.put("errmsg", "参数为空");
                return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
            }
            RoomTypeDto roomTypeDto = roomTypeService.selectByName(hotelId, name);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "T");
            result.put("roomType", roomTypeDto);
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


    @RequestMapping(value = "/findRoomTypeById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findRoomTypeByName(Long roomTypeId) {
        try {
            if(roomTypeId == null){
                HashMap<String,Object> result = new LinkedHashMap<String, Object>();
                result.put("success", "F");
                result.put("errmsg", "参数为空");
                return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
            }
            RoomTypeDto roomTypeDto = roomTypeService.selectById(roomTypeId);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "T");
            result.put("roomType", roomTypeDto);
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

    @RequestMapping(value = "/findRoomTypeByHotelId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findRoomTypeByHotelId(Long hotelId) {
        try {
            if(hotelId == null){
                HashMap<String,Object> result = new LinkedHashMap<String, Object>();
                result.put("success", "F");
                result.put("errmsg", "参数为空");
                return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
            }
            RoomTypeDto roomTypeDto = roomTypeService.selectByHotelId(hotelId);
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "T");
            result.put("roomType", roomTypeDto);
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

    @RequestMapping(value = "/clearStockAndPrice")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> clearStockAndPrice() {

        this.roomTypeService.clearStockAndPrice();
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/fullStock")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> fullStock(Long hotelId, Long roomTypeId, String fromDate, String toDate) {

        if (null == hotelId || null == roomTypeId || StringUtils.isBlank(fromDate) || StringUtils.isBlank(toDate)) {
            throw new MyException("参数错误");
        }

        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date from = null;
        Date to = null;
        try {
            from = format.parse(fromDate);
            to = format.parse(toDate);
        } catch (Exception e) {
            throw new MyException("日期参数应为 yyyy-MM-dd ");
        }

        //若开始时间晚于结束时间
        if (from.after(to)) {
            throw new MyException("开始时间晚于结束时间 ");
        }

        //
        this.roomTypeStockService.fullStock(hotelId, roomTypeId, from, to);

        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);

    }

    @RequestMapping(value = "/getRoomTypePrice")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> getRoomTypePrice(Long roomTypeId, String fromDate, String toDate) {
        List<RoomTypePrice>  roomTypePriceList = roomTypePriceService.getRoomTypePrice(roomTypeId,
                DateUtils.getDateFromString(fromDate, DateUtils.FORMAT_DATE),
                DateUtils.getDateFromString(toDate, DateUtils.FORMAT_DATE));
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("roomTypePriceList", roomTypePriceList);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);

    }
}

