package com.mk.hotel.hotelinfo.service.impl;

import com.mk.framework.Constant;
import com.mk.framework.coordinate.Coordinate;
import com.mk.framework.coordinate.CoordinateUtils;
import com.mk.framework.net.PmsAuthHeader;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.common.utils.QiniuUtils;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.enums.HotelTypeEnum;
import com.mk.hotel.hotelinfo.mapper.HotelFanqieMappingMapper;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.hotelinfo.model.HotelFanqieMapping;
import com.mk.hotel.hotelinfo.model.HotelFanqieMappingExample;
import com.mk.hotel.remote.amap.AddressInfoRemoteService;
import com.mk.hotel.remote.amap.json.AddressByLocationResponse;
import com.mk.hotel.remote.fanqielaile.hotel.json.ImgList;
import com.mk.hotel.remote.fanqielaile.hotel.json.inn.Inn;
import com.mk.hotel.remote.ots.CityRemoteService;
import com.mk.hotel.remote.ots.json.City;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import com.mk.ots.mapper.LandMarkMapper;
import com.mk.ots.model.LandMark;
import com.mk.ots.model.LandMarkExample;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenqi on 16/5/13.
 */
@Service
public class FanqielaileHotelProxyService {

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private HotelFanqieMappingMapper hotelFanqieMappingMapper;

    @Autowired
    private LandMarkMapper landMarkMapper;
    @Autowired
    private AddressInfoRemoteService addressInfoRemoteService;
    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private CityRemoteService cityRemoteService;

    private static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    public Hotel saveOrUpdateHotel(Integer innId, Inn inn) {

        //update
        this.saveOrUpdateMapping(innId.longValue(), inn.getAccountId());
        //
        Hotel hotel = convertHotel(innId, inn);

        //
        HotelExample example = new HotelExample();
        example.createCriteria().andFangIdEqualTo(innId.longValue()).andSourceTypeEqualTo(HotelSourceEnum.FANQIE.getId());
        List<Hotel> hotelList = this.hotelMapper.selectByExample(example);

        if (hotelList.isEmpty()) {
            this.hotelMapper.insert(hotel);
            //TODO facilitiesMap
        } else {
            //db
            Hotel dbHotel = hotelList.get(0);

            //
            hotel.setId(dbHotel.getId());
            hotel.setCreateBy(dbHotel.getCreateBy());
            hotel.setCreateDate(dbHotel.getCreateDate());
            this.hotelMapper.updateByPrimaryKeySelective(hotel);
            //TODO facilitiesMap
        }

        hotelService.updateRedisHotel(hotel.getId(), hotel, "FanqielaileHotelProxyService.updateHotel");
        return hotel;
    }

    private HotelFanqieMapping saveOrUpdateMapping (Long innId, Long accountId) {

        HotelFanqieMappingExample example = new HotelFanqieMappingExample();
        example.createCriteria().andHotelIdEqualTo(innId);
        List<HotelFanqieMapping> mappingList =  this.hotelFanqieMappingMapper.selectByExample(example);

        //
        HotelFanqieMapping mapping = new HotelFanqieMapping();
        mapping.setHotelId(innId);
        mapping.setAccountId(accountId);
        mapping.setCreateBy(Constant.SYSTEM_USER_NAME);
        mapping.setCreateDate(new Date());
        mapping.setUpdateBy(Constant.SYSTEM_USER_NAME);
        mapping.setUpdateDate(new Date());
        mapping.setIsValid("T");

        //
        if (mappingList.isEmpty()) {
            this.hotelFanqieMappingMapper.insert(mapping);
        } else {

            boolean isExist = false;
            for (HotelFanqieMapping dbMapping : mappingList) {
                Long dbAccountId = dbMapping.getAccountId();

                //已存在,不处理.不存在,更新原记录setIsValid置为"F"
                if (dbAccountId.equals(accountId)) {
                    //已存在,不处理
                    isExist = true;

                    dbMapping.setIsValid("T");
                    dbMapping.setUpdateDate(new Date());
                    dbMapping.setUpdateBy(Constant.SYSTEM_USER_NAME);

                } else {
                    //不存在,更新原记录setIsValid置为"F"
                    dbMapping.setIsValid("F");
                    dbMapping.setUpdateDate(new Date());
                    dbMapping.setUpdateBy(Constant.SYSTEM_USER_NAME);
                }

            }

            //不存在
            if (!isExist) {
                this.hotelFanqieMappingMapper.updateByPrimaryKey(mapping);
            }
        }

        return mapping;
    }

    private Hotel convertHotel(Integer innId, Inn inn) {
        if(null == innId || null == inn){
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
                    disCode = addressComponent.getAdcode();
                    townCode = addressComponent.getTowncode();
                    if (StringUtils.isNotBlank(townCode) && townCode.length() >= 9) {
                        townCode = townCode.substring(0, 9);
                    }
                    //
                    City city = this.cityRemoteService.findByDisCode(disCode);
                    if (null != city) {
                        cityCode = city.getCityCode();
                        provCode = city.getProvCode();
                    }
                }
            }
        }

        //pic
        String pic = this.processPic(inn.getImgList());

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
        hotel.setFangId(innId.longValue());
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

        hotel.setPic(pic);
        hotel.setSourceType(HotelSourceEnum.FANQIE.getId());
        return hotel;
    }

    public String processPic(List<ImgList> imgList){
        String fanqieImgDomain = "http://img.fanqiele.com";

        if (null == imgList) {
            return "[{\"name\":\"def\",\"pic\":[{\"url\":\"\"}]},{\"name\":\"lobby\",\"pic\":[{\"url\":\"\"}]},{\"name\":\"mainHousing\",\"pic\":[{\"url\":\"\"}]}]";
        } else {
            //
            String coverImgUrl = "";
            List<String> notCoverUrlList = new ArrayList<String>();

            //
            for (ImgList img : imgList) {

                //
                String imgUrl = fanqieImgDomain + img.getImgUrl();

                //(imgList) isCover 是否封面 number (1封面,0 null不是封面)
                Integer isCover = img.getIsCover();
                if (Integer.valueOf(1).equals(isCover)) {
                    //
                    try {
                        coverImgUrl = QiniuUtils.upload(imgUrl, com.mk.hotel.common.Constant.QINIU_BUCKET);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String uploadImgUrl = null;
                    //
                    try {
                        uploadImgUrl = QiniuUtils.upload(imgUrl, com.mk.hotel.common.Constant.QINIU_BUCKET);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //
                    if (null != uploadImgUrl) {
                        notCoverUrlList.add(uploadImgUrl);
                    }
                }
            }

            //
            StringBuilder notCoverImgUrl = new StringBuilder();
            for (String imgUrl : notCoverUrlList) {
                //非空,追加前加","
                if (!"".equals(notCoverImgUrl.toString())) {
                    notCoverImgUrl.append(",");
                }

                notCoverImgUrl.append("{\"url\":\"").append(imgUrl).append("\"}");
            }

            //
            StringBuilder returnUrl = new StringBuilder()
                    .append("[{\"name\":\"def\",\"pic\":[{\"url\":\"")
                    .append(coverImgUrl)
                    .append("\"}]},{\"name\":\"lobby\",\"pic\":[{\"url\":\"\"}，{\"url\":\"\"}]},{\"name\":\"mainHousing\",\"pic\":[")
                    .append(notCoverImgUrl.toString())
                    .append("]}]");
            return returnUrl.toString();
        }
    }
}
