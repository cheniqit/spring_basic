package com.mk.motan.controller;

import com.dianping.cat.Cat;
import com.mk.execution.pushinfo.JobManager;
import com.mk.framework.Constant;
import com.mk.framework.excepiton.MyException;
import com.mk.hotel.common.bean.PageBean;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.hotelinfo.model.HotelFanqieMapping;
import com.mk.hotel.hotelinfo.service.impl.HotelPicServiceImpl;
import com.mk.hotel.hotelinfo.service.impl.HotelServiceImpl;
import com.mk.hotel.log.LogPushService;
import com.mk.hotel.log.dto.LogPushDto;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.order.controller.json.Result;
import com.mk.hotel.remote.dog.TaskFactoryRemoteService;
import com.mk.hotel.remote.dog.common.HotelCommonResponse;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.bean.PushRoomType;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.service.impl.FanqielaileRoomTypeProxyService;
import com.mk.hotel.roomtype.service.impl.RoomTypeServiceImpl;
import com.mk.ots.mapper.LandMarkMapper;
import com.mk.ots.model.LandMark;
import com.mk.ots.model.LandMarkExample;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/motan", produces = MediaType.APPLICATION_JSON_VALUE)
public class MotanController {

    @RequestMapping(value = "/online", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> online() {

        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/offline", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> offline() {

        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, false);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
