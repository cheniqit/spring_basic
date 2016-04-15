package com.mk.taskfactory.web.controller;


import com.mk.taskfactory.api.BookClickService;
import com.mk.taskfactory.api.CrawerToOtsService;
import com.mk.taskfactory.api.GdHotelPicToOtsService;
import com.mk.taskfactory.api.dtos.HotelHourClickDto;
import com.mk.taskfactory.api.dtos.crawer.CrawlPodHotelImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/imgmanage", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrawerToOtsController {

    @Autowired
    private CrawerToOtsService crawerToOtsService;
    @Autowired
    private GdHotelPicToOtsService gdHotelPicToOtsService;


    @RequestMapping(value = "/commentimg", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> commentImg(Integer start) {
        Map<String,Object> result=crawerToOtsService.commentImg(start);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/hotelimage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> hotelImage(Integer start) {
        Map<String,Object> result=crawerToOtsService.hotelImage(start);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveCommentImg", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveCommentImg(Long id) {
        Map<String,Object> result = new HashMap<String, Object>();
         crawerToOtsService.saveCommentImg(id);
        result.put("msg","SUCCESS");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/saveHotelImage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveHotelImage(Long id) {
        Map<String,Object> result = new HashMap<String, Object>();
        crawerToOtsService.saveHotelImage(id);
        result.put("msg","SUCCESS");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/podHotelImage", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> podHotelImage(CrawlPodHotelImageDto dto) {
        Map<String,Object> result = new HashMap<String, Object>();
        crawerToOtsService.podHotelImage(dto);
        result.put("msg","SUCCESS");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/hotelPhotos", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> hotelPhotos(Integer start) {
        Map<String,Object> result=gdHotelPicToOtsService.hotelPhotos(start);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/roomPic", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> roomPic(Integer start) {
        Map<String,Object> result=gdHotelPicToOtsService.roomPic(start);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
