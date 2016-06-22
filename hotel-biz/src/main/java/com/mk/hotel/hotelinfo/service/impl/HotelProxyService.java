package com.mk.hotel.hotelinfo.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.framework.net.PmsAuthHeader;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.remote.amap.AddressInfoRemoteService;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.ots.mapper.LandMarkMapper;
import com.mk.ots.model.LandMark;
import com.mk.ots.model.LandMarkExample;
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
    private LandMarkMapper landMarkMapper;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private AddressInfoRemoteService addressInfoRemoteService;
    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private RoomTypeService roomTypeService;
    private static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);




    @Transactional
    public void saveHotel(List<HotelQueryListResponse.HotelInfo> hotelInfoList){
        if(CollectionUtils.isEmpty(hotelInfoList)){
            return;
        }
        for(HotelQueryListResponse.HotelInfo hotelInfo : hotelInfoList){
            mergeHotel(Long.valueOf(hotelInfo.getId()));
        }
    }

    public HotelDto mergeCrmHotel(Long pmsHotelId) {
        //根据房间列表id查找房间详细信息
        PmsAuthHeader pmsAuthHeader = new PmsAuthHeader();

        //
        HotelQueryDetailRequest hotelQueryDetailRequest = new HotelQueryDetailRequest();
        hotelQueryDetailRequest.setChannelid(pmsAuthHeader.getChannelId());
        hotelQueryDetailRequest.setHotelid(pmsHotelId.toString());

        //
        HotelQueryDetailResponse hotelQueryDetailResponse = hotelRemoteService.queryCrmHotel(hotelQueryDetailRequest);
        if (hotelQueryDetailResponse == null || hotelQueryDetailResponse.getData() == null || hotelQueryDetailResponse.getData().getHotel() == null) {
            logger.info(String.format("hotelQueryDetailResponse info is empty"));
            return null;
        }

        //根据调用结果更新hotel表
        HotelDto hotelDto = hotelService.findByFangId(pmsHotelId, HotelSourceEnum.CRM);

        try {
            //
            Hotel hotel = convertHotel(hotelQueryDetailResponse, HotelSourceEnum.CRM);

            if (null != hotelDto) {
                hotel.setId(hotelDto.getId());
                hotel.setCreateBy(hotelDto.getCreateBy());
                hotel.setCreateDate(hotelDto.getCreateDate());
                hotel.setIsValid("T");
                this.hotelMapper.updateByPrimaryKeyWithBLOBs(hotel);

                //
                HotelDto dto = new HotelDto();
                BeanUtils.copyProperties(hotel, dto);
                return dto;
            } else {
                this.hotelMapper.insert(hotel);

                //增加 客房
                RoomTypeDto roomTypeDto = new RoomTypeDto();
                roomTypeDto.setFangId(1l);
                roomTypeDto.setFangHotelId(hotel.getFangId());
                roomTypeDto.setName("客房");
                roomTypeDto.setIsValid("T");
                roomTypeDto.setCreateBy(Constant.SYSTEM_USER_NAME);
                roomTypeDto.setUpdateDate(new Date());

                roomTypeService.saveOrUpdateByFangId(roomTypeDto, HotelSourceEnum.CRM);
                //
                HotelDto dto = new HotelDto();
                BeanUtils.copyProperties(hotel, dto);
                return dto;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        }
        return null;

    }

    public void mergeHotel(Long pmsHotelId){
        //根据房间列表id查找房间详细信息
        HotelQueryDetailRequest hotelQueryDetailRequest = new HotelQueryDetailRequest();
        PmsAuthHeader pmsAuthHeader = new PmsAuthHeader();
        hotelQueryDetailRequest.setChannelid(pmsAuthHeader.getChannelId());
        hotelQueryDetailRequest.setHotelid(pmsHotelId.toString());
        HotelQueryDetailResponse hotelQueryDetailResponse = hotelRemoteService.queryHotelDetail(hotelQueryDetailRequest);
        if(hotelQueryDetailResponse == null || hotelQueryDetailResponse.getData() == null || hotelQueryDetailResponse.getData().getHotel() == null){
            logger.info(String.format("hotelQueryDetailResponse info is empty"));
            return;
        }
        //根据调用结果更新hotel表
        HotelDto hotelDto = hotelService.findByFangId(Long.parseLong(hotelQueryDetailResponse.getData().getHotel().getId()+""), HotelSourceEnum.LEZHU);
        Hotel hotel = null;
        if(hotelDto == null || hotelDto.getId() == null){
            try {
                hotel = saveHotel(hotelQueryDetailResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                hotel = updateHotel(hotelDto.getId(),hotelQueryDetailResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        OtsInterface.initHotel(hotel.getId());
    }

    public Hotel saveHotel(HotelQueryDetailResponse hotelQueryDetailResponse) throws Exception {
        Hotel hotel = convertHotel(hotelQueryDetailResponse, HotelSourceEnum.LEZHU);
        hotelMapper.insertSelective(hotel);
        hotelService.updateRedisHotel(hotel.getId(), hotel, "HotelProxyService.saveHotel");
        return hotel;
    }

    public Hotel updateHotel(Long hotelId, HotelQueryDetailResponse hotelQueryDetailResponse) throws Exception {
        Hotel hotel = convertHotel(hotelQueryDetailResponse, HotelSourceEnum.LEZHU);
        hotel.setId(hotelId);
        HotelExample example = new HotelExample();
        example.createCriteria().andFangIdEqualTo(Long.parseLong(hotelQueryDetailResponse.getData().getHotel().getId()+""));
        hotelMapper.updateByExampleWithBLOBs(hotel, example);
        hotelService.updateRedisHotel(hotel.getId(), hotel, "HotelProxyService.updateHotel");
        return hotel;
    }

    private Hotel convertHotel(HotelQueryDetailResponse hotelQueryDetailResponse, HotelSourceEnum hotelSourceEnum) throws Exception {
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
        hotel.setRepairTime(hotelInfo.getRepairtime());
        hotel.setRetentionTime(hotelInfo.getRetentiontime());
        hotel.setRegTime(hotelInfo.getRegtime());
        hotel.setOpenTime(hotelInfo.getOpentime());

        hotel.setProvCode(hotelInfo.getProvcode()+"");
        hotel.setCityCode(hotelInfo.getCitycode()+"");
        hotel.setDisCode(hotelInfo.getDiscode()+"");
        String townCode  = addressInfoRemoteService.findTownCodeByLocation(String.valueOf(hotelInfo.getLatitude()), String.valueOf(hotelInfo.getLongitude()));
        hotel.setTownCode(townCode);


        hotel.setFangId(Long.valueOf(hotelInfo.getId()+""));
        if(CollectionUtils.isEmpty(hotelService.getAllLandMarkList())){
            LandMarkExample example = new LandMarkExample();
            List<LandMark> landMarks = landMarkMapper.selectByExample(example);
            hotelService.setAllLandMarkList(landMarks);
        }
        HotelLandMark hotelLandMark = hotelService.getHotelLandMark(hotelInfo.getLongitude(), hotelInfo.getLatitude(), Constant.HOTEL_TO_HOT_AREA_DISTANCE, hotelService.getAllLandMarkList());
        hotel.setBusinessZoneInfo(hotelLandMark.getBusinessZoneInfo().toString());
        hotel.setAirportStationInfo(hotelLandMark.getAirportStationInfo().toString());
        hotel.setScenicSpotsInfo(hotelLandMark.getScenicSpotsInfo().toString());
        hotel.setHospitalInfo(hotelLandMark.getHospitalInfo().toString());
        hotel.setCollegesInfo(hotelLandMark.getCollegesInfo().toString());

        hotel.setUpdateBy(Constant.SYSTEM_USER_NAME);
        hotel.setUpdateDate(new Date());
        hotel.setCreateBy(Constant.SYSTEM_USER_NAME);
        hotel.setCreateDate(new Date());
        hotel.setIsValid(ValidEnum.VALID.getCode());

        hotel.setSourceType(hotelSourceEnum.getId());
        return hotel;
    }
}
