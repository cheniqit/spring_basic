package com.mk.hotel.hotelinfo.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.DistanceUtil;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelCacheEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import com.mk.ots.model.LandMark;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private HotelProxyService hotelProxyService;

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


    public void updateRedisHotel(String hotelId, HotelDto hotelDto) {
        if (StringUtils.isBlank(hotelId) || null == hotelDto || null == hotelDto.getId() || null == hotelDto.getCityCode()) {
            return;
        }

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            String hotelKeyName = HotelCacheEnum.getHotelKeyName(hotelId);
            String originalHotelJson = jedis.get(hotelKeyName);

            if (null != originalHotelJson) {
                //TODO 分析 originalHotelJson ,从老城市中删除
                String originalCityKeyName = HotelCacheEnum.getCityHotelSetName(null);
                jedis.srem(originalCityKeyName,originalHotelJson);
            }

            //TODO add
            jedis.set(hotelKeyName, null);
            String cityKeyName = HotelCacheEnum.getCityHotelSetName(hotelDto.getCityCode());
            jedis.sadd(cityKeyName,null);
            jedis.set(hotelKeyName,null);
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
