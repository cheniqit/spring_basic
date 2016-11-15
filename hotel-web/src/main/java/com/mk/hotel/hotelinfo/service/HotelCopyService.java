package com.mk.hotel.hotelinfo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.mk.execution.pushinfo.JobManager;
import com.mk.framework.AppUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.JSONUtil;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.json.facility.FacilityJson;
import com.mk.hotel.hotelinfo.json.facility.HotelFacilityJson;
import com.mk.hotel.hotelinfo.json.facility.RoomTypeFacilityJson;
import com.mk.hotel.hotelinfo.json.hotelall.HotelAllJson;
import com.mk.hotel.hotelinfo.json.hotelall.RoomTypeJson;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangjie on 16/6/6.
 */
public class HotelCopyService {

    private static final Logger logger = LoggerFactory.getLogger(HotelCopyService.class);

    public void handleHotelAll (String body) {

        List<HotelAllJson> hotelJsonList = new ArrayList<HotelAllJson>();
        try {
            //
            JSONArray hotelAllArray = JSON.parseArray(body);
            int arraySize = hotelAllArray.size();

            for (int i = 0; i < arraySize; i++) {
                String strHotelALLJson = hotelAllArray.get(i).toString();

                //
                HotelAllJson hotelJson = JSONUtil.fromJson(strHotelALLJson, HotelAllJson.class);
                hotelJsonList.add(hotelJson);
            }
        } catch (Exception e) {
            throw new MyException("格式错误");
        }

        for (HotelAllJson hotelJson : hotelJsonList) {
            //
            HotelDto hotelDto = new HotelDto();
            hotelDto.setFangId(hotelJson.getId());
            hotelDto.setName(hotelJson.getHotelname());
            hotelDto.setAddr(hotelJson.getDetailaddr());
            hotelDto.setPhone(hotelJson.getHotelphone());
            hotelDto.setLat(hotelJson.getLatitude());
            hotelDto.setLon(hotelJson.getLongitude());
            hotelDto.setDefaultLeaveTime(hotelJson.getDefaultleavetime());
            hotelDto.setHotelType(String.valueOf(hotelJson.getHoteltype()));
            hotelDto.setRetentionTime(hotelJson.getRetentiontime());
            hotelDto.setRepairTime(hotelJson.getRepairtime());
            hotelDto.setRegTime(hotelJson.getRegtime());
            hotelDto.setIntroduction(hotelJson.getIntroduction());
            hotelDto.setProvCode(String.valueOf(hotelJson.getProvcode()));
            hotelDto.setCityCode(String.valueOf(hotelJson.getCitycode()));
            hotelDto.setDisCode(String.valueOf(hotelJson.getDiscode()));
            hotelDto.setIsValid("T");
            hotelDto.setOpenTime(hotelJson.getOpentime());
            hotelDto.setPic(hotelJson.getHotelpic());
            hotelDto.setPics(hotelJson.getHotelpics());

            //
            List<RoomTypeJson> roomTypeJsonList = hotelJson.getRoomtypes();
            if (null != roomTypeJsonList) {

                List<RoomTypeDto> roomTypeDtoList = new ArrayList<RoomTypeDto>();
                for (RoomTypeJson roomTypeJson : roomTypeJsonList) {
                    //
                    Integer intArea = null;

                    try {
                        if (StringUtils.isNotBlank(roomTypeJson.getArea())) {
                            intArea = new BigDecimal(roomTypeJson.getArea()).intValue();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Cat.logError(e);
//                        throw new MyException("-99", "-99", "area格式错误");
                    }

                    //
                    Integer intPrePay = null;
                    try {
                        if (StringUtils.isNotBlank(roomTypeJson.getPrepay())) {
                            intPrePay = Integer.parseInt(roomTypeJson.getPrepay());
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                        Cat.logError(e);
//                        throw new MyException("-99", "-99", "prepay格式错误");
                    }

                    //
                    Integer intBreakfast = null;
                    try {
                        if (StringUtils.isNotBlank(roomTypeJson.getBreakfast())) {
                            intBreakfast = Integer.parseInt(roomTypeJson.getBreakfast());
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                        Cat.logError(e);
//                        throw new MyException("-99", "-99", "breakfast格式错误");
                    }

                    //
                    String isValid = null;
                    Integer status = roomTypeJson.getStatus();

                    //默认为T,(不考虑push值)
//                    if (null == status) {
//                        isValid = "T";
//                    } else {
//                        if (1 == roomTypeJson.getStatus()) {
//                            isValid = "F";
//                        } else {
//                            isValid = "T";
//                        }
//                    }
                    isValid = "T";


                    //
                    RoomTypeDto roomTypeDto = new RoomTypeDto();
                    roomTypeDto.setFangHotelId(hotelJson.getId());
                    roomTypeDto.setFangId(roomTypeJson.getId());
                    roomTypeDto.setName(roomTypeJson.getName());
                    roomTypeDto.setArea(intArea);
                    roomTypeDto.setBedType(roomTypeJson.getBedtype());
                    roomTypeDto.setBedSize(roomTypeJson.getBedsize());
                    roomTypeDto.setRoomNum(roomTypeJson.getRoomnum());
                    roomTypeDto.setPrepay(intPrePay);
                    roomTypeDto.setBreakfast(intBreakfast);
                    roomTypeDto.setStatus(roomTypeJson.getStatus());
                    roomTypeDto.setRefund(roomTypeJson.getRefund());
                    roomTypeDto.setMaxRoomNum(roomTypeJson.getMaxroomnum());
                    roomTypeDto.setRoomTypePics(roomTypeJson.getRoomtypepics());

                    roomTypeDto.setIsValid(isValid);

                    roomTypeDtoList.add(roomTypeDto);
                }

                hotelDto.setRoomTypeDtoList(roomTypeDtoList);
            }


            JobManager.addPushInfoToRefreshJob(JsonUtils.toJson(hotelDto), LogPushTypeEnum.hotel);

//            //
//            HotelService hotelService = AppUtils.getBean(HotelService.class);
//
//            hotelService.saveOrUpdateByFangId(hotelDto);
//
//            //
//            OtsInterface.initHotel(hotelDto.getId());
        }
    }

    public void handleHotelDetail (String body) {


        HotelDto hotelDto = JsonUtils.fromJson(body, HotelDto.class);
        logger.info("handleHotelDetail: hotelId:{} start", hotelDto.getId());

        HotelService hotelService = AppUtils.getBean(HotelService.class);
        hotelService.saveOrUpdateByFangId(hotelDto, HotelSourceEnum.LEZHU);

        //
        OtsInterface.initHotel(hotelDto.getId());
        logger.info("handleHotelDetail: hotelId:{} init", hotelDto.getId());
    }


    public void handleHotelDel (String body) {
          /*
        {
            "hotelid": "2811, 2311,22333"
        }
         */
        try {
            //
            String hotelIds = JSONObject.parseObject(body).get("hotelid").toString();
            String[] ids = hotelIds.split(",");

            List<Long> idList = new ArrayList<Long>();
            for (String strId : ids) {
                Long id = Long.parseLong(strId);
                idList.add(id);
            }

            //
            HotelService hotelService = AppUtils.getBean(HotelService.class);
            //
            for (Long id : idList) {
                hotelService.deleteByFangId(id, HotelSourceEnum.LEZHU);

                //
                OtsInterface.initHotel(id);
            }
        } catch (Exception e) {
            throw new MyException("格式错误");
        }

    }

    public void handleHotelFacility (String body) {

        //
        HotelService hotelService = AppUtils.getBean(HotelService.class);
        //
        HotelFacilityService hotelFacilityService = AppUtils.getBean(HotelFacilityService.class);
        //
        RoomTypeFacilityService roomTypeFacilityService = AppUtils.getBean(RoomTypeFacilityService.class);

        //
        HotelFacilityJson facilityJson = null;
        try {
            //
            facilityJson = JSONUtil.fromJson(body, HotelFacilityJson.class);
        } catch (Exception e) {
            throw new MyException("格式错误");
        }

        Long fangHotelId = facilityJson.getHotelid();
        List<FacilityJson> facilityJsonList = facilityJson.getTags();

        if (null != facilityJsonList && !facilityJsonList.isEmpty()) {

            List<HotelFacilityDto> hotelFacilityDtoList = new ArrayList<HotelFacilityDto>();
            for (FacilityJson json : facilityJsonList) {

                HotelFacilityDto dto = new HotelFacilityDto();
                dto.setFangHotelId(fangHotelId);
                dto.setFacilityId(json.getId());
                dto.setFacilityName(json.getTagname());
                dto.setFacilityType(json.getTaggroupid());
                hotelFacilityDtoList.add(dto);
            }

            hotelFacilityService.saveOrUpdateByFangId(hotelFacilityDtoList, HotelSourceEnum.LEZHU);
        }

        //
        List<RoomTypeFacilityJson> roomTypeFacilityJsonList = facilityJson.getRoomtypeTags();
        if (null != roomTypeFacilityJsonList) {

            for (RoomTypeFacilityJson roomTypeFacilityJson : roomTypeFacilityJsonList) {
                Long fangRoomTypeId = roomTypeFacilityJson.getRoomtypeid();
                List<FacilityJson> roomTypeTag = roomTypeFacilityJson.getTags();

                if (null != roomTypeTag && !roomTypeTag.isEmpty()) {

                    List<RoomTypeFacilityDto> roomTypeFacilityDtoList = new ArrayList<RoomTypeFacilityDto>();
                    for(FacilityJson json: roomTypeTag) {

                        //
                        RoomTypeFacilityDto dto = new RoomTypeFacilityDto();
                        dto.setFangHotelId(fangHotelId);
                        dto.setFangRoomTypeId(fangRoomTypeId);
                        dto.setFacilityId(json.getId());
                        dto.setFacilityName(json.getTagname());
                        dto.setFacilityType(json.getTaggroupid());

                        roomTypeFacilityDtoList.add(dto);
                    }

                    roomTypeFacilityService.saveOrUpdateByFangid(roomTypeFacilityDtoList, HotelSourceEnum.LEZHU);
                }
            }
        }

        //
        HotelDto dbHotel = hotelService.findByFangId(fangHotelId, HotelSourceEnum.LEZHU);
        if (null != dbHotel) {
            //
            OtsInterface.initHotel(dbHotel.getId());
        }
    }


    public void handleHotelFanqie (String proxyInnJson) {
        //
        HotelService hotelService = AppUtils.getBean(HotelService.class);
        hotelService.mergeFangqieHotelByProxyInnJson(proxyInnJson);
    }


    public void handleRoomStatusFanqie (String mappingJson) {
        //
        HotelService hotelService = AppUtils.getBean(HotelService.class);
        hotelService.mergeFangqieRoomStatusByHotelFanqieMappingJson(mappingJson);
    }
}
