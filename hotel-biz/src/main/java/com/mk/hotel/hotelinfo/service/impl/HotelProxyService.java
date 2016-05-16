package com.mk.hotel.hotelinfo.service.impl;

import com.mk.framework.Constant;
import com.mk.framework.DateUtils;
import com.mk.framework.net.PmsAuthHeader;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.log.service.impl.LogPushServiceImpl;
import com.mk.hotel.remote.amap.AddressInfoRemoteService;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/13.
 */
@Service
public class HotelProxyService {

    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private AddressInfoRemoteService addressInfoRemoteService;
    @Autowired
    private HotelService hotelService;

    private static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    @Transactional
    public void saveHotel(List<HotelQueryListResponse.HotelInfo> hotelInfoList){
        if(CollectionUtils.isEmpty(hotelInfoList)){
            return;
        }
        for(HotelQueryListResponse.HotelInfo hotelInfo : hotelInfoList){
            //根据房间列表id查找房间详细信息
            HotelQueryDetailRequest hotelQueryDetailRequest = new HotelQueryDetailRequest();
            PmsAuthHeader pmsAuthHeader = new PmsAuthHeader();
            hotelQueryDetailRequest.setChannelid(pmsAuthHeader.getChannelId());
            hotelQueryDetailRequest.setHotelid(String.valueOf(hotelInfo.getId()));
            HotelQueryDetailResponse hotelQueryDetailResponse = hotelRemoteService.queryHotelDetail(hotelQueryDetailRequest);
            if(hotelQueryDetailResponse == null || hotelQueryDetailResponse.getData() == null || hotelQueryDetailResponse.getData().getHotel() == null){
                logger.info(String.format("page no %s hotel info is empty"));
                return;
            }
            //根据调用结果更新hotel表
            HotelDto hotelDto = hotelService.findByFangId(Long.parseLong(hotelQueryDetailResponse.getData().getHotel().getId()+""));
            if(hotelDto == null || hotelDto.getId() == null){
                try {
                    saveHotel(hotelQueryDetailResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    updateHotel(hotelQueryDetailResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveHotel(HotelQueryDetailResponse hotelQueryDetailResponse) throws Exception {
        Hotel hotel = convertHotel(hotelQueryDetailResponse);
        hotelMapper.insertSelective(hotel);
    }

    public void updateHotel(HotelQueryDetailResponse hotelQueryDetailResponse) throws Exception {
        Hotel hotel = convertHotel(hotelQueryDetailResponse);
        HotelExample example = new HotelExample();
        example.createCriteria().andFangIdEqualTo(Long.parseLong(hotelQueryDetailResponse.getData().getHotel().getId()+""));
        hotelMapper.updateByExampleSelective(hotel, example);
    }

    private Hotel convertHotel(HotelQueryDetailResponse hotelQueryDetailResponse) throws Exception {
        if(hotelQueryDetailResponse == null || hotelQueryDetailResponse.getData() == null || hotelQueryDetailResponse.getData().getHotel() == null){
            throw new Exception("参数错误,酒店信息为空");
        }
        HotelQueryDetailResponse.HotelInfo hotelInfo = hotelQueryDetailResponse.getData().getHotel();
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(hotelInfo, hotel);

        hotel.setName(hotelInfo.getHotelname());
        hotel.setAddr(hotelInfo.getDetailaddr());
        hotel.setPhone(hotelInfo.getHotelphone());
        hotel.setPic(hotelInfo.getHotelpic());
        hotel.setLat(new BigDecimal(hotelInfo.getLatitude()));
        hotel.setLon(new BigDecimal(hotelInfo.getLongitude()));
        hotel.setDefaultLeaveTime(hotelInfo.getDefaultleavetime());
        hotel.setHotelType(hotelInfo.getHoteltype()+"");
        hotel.setRepairTime(DateUtils.parseDate(hotelInfo.getRepairtime(), DateUtils.FORMAT_DATE));
        hotel.setCityCode(hotelInfo.getCitycode()+"");
        hotel.setDisCode(hotelInfo.getDiscode()+"");
        String townCode  = addressInfoRemoteService.findTownCodeByLocation(String.valueOf(hotelInfo.getLatitude()), String.valueOf(hotelInfo.getLongitude()));
        hotel.setTownCode(townCode);

        hotel.setRetentionTime(hotelInfo.getRetentiontime());
        hotel.setFangId(Long.valueOf(hotelInfo.getId()+""));
        hotel.setBusinessZoneInfo("");
        hotel.setProvCode(hotelInfo.getProvcode()+"");

        hotel.setAirportStationInfo("");
        hotel.setScenicSpotsInfo("");
        hotel.setHospitalInfo("");
        hotel.setCollegesInfo("");

        hotel.setUpdateBy(Constant.SYSTEM_USER_NAME);
        hotel.setUpdateDate(new Date());
        hotel.setCreateBy(Constant.SYSTEM_USER_NAME);
        hotel.setCreateDate(new Date());
        hotel.setIsValid(ValidEnum.VALID.getCode());
        return hotel;
    }
}