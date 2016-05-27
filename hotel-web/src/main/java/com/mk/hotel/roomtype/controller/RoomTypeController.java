package com.mk.hotel.roomtype.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.JSONUtil;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.json.roomtype.RoomTypeJson;
import com.mk.hotel.roomtype.json.roomtypeprice.PriceInfoJson;
import com.mk.hotel.roomtype.json.roomtypeprice.RoomTypePriceJson;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
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
    private RoomTypePriceService roomTypePriceService;

    @Autowired
    private LogPushService logPushService;

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

        Long fangHotelId = null;
        //
        List<RoomTypeJson> roomTypeJsonList = new ArrayList<RoomTypeJson>();
        try {
            //
            JSONArray bodyJson = JSON.parseArray(body);
            int bodySize = bodyJson.size();

            for (int i = 0; i < bodySize; i++) {
                String strRoomTypeJson = bodyJson.get(i).toString();
                //
                RoomTypeJson roomTypeJson = JSONUtil.fromJson(strRoomTypeJson, RoomTypeJson.class);
                roomTypeJsonList.add(roomTypeJson);

                //
                fangHotelId = roomTypeJson.getHotelid();
            }
        } catch (Exception e) {
            throw new MyException("-99", "-99", "格式错误");
        }

        //
        for (RoomTypeJson roomTypeJson : roomTypeJsonList) {

            RoomTypeDto roomTypeDto = new RoomTypeDto();

            roomTypeDto.setFangHotelId(roomTypeJson.getHotelid());
            roomTypeDto.setFangId(roomTypeJson.getId());
            roomTypeDto.setName(roomTypeJson.getName());

            Integer intArea = null;
            try {
                intArea = new BigDecimal(roomTypeJson.getArea()).intValue();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Cat.logError(e);
                throw new MyException("-99", "-99", "area格式错误");
            }

            roomTypeDto.setArea(intArea);

            String strBedType = roomTypeJson.getBedtype();
            if (StringUtils.isNotBlank(strBedType)) {
                try {
                    Integer bedType = Integer.valueOf(strBedType);
                    roomTypeDto.setBedType(bedType);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Cat.logError(e);
                    throw new MyException("-99", "-99", "BedType格式错误");
                }
            }

            roomTypeDto.setRoomNum(roomTypeJson.getRoomnum());
            roomTypeDto.setPrepay(roomTypeJson.getPrepay());
            roomTypeDto.setBreakfast(roomTypeJson.getBreakfast());
            roomTypeDto.setStatus(roomTypeJson.getStatus());
            roomTypeDto.setRefund(roomTypeJson.getRefund());
            roomTypeDto.setMaxRoomNum(roomTypeJson.getMaxroomnum());
            roomTypeDto.setRoomTypePics(roomTypeJson.getRoomtypepics());

            this.roomTypeService.saveOrUpdateByFangId(roomTypeDto);
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
            HotelDto hotelDto = this.hotelService.findByFangId(hotelId);
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
            this.roomTypeService.deleteByHotelId(hotelDto.getId(), idList);

            //
            OtsInterface.initHotel(hotelDto.getId());

        } catch (Exception e) {
            throw new MyException("-99", "-99", "格式错误");
        }
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
            HotelDto hotelDto = this.hotelService.findByFangId(hotelId);
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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //json
        RoomTypePriceJson roomTypePriceJson = null;
        try {
            //json
            roomTypePriceJson = JSONUtil.fromJson(body, RoomTypePriceJson.class);
        } catch (Exception e) {
            throw new MyException("-99", "-99", "格式错误");
        }

        //fang-hotelId
        Long fangHotelId = roomTypePriceJson.getHotelid();

        //roomTypeJsonList
        List<RoomTypePriceDto> roomTypePriceDtoList = new ArrayList<RoomTypePriceDto>();
        List<com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson> roomTypeJsonList = roomTypePriceJson.getRoomtypeprices();
        for (com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson roomTypeJson : roomTypeJsonList) {
            //fang-roomTypeId
            Long fangRoomTypeId = roomTypeJson.getRoomtypeid();

            List<PriceInfoJson> priceInfoJsonList = roomTypeJson.getPriceinfos();
            for (PriceInfoJson priceInfoJson : priceInfoJsonList) {

                //day
                Date day = null;
                String strDay = priceInfoJson.getDate();
                if (StringUtils.isBlank(strDay)) {
                    continue;
                } else {
                    try {
                        day = format.parse(strDay);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }

                //cost
                BigDecimal cost = null;
                String strCost = priceInfoJson.getCost();
                if (StringUtils.isBlank(strCost)) {
                    continue;
                } else {
                    try {
                        cost = new BigDecimal(strCost);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }

                //price
                BigDecimal price = null;
                String strPrice = priceInfoJson.getPrice();
                if (StringUtils.isBlank(strPrice)) {
                    continue;
                } else {
                    try {
                        price = new BigDecimal(strPrice);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }

                //dto
                RoomTypePriceDto roomTypePriceDto = new RoomTypePriceDto();
                roomTypePriceDto.setDay(day);
                roomTypePriceDto.setPrice(price);
                roomTypePriceDto.setCost(cost);
                roomTypePriceDto.setFangHotelId(fangHotelId);
                roomTypePriceDto.setFangRoomTypeId(fangRoomTypeId);
                roomTypePriceDtoList.add(roomTypePriceDto);
            }
        }

        this.roomTypePriceService.saveOrUpdateByFangId(roomTypePriceDtoList);

        //
        HotelDto dbHotel = this.hotelService.findByFangId(fangHotelId);
        if (null != dbHotel) {

            //
            OtsInterface.initHotel(dbHotel.getId());
        }
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/mergeRoomType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeRoomType(Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            roomTypeService.mergeRoomType(pageNo);
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
    public ResponseEntity<HashMap<String, Object>> mergeRoomTypePrice(Integer pageNo) {
        try {
            if(pageNo == null){
                pageNo = 1;
            }
            roomTypeService.mergeRoomTypePrice(pageNo);
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
}

