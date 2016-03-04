package com.mk.taskfactory.web.controller.ods;

import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.dtos.HotelHourClickDto;
import com.mk.taskfactory.api.ods.RoomTypePricePorterService;
import com.mk.taskfactory.biz.mapper.ods.RoomTypeOnlinePriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by fisher.wu on 16/3/4.
 */
@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypePriceController {

    @Autowired
    private RoomTypePricePorterService roomTypePricePorterService;

    @RequestMapping(value = "/roomtype/price", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> roomTypePrice() {
        Map<String,Object> result = roomTypePricePorterService.doExecute();
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}
