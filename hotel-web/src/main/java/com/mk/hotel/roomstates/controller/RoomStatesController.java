package com.mk.hotel.roomstates.controller;

import com.mk.hotel.roomstates.IRoomStatesService1;
import com.mk.hotel.roomstates.dto.RoomStatesDto1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/roomstates", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomStatesController {

    @Autowired
    private IRoomStatesService1 roomstatesService;

    @RequestMapping(value = "/querystates", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> queryStates(Long roomTypeId, String startDate, String endDate) {


        //TODO
        List<RoomStatesDto1> dataList = this.roomstatesService.queryStates(roomTypeId, null, null);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("data", dataList);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatepriceandstock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updatePriceAndStock(Long roomTypeId, String startDate, String endDate,
                                                                       BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice,
                                                                       BigDecimal totalStock) {

        //TODO
        this.roomstatesService.updatepriceandstock(roomTypeId, null, null, marketPrice, salePrice, settlePrice, totalStock);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateprice", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updatePrice(Long roomTypeId, String startDate, String endDate,
                                                               BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice) {

        //TODO
        this.roomstatesService.updatePrice(roomTypeId, null, null, marketPrice, salePrice, settlePrice);

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatestock", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateStock(Long roomTypeId, String startDate, String endDate,
                                                               BigDecimal totalStock) {

        //TODO
        this.roomstatesService.updateStock(roomTypeId, null, null, totalStock);
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }
}

