package com.mk.hotel.hotelinfo.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.framework.DateUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.common.bean.PageBean;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.redisbean.Facility;
import com.mk.hotel.hotelinfo.HotelFacilityService;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
import com.mk.hotel.hotelinfo.enums.HotelFacilityCacheEnum;
import com.mk.hotel.hotelinfo.mapper.HotelFacilityMapper;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.hotelinfo.model.HotelFacility;
import com.mk.hotel.hotelinfo.model.HotelFacilityExample;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelTagRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelTagResponse;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import com.mk.hotel.roomtype.enums.RoomTypeFacilityCacheEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class HotelFacilityServiceImpl implements HotelFacilityService {
    @Autowired
    private HotelFacilityMapper hotelFacilityMapper;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private HotelMapper hotelMapper;

    private Logger logger = LoggerFactory.getLogger(HotelFacilityServiceImpl.class);

    public void saveOrUpdateByFangId (List<HotelFacilityDto> hotelFacilityDtoList) {
        if (null == hotelFacilityDtoList || hotelFacilityDtoList.isEmpty()) {
            throw new MyException("-99", "-99", "hotelFacilityDtoList 不可为空");
        }

        HotelFacilityDto hotelFacilityDto = hotelFacilityDtoList.get(0);
        //
        HotelDto hotelDto = this.hotelService.findByFangId(hotelFacilityDto.getFangHotelId());
        if (null == hotelDto) {
            return;
        }

        //redis
        this.updateRedisFacility(hotelDto.getId(), hotelFacilityDtoList, "HotelFacilityService.saveOrUpdateByFangId");

        //db
        HotelFacilityExample example = new HotelFacilityExample();
        example.createCriteria().andHotelIdEqualTo(hotelDto.getId());
        this.hotelFacilityMapper.deleteByExample(example);

        for (HotelFacilityDto dto : hotelFacilityDtoList) {
            dto.setHotelId(hotelDto.getId());
            dto.setIsValid("T");

            HotelFacility facility = new HotelFacility();
            BeanUtils.copyProperties(dto, facility);
            facility.setCreateBy("hotel_system");
            facility.setCreateDate(new Date());
            facility.setUpdateBy("hotel_system");
            facility.setUpdateDate(new Date());
            this.hotelFacilityMapper.insert(facility);
        }
    }

    public void updateRedisFacility(Long hotelId, List<HotelFacilityDto> hotelFacilityDtoList, String cacheFrom) {
        if (null == hotelId || null == hotelFacilityDtoList || hotelFacilityDtoList.isEmpty()) {
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(new Date());

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
            String facilityKeyName = HotelFacilityCacheEnum.getFacilityKeyName(String.valueOf(hotelId));

            //redis rem
            Set<String> facilityList =  jedis.smembers(facilityKeyName);
            for (String facilityJson : facilityList) {
                jedis.srem(facilityKeyName,facilityJson);
            }

            //redis add
            for (HotelFacilityDto dto : hotelFacilityDtoList) {
                Facility facility = new Facility();
                facility.setFacId(dto.getId());
                facility.setFacName(dto.getFacilityName());
                facility.setFacType(dto.getFacilityType());
                facility.setCacheTime(strDate);
                facility.setCacheFrom(cacheFrom);
                jedis.sadd(facilityKeyName, JsonUtils.toJson(facility));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }


    public void mergeHotelFacility(){
        //查询pms所有的信息房间列表id
        int pageNo = 1;
        mergeHotelFacility(pageNo);
    }

    public void mergeHotelFacility(int pageNo){
        logger.info("begin mergeHotelFacility pageNo {}", pageNo);
        //酒店分页
        HotelExample hotelExample = new HotelExample();
        int count = hotelMapper.countByExample(hotelExample);
        PageBean pageBean = new PageBean(pageNo, count, Constant.DEFAULT_REMOTE_PAGE_SIZE);
        HotelExample example = new HotelExample();
        example.setStart(pageBean.getStart());
        example.setPageCount(pageBean.getPageCount());
        List<Hotel> hotelList = hotelMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(hotelList)){
            return;
        }
        for(Hotel hotel : hotelList){
            HotelTagRequest request = new HotelTagRequest();
            request.setHotelid(hotel.getFangId().toString());
            HotelTagResponse response = hotelRemoteService.queryHotelTags(request);
            if(response != null && response.getData() != null && !CollectionUtils.isEmpty(response.getData().getRoomtypeTags())){
                Integer fangHotelId = response.getData().getHotelid();
                List<HotelTagResponse.RoomTypeFacilityJson> facilityJsonList = response.getData().getRoomtypeTags();
                if (null != facilityJsonList && !facilityJsonList.isEmpty()) {
                    for(HotelTagResponse.RoomTypeFacilityJson facilityJson : facilityJsonList){
                        List<RoomTypeFacilityDto> roomTypeFacilityDtoList = new ArrayList<RoomTypeFacilityDto>();
                        for (HotelTagResponse.FacilityJson json : facilityJson.getTags()) {
                            RoomTypeFacilityDto dto = new RoomTypeFacilityDto();
                            dto.setFangHotelId(Long.valueOf(fangHotelId));
                            dto.setFangRoomTypeId(facilityJson.getRoomtypeid());
                            dto.setFacilityId(json.getId());
                            dto.setFacilityName(json.getTagname());
                            dto.setFacilityType(json.getTaggroupid());
                            dto.setUpdateBy(Constant.SYSTEM_USER_NAME);
                            dto.setUpdateDate(new Date());
                            dto.setCreateBy(Constant.SYSTEM_USER_NAME);
                            dto.setCreateDate(new Date());
                            dto.setIsValid(ValidEnum.VALID.getCode());
                            roomTypeFacilityDtoList.add(dto);
                        }
                        roomTypeFacilityService.saveOrUpdateByFangid(roomTypeFacilityDtoList);
                    }
                }
            }
            if(response != null && response.getData() != null && !CollectionUtils.isEmpty(response.getData().getTags())){
                Integer fangHotelId = response.getData().getHotelid();
                List<HotelTagResponse.Tags> tagsList = response.getData().getTags();
                if (null != tagsList && !tagsList.isEmpty()) {
                    List<HotelFacilityDto> hotelFacilityDtoList = new ArrayList<HotelFacilityDto>();
                    for(HotelTagResponse.Tags tags : tagsList){
                        HotelFacilityDto dto = new HotelFacilityDto();
                        dto.setHotelId(hotel.getId());
                        dto.setFangHotelId(Long.valueOf(fangHotelId));
                        dto.setFacilityId(Long.valueOf(tags.getId()));
                        dto.setFacilityName(tags.getTagname());
                        dto.setFacilityType(Long.valueOf(tags.getTaggroupid()));
                        dto.setUpdateBy(Constant.SYSTEM_USER_NAME);
                        dto.setUpdateDate(new Date());
                        dto.setCreateBy(Constant.SYSTEM_USER_NAME);
                        dto.setCreateDate(new Date());
                        dto.setIsValid(ValidEnum.VALID.getCode());
                        hotelFacilityDtoList.add(dto);
                    }
                    this.saveOrUpdateByFangId(hotelFacilityDtoList);
                }

            }
        }
        logger.info("end mergeHotelFacility pageNo {}", pageNo);
        pageNo++;
        mergeHotelFacility(pageNo);
    }
}
