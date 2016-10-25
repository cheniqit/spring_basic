package com.mk.hotel.roomstates.controller;

import com.mk.framework.excepiton.MyException;
import com.mk.hotel.hotelinfo.dto.UpdatePriceAndStock;
import com.mk.hotel.roomstates.IRoomStatesService;
import com.mk.hotel.roomstates.dto.RoomStatesDto;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalDto;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalDto;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/roomstates", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomStatesController {

    private static Logger logger = LoggerFactory.getLogger(RoomStatesController.class);

    @Autowired
    private IRoomStatesService roomStatesService;

    @RequestMapping(value = "/querystates", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> queryStates(Long roomTypeId, String startDate, String endDate, String token) {

        Date start = this.parseDate(startDate);
        Date end = null;
        if (StringUtils.isNotBlank(endDate)) {
            end = this.parseDate(endDate);
        }

        List<RoomStatesDto> dataList = this.roomStatesService.queryStates(roomTypeId, start, end, token);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("data", dataList);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatenormalprice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateNormalPrice(RoomTypePriceNormalDto dto, String operatorId, String token) {

        this.roomStatesService.updateNormalPrice(dto, operatorId, token);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateprice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updatePrice(Long roomTypeId, String startDate, String endDate,
                                                               BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operatorId, String token) {

        Date start = this.parseDate(startDate);
        Date end = this.parseDate(endDate);
        this.roomStatesService.updatePrice(roomTypeId, start, end, marketPrice, salePrice, settlePrice, operatorId, token);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatenormalstock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateNormalStock(RoomTypeStockNormalDto dto, String operatorId, String token) {

        //TODO service


        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatestock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateStock(Long roomTypeId, String startDate, String endDate,
                                                               Long totalStock, String operatorId, String token) {

        Date start = this.parseDate(startDate);
        Date end = this.parseDate(endDate);
        this.roomStatesService.updateStock(roomTypeId, start, end, totalStock, operatorId, token);
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/updatepriceandstock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updatePriceAndStock(UpdatePriceAndStock date) {
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        if(date == null || date.getRoomTypeId() == null || CollectionUtils.isEmpty(date.getDateList())){
            result.put("errmsg", "参数错误");
            result.put("success", "F");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        this.roomStatesService.updatePriceAndStock(date);

        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    private Date parseDate(String strDate) {
        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //
        Date result = null;
        try {
            result = format.parse(strDate);
        } catch (Exception e) {
            throw new MyException("日期格式错误, 应为 yyyy-MM-dd");
        }

        return result;
    }
}

