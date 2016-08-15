package com.mk.hotel.hotelinfo.controller;

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

/**
 * Created by chenqi on 16/5/9.
 */
@Controller
@RequestMapping(value = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

    @Autowired
    private HotelServiceImpl hotelService;

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
    @Autowired
    private FanqielaileRoomTypeProxyService fanqielaileRoomTypeProxyService;
    @Autowired
    private TaskFactoryRemoteService taskFactoryRemoteService;
    @Autowired
    private LandMarkMapper landMarkMapper;
    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    private Logger logger = LoggerFactory.getLogger(HotelController.class);

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

    @RequestMapping(value = "/updateFanqieRoomTypeInfo", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    public @ResponseBody
    String updateFanqieRoomTypeInfo(HttpServletRequest request){
        Result result = new Result();
        BufferedReader br = null;
        XStream xstream = null;
        try{
            br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
            String lineStr = null;
            StringBuffer sb = new StringBuffer();
            while((lineStr = br.readLine()) != null){
                sb.append(lineStr);
            }
            xstream = new XStream(new DomDriver());
            xstream.alias("PushRoomType", PushRoomType.class);
            xstream.autodetectAnnotations(true);
            try {
                //log
                LogPushDto logPushDto = new LogPushDto();
                logPushDto.setMsg(sb.toString());
                logPushDto.setType(LogPushTypeEnum.fanqieRoomTypeInfo.getId());

                this.logPushService.save(logPushDto);
            } catch (Exception e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            PushRoomType pushRoomType = (PushRoomType) xstream.fromXML(sb.toString(), new PushRoomType());
            fanqielaileRoomTypeProxyService.updateFanqieRoomTypeInfo(pushRoomType);
            result.setMessage("成功");
            result.setStatus("200");
        }catch (Exception e){
            e.printStackTrace();
            IOUtils.closeQuietly(br);
            result.setMessage("失败");
            result.setStatus("400");
        }

        return xstream.toXML(result);
    }

    @RequestMapping(value = "/updateCrmHotel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateCrmHotel(Long fangId) {
        if (null == fangId) {
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "F");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HotelDto dto = this.hotelService.updateCrmHotel(fangId);

        //
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        if (null == dto) {
            result.put("success", "F");
        } else {
            result.put("success", "T");
            result.put("id", dto.getId());
            result.put("name", dto.getName());
            result.put("pic", dto.getPic());
        }
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

    @RequestMapping(value = "/findHotelByFangId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findHotelByFangId(Long fangId, Integer hotelSource) {
        try {
            HotelSourceEnum hotelSourceEnum = HotelSourceEnum.getById(hotelSource);
            if(hotelSourceEnum == null){
                HashMap<String, Object> result = new LinkedHashMap<String, Object>();
                result.put("success", "F");
                result.put("errmsg", "hotelSource参数错误");
                return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
            }
            HotelDto hotelDto = hotelService.findByFangId(fangId, hotelSourceEnum);
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

    @RequestMapping(value = "/findHotelMappingByHotelId", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> findHotelMappingByHotelId(Long hotelId) {
        try {
            HotelFanqieMapping hotelFanqieMapping = hotelService.findHotelMappingByHotelId(hotelId);
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("success", "T");
            result.put("hotelFanqieMapping", hotelFanqieMapping);
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
    public ResponseEntity<HashMap<String, Object>> savePicture(String hotelId, String hotelPicInfo, String updateBy) {

        try {
            HashMap<String,Object> result = new LinkedHashMap<String, Object>();
            hotelPicService.saveHotelPic(hotelId, hotelPicInfo, updateBy);
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
    public ResponseEntity<HashMap<String, Object>> mergeFanqieHotel(Long innId, Long accountId) {

        //
        List<String> proxyInnJsonList = this.hotelService.mergeFanqieHotel(innId, accountId);

        for (String proxyInnJson : proxyInnJsonList) {
            JobManager.addPushInfoToRefreshJob(proxyInnJson, LogPushTypeEnum.hotelFanqie);
        }

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }





    @RequestMapping(value = "/mergeFangqieRoomStatus")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> mergeFangqieRoomStatus() {

        //
        List<String> proxyInnJsonList = this.hotelService.mergeFangqieRoomStatus();

        for (String proxyInnJson : proxyInnJsonList) {
            JobManager.addPushInfoToRefreshJob(proxyInnJson, LogPushTypeEnum.roomTypeStatusFanqie);
        }

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateHotelIndex")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateHotelIndex(String hotelIdList) {
        if(StringUtils.isBlank(hotelIdList)){
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("errMsg", "参数错误");
            result.put("success", "F");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        String[] idList = hotelIdList.split(",");

        for (String hotelId : idList) {
            OtsInterface.initHotel(Long.valueOf(hotelId));
        }
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/updateHotelIndexAll")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateHotelIndexAll(Integer hotelSource) {
        if(hotelSource == null){
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("errMsg", "参数错误");
            result.put("success", "F");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        HotelExample example = new HotelExample();
        example.createCriteria().andSourceTypeEqualTo(hotelSource);

        int pageNo = 1;
        while (true){
            List<Hotel> hotelList = hotelService.findHotelByPage(example, pageNo, 1000);
            if(CollectionUtils.isEmpty(hotelList)){
                break;
            }
            for (Hotel hotel : hotelList) {
                OtsInterface.initHotel(hotel.getId());
            }
            logger.info("updateHotelIndexAll params pagerNo {} end", pageNo);
            pageNo++;
        }

        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateHotelPrice")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> updateHotelPrice(Long hotelId, Long roomTypeId, BigDecimal price) {
        if(hotelId == null || roomTypeId == null || price == null){
            HashMap<String, Object> result = new LinkedHashMap<String, Object>();
            result.put("errMsg", "参数错误");
            result.put("success", "F");
            return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
        }
        try {
            //log
            LogPushDto logPushDto = new LogPushDto();
            logPushDto.setMsg(hotelId+"|"+roomTypeId+"|"+price);
            logPushDto.setType(LogPushTypeEnum.updateRoomTypePrice.getId());

            this.logPushService.save(logPushDto);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
        taskFactoryRemoteService.updateHotelPrice(hotelId.toString(), roomTypeId.toString(), price.toString());
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/getLandMark", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HotelLandMark> updateHotelPrice(Double lon, Double lat) {
        if(lon == null || lat == null){
            throw new MyException("参数错误");
        }
        if(org.springframework.util.CollectionUtils.isEmpty(hotelService.getAllLandMarkList())){
            LandMarkExample example = new LandMarkExample();
            List<LandMark> landMarks = landMarkMapper.selectByExample(example);
            hotelService.setAllLandMarkList(landMarks);
        }
        HotelLandMark hotelLandMark = hotelService.getHotelLandMark(
                lon,
                lat,
                Constant.HOTEL_TO_HOT_AREA_DISTANCE,
                hotelService.getAllLandMarkList());
        return new ResponseEntity<HotelLandMark>(hotelLandMark, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateHotelToRedis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HotelCommonResponse> updateHotelPrice(Long hotelId, Long roomTypeId) {
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);

        if (!HotelSourceEnum.SHUIME2.getId().equals(hotel.getSourceType())) {
            throw new MyException("酒店非睡么自建类型!");
        }

        hotelService.updateRedisHotel(hotelId, hotel, "HotelController.updateHotelPrice");

        //
        if(roomTypeId == null){
            List<RoomType>  roomTypeList = roomTypeService.selectRoomTypeByHotelId(hotelId);
            for(RoomType roomType : roomTypeList){
                roomTypeService.updateRoomTypeToRedis(hotelId, roomType.getId());
            }
        }else{
            roomTypeService.updateRoomTypeToRedis(hotelId, roomTypeId);
        }

        //
        OtsInterface.initHotel(hotelId);

        //
        HotelCommonResponse commonResponse = new HotelCommonResponse();
        commonResponse.setSuccess(ValidEnum.VALID.getCode());
        return new ResponseEntity<HotelCommonResponse>(commonResponse, HttpStatus.OK);
    }
}
