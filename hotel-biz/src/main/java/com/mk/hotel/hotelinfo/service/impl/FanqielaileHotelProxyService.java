package com.mk.hotel.hotelinfo.service.impl;

import com.mk.framework.Constant;
import com.mk.framework.coordinate.Coordinate;
import com.mk.framework.coordinate.CoordinateUtils;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.framework.security.MD5;
import com.mk.hotel.common.enums.FacilityEnum;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.QiniuUtils;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelFacilityDto;
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
import com.mk.hotel.remote.fanqielaile.hotel.json.FacilitiesMap;
import com.mk.hotel.remote.fanqielaile.hotel.json.ImgList;
import com.mk.hotel.remote.fanqielaile.hotel.json.inn.Inn;
import com.mk.hotel.remote.ots.CityRemoteService;
import com.mk.hotel.remote.ots.json.City;
import com.mk.ots.mapper.LandMarkMapper;
import com.mk.ots.model.LandMark;
import com.mk.ots.model.LandMarkExample;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    private HotelFanqieMappingMapper hotelFanqieMappingMapper;

    @Autowired
    private LandMarkMapper landMarkMapper;
    @Autowired
    private AddressInfoRemoteService addressInfoRemoteService;
    @Autowired
    private HotelServiceImpl hotelService;

    @Autowired
    private CityRemoteService cityRemoteService;
    @Autowired
    private HotelFacilityServiceImpl hotelFacilityServiceImpl;

    private static Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    public Hotel saveOrUpdateHotel(Integer innId, Inn inn) {

        //
        HotelExample example = new HotelExample();
        example.createCriteria().andFangIdEqualTo(innId.longValue()).andSourceTypeEqualTo(HotelSourceEnum.FANQIE.getId());
        List<Hotel> hotelList = this.hotelMapper.selectByExampleWithBLOBs(example);

        Hotel hotel = null;
        if (hotelList.isEmpty()) {
            //
            hotel = convertHotel(innId, inn, null);
            this.hotelMapper.insert(hotel);
            saveOrUpdateHotelFacility(innId.longValue(), hotel.getId(), inn.getFacilitiesMap());
        } else {
            //db
            Hotel dbHotel = hotelList.get(0);

            //
            hotel = convertHotel(innId, inn, dbHotel);

            hotel.setId(dbHotel.getId());
            hotel.setCreateBy(dbHotel.getCreateBy());
            hotel.setCreateDate(dbHotel.getCreateDate());
            this.hotelMapper.updateByPrimaryKeyWithBLOBs(hotel);
            saveOrUpdateHotelFacility(innId.longValue(), dbHotel.getId(), inn.getFacilitiesMap());
        }

        //更新到redis
        //redis中使用accountId
        Hotel redisHotel = new Hotel();
        BeanUtils.copyProperties(hotel, redisHotel);
        redisHotel.setFangId(inn.getAccountId());
        hotelService.updateRedisHotel(redisHotel.getId(), redisHotel, "FanqielaileHotelProxyService.updateHotel");
        return hotel;
    }

    public void saveOrUpdateHotelFacility(Long innId, Long hotelId, List<FacilitiesMap> facilitiesMap){
        List<HotelFacilityDto> hotelFacilityDtoList = new ArrayList<HotelFacilityDto>();
        for (FacilitiesMap facility : facilitiesMap) {
            //
            FacilityEnum facilityEnum = FacilityEnum.getByFanqieType(facility.getValue());

            HotelFacilityDto dto = new HotelFacilityDto();
            dto.setHotelId(hotelId);
            dto.setFangHotelId(innId);
            dto.setFacilityId(facilityEnum.getId().longValue());
            dto.setFacilityName(facilityEnum.getTitle());
            dto.setUpdateBy(Constant.SYSTEM_USER_NAME);
            dto.setUpdateDate(new Date());
            dto.setCreateBy(Constant.SYSTEM_USER_NAME);
            dto.setCreateDate(new Date());
            dto.setIsValid(ValidEnum.VALID.getCode());
            hotelFacilityDtoList.add(dto);
        }
        hotelFacilityServiceImpl.saveOrUpdateByFangId(hotelFacilityDtoList, HotelSourceEnum.FANQIE);
    }

    public HotelFanqieMapping saveOrUpdateMapping (Long innId, Integer pattern, Long accountId) {

        HotelFanqieMappingExample example = new HotelFanqieMappingExample();
        example.createCriteria().andInnIdEqualTo(innId);
        List<HotelFanqieMapping> mappingList =  this.hotelFanqieMappingMapper.selectByExample(example);

        //
        HotelFanqieMapping mapping = new HotelFanqieMapping();
        mapping.setInnId(innId);
        mapping.setAccountId(accountId);
        mapping.setPattern(pattern);
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

    private Hotel convertHotel(Integer innId, Inn inn, Hotel dbHotel) {
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
        String picSign = this.calcPicSign(inn.getImgList());
        String pic = null;
        if (null != picSign && null != dbHotel && picSign.equals(dbHotel.getPicsSign())) {
            //签名相同不转换
            pic = dbHotel.getPic();
        } else {
            //其他情况
            pic = this.processPic(inn.getImgList());
        }

        //HotelLandMark
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

        //hotelType
        HotelTypeEnum hotelTypeEnum = HotelTypeEnum.getByName(inn.getInnType());

        //repairTime
        String repairTime = inn.getLastDecorateTime();
        try {
            //只含年份的,补全
            if (StringUtils.isNotBlank(repairTime)) {
                Integer.parseInt(repairTime);
                repairTime = repairTime + "-01-01";
            } else {
                repairTime = null;
            }
        } catch (Exception e) {
            //不处理
        }
        //openTime
        String openTime = inn.getOpenTime();
        try {
            //只含年份的,补全
            if (StringUtils.isNotBlank(openTime)) {
                Integer.parseInt(openTime);
                openTime = openTime + "-01-01";
            } else {
                openTime = null;
            }
        } catch (Exception e) {
            //不处理
        }
        //
        Hotel hotel = new Hotel();
        hotel.setFangId(innId.longValue());
        hotel.setName(inn.getBrandName());
        hotel.setAddr(inn.getAddr());
        hotel.setPhone(inn.getFrontPhone());
        hotel.setLon(new BigDecimal(gaodiCoordinate.getLon()));
        hotel.setLat(new BigDecimal(gaodiCoordinate.getLat()));
        hotel.setDefaultLeaveTime("120000");
        hotel.setHotelType(hotelTypeEnum.getId().toString());
        hotel.setRetentionTime("180000");
        hotel.setRepairTime(repairTime);
        hotel.setIntroduction(inn.getInnInfo());
        hotel.setProvCode(provCode);
        hotel.setCityCode(cityCode);
        hotel.setDisCode(disCode);
        hotel.setTownCode(townCode);
        hotel.setOpenTime(openTime);
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

        hotel.setPicsSign(picSign);
        hotel.setPic(pic);
        hotel.setSourceType(HotelSourceEnum.FANQIE.getId());
        return hotel;
    }

    private String calcPicSign(List<ImgList> imgList) {

        //全刷图片开关,当有值时返回null,全部刷新图片
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
            String key = jedis.get("fanqieFlushAllPic");
            if (StringUtils.isNotBlank(key)) {
                return null;
            }
        } catch (Exception e) {

        }finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        if (null == imgList || imgList.isEmpty()) {
            return null;
        }

        //
        StringBuilder pics = new StringBuilder();

        for (ImgList img : imgList) {
            pics.append(img.toString());
        }
        return MD5.MD5Encode(pics.toString());
    }

    public String processPic(List<ImgList> imgList){
        String fanqieImgDomain = "http://img.fanqiele.com";

        if (null == imgList || imgList.isEmpty()) {
            return null;
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
                if (Integer.valueOf(1).equals(isCover) && "".equals(coverImgUrl)) {
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
                    .append("\"}]},{\"name\":\"lobby\",\"pic\":[]},{\"name\":\"mainHousing\",\"pic\":[")
                    .append(notCoverImgUrl.toString())
                    .append("]}]");
            return returnUrl.toString();
        }
    }
}
