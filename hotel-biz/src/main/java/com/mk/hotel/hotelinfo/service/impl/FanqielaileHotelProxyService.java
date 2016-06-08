package com.mk.hotel.hotelinfo.service.impl;

import com.mk.framework.Constant;
import com.mk.framework.coordinate.Coordinate;
import com.mk.framework.coordinate.CoordinateUtils;
import com.mk.framework.net.PmsAuthHeader;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelTypeEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.remote.amap.AddressInfoRemoteService;
import com.mk.hotel.remote.amap.json.AddressByLocationResponse;
import com.mk.hotel.remote.fanqielaile.hotel.json.inn.Inn;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
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
public class FanqielaileHotelProxyService {

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

    private static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    public Hotel updateHotel(Long hotelId, Inn inn) throws Exception {
        Hotel hotel = convertHotel(inn);
        hotel.setId(hotelId);

        HotelExample example = new HotelExample();
        example.createCriteria().andFangIdEqualTo(inn.getAccountId());

        hotelMapper.updateByExampleSelective(hotel, example);
        hotelService.updateRedisHotel(hotel.getId(), hotel, "FanqielaileHotelProxyService.updateHotel");
        return hotel;
    }

    private Hotel convertHotel(Inn inn) throws Exception {
        if(null == inn){
            return null;
        }
        // 转换 坐标
        Coordinate baiduCoordinate = new Coordinate();
        baiduCoordinate.setLon(inn.getBaiduLon());
        baiduCoordinate.setLat(inn.getBaiduLat());
        Coordinate gaodiCoordinate = CoordinateUtils.decrypt(baiduCoordinate);

        //查询行政
        String provCode = null;
        String cityCode = null;
        String disCode = null;
        String townCode = null;
        AddressByLocationResponse location  = addressInfoRemoteService.findAddressByLocation(
                String.valueOf(gaodiCoordinate.getLat()),
                String.valueOf(gaodiCoordinate.getLon()));

        if (null != location) {
            AddressByLocationResponse.Regeocode regeocode = location.getRegeocode();
            if (null != regeocode) {
                AddressByLocationResponse.AddressComponent addressComponent = regeocode.getAddresscomponent();
                if (null != addressComponent) {
                    provCode = addressComponent.getProvince();
                    cityCode = addressComponent.getCitycode();
                    disCode = addressComponent.getAdcode();
                    townCode = addressComponent.getTowncode();
                }
            }
        }


        //
        if(CollectionUtils.isEmpty(hotelService.getAllLandMarkList())){
            LandMarkExample example = new LandMarkExample();
            List<LandMark> landMarks = landMarkMapper.selectByExample(example);
            hotelService.setAllLandMarkList(landMarks);
        }
        HotelLandMark hotelLandMark = hotelService.getHotelLandMark(
                gaodiCoordinate.getLon(),
                gaodiCoordinate.getLat(),
                Constant.HOTEL_TO_HOT_AREA_DISTANCE,
                hotelService.getAllLandMarkList());

        Hotel hotel = new Hotel();
        hotel.setFangId(inn.getAccountId());
        hotel.setName(inn.getBrandName());
        hotel.setAddr(inn.getAddr());
        hotel.setPhone(inn.getFrontPhone());
        hotel.setLon(new BigDecimal(gaodiCoordinate.getLon()));
        hotel.setLat(new BigDecimal(gaodiCoordinate.getLat()));
        hotel.setDefaultLeaveTime("120000");
        //TODO
        hotel.setHotelType(String.valueOf(HotelTypeEnum.INNER.getId()));
        hotel.setRetentionTime("180000");
        hotel.setRepairTime(inn.getLastDecorateTime() + "-01-01");
        hotel.setIntroduction(inn.getInnInfo());
        hotel.setProvCode(provCode);
        hotel.setCityCode(cityCode);
        hotel.setDisCode(disCode);
        hotel.setTownCode(townCode);
        hotel.setOpenTime(inn.getOpenTime() + "-01-01");
        hotel.setRegTime(null);

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
        return hotel;
    }
}
