package com.mk.hotel.hotelinfo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dianping.cat.Cat;
import com.mk.framework.DistanceUtil;
import com.mk.framework.JsonUtils;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.common.redisbean.PicList;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelCacheEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.remote.amap.AddressInfoRemoteService;
import com.mk.hotel.remote.amap.json.AddressByLocationResponse;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import com.mk.ots.mapper.LandMarkMapper;
import com.mk.ots.model.LandMark;
import com.mk.ots.model.LandMarkExample;
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

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private LandMarkMapper landMarkMapper;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private HotelProxyService hotelProxyService;
    @Autowired
    private AddressInfoRemoteService addressInfoRemoteService;

    private Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    private static List<LandMark> allLandMarkList = new ArrayList<LandMark>();

    public static List<LandMark> getAllLandMarkList() {
        return allLandMarkList;
    }

    public static void setAllLandMarkList(List<LandMark> allLandMarkList) {
        HotelServiceImpl.allLandMarkList = allLandMarkList;
    }

    @Override
    public HotelDto findById(Long id) {

        Hotel hotel = hotelMapper.selectByPrimaryKey(id);

        HotelDto dto = new HotelDto();
        BeanUtils.copyProperties(hotel, dto);
        return dto;
    }

    public HotelDto findByFangId(Long fangId) {

        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andFangIdEqualTo(fangId);
        List<Hotel> hotelList = hotelMapper.selectByExample(hotelExample);

        if (hotelList.isEmpty()) {
            return null;
        }

        HotelDto dto = new HotelDto();
        BeanUtils.copyProperties(hotelList.get(0), dto);
        return dto;
    }


    public void mergePmsHotel(){
        //查询pms所有的信息房间列表id
        int pageNo = 1;
        mergePmsHotel(pageNo);
    }

    public void mergePmsHotel(int pageNo){
        logger.info("begin mergePmsHotel pageNo {}", pageNo);
        HotelQueryListRequest request = new HotelQueryListRequest(pageNo);
        HotelQueryListResponse response = hotelRemoteService.queryHotelList(request);
        if(response.getData() == null || CollectionUtils.isEmpty(response.getData().getHotels())){
            return;
        }
        List<HotelQueryListResponse.HotelInfo> hotelInfoList = response.getData().getHotels();
        hotelProxyService.saveHotel(hotelInfoList);
        logger.info("end mergePmsHotel pageNo {}", pageNo);
        pageNo++;
        mergePmsHotel(pageNo);
    }

    public void updateRedisHotel(Long hotelId, HotelDto hotelDto, String cacheFrom) {
        if (null == hotelId || null == hotelDto || null == hotelDto.getId() || null == hotelDto.getCityCode()) {
            return;
        }

        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = formatTime.format(new Date());

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            String hotelKeyName = HotelCacheEnum.getHotelKeyName(String.valueOf(hotelId));
            String originalHotelJson = jedis.get(hotelKeyName);

            //srem from redis
            if (null != originalHotelJson) {
                //hotelInRedis
                com.mk.hotel.hotelinfo.redisbean.Hotel hotelInRedis =
                        JsonUtils.fromJson(originalHotelJson, com.mk.hotel.hotelinfo.redisbean.Hotel.class);
                //originalCityCode
                Integer originalCityCode = hotelInRedis.getCityCode();
                //originalCityKeyName
                String originalCityKeyName = HotelCacheEnum.getCityHotelSetName(String.valueOf(originalCityCode));

                jedis.srem(originalCityKeyName, originalHotelJson);
            }

            //roomtype pic
            String strPics = hotelDto.getPic();
            JSONArray picArray = JSONArray.parseArray(strPics);

            List<PicList> picLists = new ArrayList<PicList>();

            for (String strPic : picArray.toArray(new String[0])) {
                PicList picList = JsonUtils.fromJson(strPic, PicList.class);
                picLists.add(picList);
            }

            //hotel type
            Integer intHotelType = null;
            try {
                intHotelType = Integer.parseInt(hotelDto.getHotelType());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            //town code
            AddressByLocationResponse townlocation = addressInfoRemoteService.findAddressByLocation(
                    String.valueOf(hotelDto.getLat()), String.valueOf(hotelDto.getLon()));
            String townCode = null;
            String townName = null;

            if (null != townlocation) {
                AddressByLocationResponse.Regeocode regeocode = townlocation.getRegeocode();
                if (null != regeocode) {
                    AddressByLocationResponse.AddressComponent addressComponent = regeocode.getAddresscomponent();
                    if (null != addressComponent) {
                        townCode = addressComponent.getTowncode();
                        townName = addressComponent.getTownship();
                    }
                }
            }

            //getProvCode
            Integer intProvCode = null;
            try {
                intProvCode = Integer.parseInt(hotelDto.getProvCode());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            //getCityCode
            Integer intCityCode = null;
            try {
                hotelDto.getCityCode();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            //getDisCode
            Integer intDisCode = null;
            try {
                hotelDto.getDisCode();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            //townCode
            Integer intTownCode = null;
            try {
                intTownCode = Integer.parseInt(townCode);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Cat.logError(e);
            }

            //online
            String online = null;
            String isValid = hotelDto.getIsValid();
            if ("T".equals(isValid)) {
                online = "T";
            } else {
                online = "F";
            }

            //
            if(CollectionUtils.isEmpty(this.getAllLandMarkList())){
                LandMarkExample example = new LandMarkExample();

                List<LandMark> landMarks = landMarkMapper.selectByExample(example);
                this.setAllLandMarkList(landMarks);
            }
            HotelLandMark hotelLandMark = this.getHotelLandMark(
                    hotelDto.getLon().doubleValue(), hotelDto.getLat().doubleValue(), 10000, this.getAllLandMarkList());

            //
            com.mk.hotel.hotelinfo.redisbean.Hotel hotel = new com.mk.hotel.hotelinfo.redisbean.Hotel();

            hotel.setId(hotelId);
            hotel.setSourceId(String.valueOf(hotelDto.getFangId()));
            hotel.setHotelName(hotelDto.getName());
            hotel.setDetailAddr(hotelDto.getAddr());
            hotel.setLongitude(hotelDto.getLon());
            hotel.setLatitude(hotelDto.getLat());
            hotel.setOpenTime(hotelDto.getOpenTime());
            hotel.setRepairTime(hotelDto.getRepairTime());
            hotel.setOnline(online);
            hotel.setRetentionTime(hotelDto.getRetentionTime());
            hotel.setDefaultLeaveTime(hotelDto.getDefaultLeaveTime());
            hotel.setHotelPhone(hotelDto.getPhone());
            hotel.setProvCode(intProvCode);
            hotel.setCityCode(intCityCode);
            hotel.setDisCode(intDisCode);
            hotel.setTownCode(intTownCode);
            hotel.setTownName(townName);
            hotel.setHotelType(intHotelType);
            hotel.setIntroduction(hotelDto.getIntroduction());
            hotel.setHotelPics(picLists);
            hotel.setBusinessZoneInfo(hotelLandMark.getBusinessZoneInfo().toString());
            hotel.setAirportStationInfo(hotelLandMark.getAirportStationInfo().toString());
            hotel.setScenicSpotsInfo(hotelLandMark.getScenicSpotsInfo().toString());
            hotel.setHospitalInfo(hotelLandMark.getHospitalInfo().toString());
            hotel.setCollegesInfo(hotelLandMark.getCollegesInfo().toString());

            //
            hotel.setCacheTime(strDate);
            hotel.setCacheFrom(cacheFrom);
            //
            jedis.set(hotelKeyName, JsonUtils.toJson(hotel));

            //
            String cityKeyName = HotelCacheEnum.getCityHotelSetName(String.valueOf(hotelDto.getCityCode()));
            jedis.sadd(cityKeyName, JsonUtils.toJson(hotel));

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     *
     * @param lat 目标纬度
     * @param lon 目标经度
     * @param rang 距离
     * @param landMarks 数据
     * @return 返回小于等于rang范围的landMark
     */
    public HotelLandMark getHotelLandMark(Double lon, Double lat, Integer rang, List<LandMark> landMarks){
        HotelLandMark hotelLandMark = new HotelLandMark();
        if(CollectionUtils.isEmpty(landMarks)){
            return hotelLandMark;
        }
        if(lon == null || lat == null){
            return hotelLandMark;
        }
        for(LandMark landMark : landMarks){
            if(landMark.getLng() == null || landMark.getLat() == null){
                continue;
            }
            float distance = DistanceUtil.calculateLineDistance(lon, lat, landMark.getLng().doubleValue(), landMark.getLat().doubleValue());
            if(distance > rang){
                continue;
            }
            //0附近；1商圈；2机场车站；3地铁路线；4行政区；5景点；6医院；7高校；8酒店；9地址；-1不限
            if(landMark.getLtype() == 1){
                hotelLandMark.getBusinessZoneInfoList().add(HotelLandMark.instanceAreaInfo(landMark.getLandmarkname(), distance));
            }else if(landMark.getLtype() == 2){
                hotelLandMark.getAirportStationInfoList().add(HotelLandMark.instanceAreaInfo(landMark.getLandmarkname(), distance));
            }else if(landMark.getLtype() == 5){
                hotelLandMark.getScenicSpotsInfoList().add(HotelLandMark.instanceAreaInfo(landMark.getLandmarkname(), distance));
            }else if(landMark.getLtype() == 6){
                hotelLandMark.getHospitalInfoList().add(HotelLandMark.instanceAreaInfo(landMark.getLandmarkname(), distance));
            }else if(landMark.getLtype() == 7){
                hotelLandMark.getCollegesInfoList().add(HotelLandMark.instanceAreaInfo(landMark.getLandmarkname(), distance));
            }
        }
        if(hotelLandMark.getBusinessZoneInfo().length() > 0 ){
            hotelLandMark.getBusinessZoneInfo().delete(0,1);
        }
        if(hotelLandMark.getAirportStationInfo().length() > 0 ) {
            hotelLandMark.getAirportStationInfo().delete(0, 1);
        }
        if(hotelLandMark.getScenicSpotsInfo().length() > 0 ) {
            hotelLandMark.getScenicSpotsInfo().delete(0, 1);
        }
        if(hotelLandMark.getHospitalInfo().length() > 0 ) {
            hotelLandMark.getHospitalInfo().delete(0, 1);
        }
        if(hotelLandMark.getCollegesInfo().length() > 0 ) {
            hotelLandMark.getCollegesInfo().delete(0, 1);
        }
        return hotelLandMark;
    }


}
