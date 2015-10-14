package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.RoomSaleService;
import com.mk.taskfactory.api.dtos.RoomSaleToOtsDto;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/roomsale", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomSaleController {
    @Autowired
    private RoomSaleService roomSaleService;
    @RequestMapping(value = "/querysaleroom", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<RoomSaleToOtsDto>> querySaleRoom(TRoomSaleDto bean) {
        List<RoomSaleToOtsDto>result=roomSaleService.querySaleRoom(bean);
        return new ResponseEntity<List<RoomSaleToOtsDto>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/gethotelsalebyhotelid", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RoomSaleToOtsDto> getHotelSaleByHotelId(Integer hotelId) {
        if (hotelId==null){
            return new ResponseEntity(null, HttpStatus.OK);
        }
        RoomSaleToOtsDto result=roomSaleService.getHotelSaleByHotelId(hotelId);
        return new ResponseEntity<RoomSaleToOtsDto>(result, HttpStatus.OK);
    }
}
