package com.mk.hotel.hotelinfo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.framework.DistanceUtil;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.common.redisbean.PicList;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelCacheEnum;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelFanqieMappingMapper;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.hotelinfo.model.HotelFanqieMapping;
import com.mk.hotel.hotelinfo.model.HotelFanqieMappingExample;
import com.mk.hotel.remote.amap.AddressInfoRemoteService;
import com.mk.hotel.remote.amap.json.AddressByLocationResponse;
import com.mk.hotel.remote.fanqielaile.hotel.FanqielaileRemoteService;
import com.mk.hotel.remote.fanqielaile.hotel.json.inn.Inn;
import com.mk.hotel.remote.fanqielaile.hotel.json.inn.InnList;
import com.mk.hotel.remote.fanqielaile.hotel.json.proxysalelist.PricePatterns;
import com.mk.hotel.remote.fanqielaile.hotel.json.proxysalelist.ProxyInns;
import com.mk.hotel.remote.fanqielaile.hotel.json.proxysalelist.SaleList;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus.RoomDetailList;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus.RoomList;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomType;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomTypeList;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.service.impl.FanqielaileRoomTypeProxyService;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private HotelFanqieMappingMapper hotelFanqieMappingMapper;
    @Autowired
    private LandMarkMapper landMarkMapper;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private HotelProxyService hotelProxyService;
    @Autowired
    private AddressInfoRemoteService addressInfoRemoteService;
    @Autowired
    private HotelPicServiceImpl hotelPicService;

    @Autowired
    private FanqielaileHotelProxyService fanqielaileHotelProxyService;
    @Autowired
    private FanqielaileRemoteService fanqielaileRemoteService;
    @Autowired
    private FanqielaileRoomTypeProxyService fanqielaileRoomTypeProxyService;

    private Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class);

    private static List<LandMark> allLandMarkList = new ArrayList<LandMark>();

    public static List<LandMark> getAllLandMarkList() {
        return allLandMarkList;
    }

    public static void setAllLandMarkList(List<LandMark> allLandMarkList) {
        HotelServiceImpl.allLandMarkList = allLandMarkList;
    }

    public void deleteByFangId(Long id, HotelSourceEnum hotelSourceEnum) {

        if (null == id || null == hotelSourceEnum) {
            throw new MyException("-99", "-99", "id,hotelSourceEnum必填");
        }

        //
        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andFangIdEqualTo(id).andSourceTypeEqualTo(hotelSourceEnum.getId());

        List<Hotel> hotelList = hotelMapper.selectByExampleWithBLOBs(hotelExample);
        if (!hotelList.isEmpty()) {
            Hotel hotel = hotelList.get(0);
            hotel.setIsValid("F");
            hotel.setUpdateBy("hotel_system");
            hotel.setUpdateDate(new Date());

            this.hotelMapper.updateByPrimaryKeyWithBLOBs(hotel);
            this.updateRedisHotel(hotel.getId(), hotel, "HotelService.deleteByFangId");
        }

    }

    public void saveOrUpdateByFangId(HotelDto hotelDto, HotelSourceEnum hotelSourceEnum) {

        if (null == hotelDto || null == hotelDto.getFangId() || null == hotelSourceEnum) {
            throw new MyException("-99", "-99", "hotelDto fangId hotelSourceEnum必填");
        }

        //
        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andFangIdEqualTo(hotelDto.getFangId()).andSourceTypeEqualTo(hotelSourceEnum.getId());
        List<Hotel> hotelList = hotelMapper.selectByExampleWithBLOBs(hotelExample);

        //
        Long hotelId = null;

        //save or update hotel
        if (hotelList.isEmpty()) {
            Hotel dbHotel = new Hotel();
            BeanUtils.copyProperties(hotelDto, dbHotel);

            dbHotel.setPic(hotelDto.getPics());

            dbHotel.setCreateDate(new Date());
            dbHotel.setCreateBy("hotel_system");
            dbHotel.setUpdateDate(new Date());
            dbHotel.setUpdateBy("hotel_system");
            dbHotel.setSourceType(hotelSourceEnum.getId());
            //
            this.hotelMapper.insert(dbHotel);
            hotelId = dbHotel.getId();
            hotelDto.setId(hotelId);
            this.updateRedisHotel(hotelId, dbHotel, "HotelService.saveOrUpdateByFangId(HotelDto)");
        } else {
            //hotelLandMark
            if(CollectionUtils.isEmpty(this.getAllLandMarkList())){
                LandMarkExample example = new LandMarkExample();

                List<LandMark> landMarks = landMarkMapper.selectByExample(example);
                this.setAllLandMarkList(landMarks);
            }
            HotelLandMark hotelLandMark = this.getHotelLandMark(
                    hotelDto.getLon().doubleValue(), hotelDto.getLat().doubleValue(), Constant.HOTEL_TO_HOT_AREA_DISTANCE, this.getAllLandMarkList());
            //town code
            String townCode = addressInfoRemoteService.findTownCodeByLocation(
                    String.valueOf(hotelDto.getLat()), String.valueOf(hotelDto.getLon()));

            //
            Hotel hotel = hotelList.get(0);
            hotel.setFangId(hotelDto.getFangId());
            hotel.setName(hotelDto.getName());
            hotel.setAddr(hotelDto.getAddr());
            hotel.setPhone(hotelDto.getPhone());
            hotel.setLat(hotelDto.getLat());
            hotel.setLon(hotelDto.getLon());
            hotel.setDefaultLeaveTime(hotelDto.getDefaultLeaveTime());
            hotel.setHotelType(hotelDto.getHotelType());
            hotel.setRetentionTime(hotelDto.getRetentionTime());
            hotel.setRepairTime(hotelDto.getRepairTime());
            hotel.setIntroduction(hotelDto.getIntroduction());
            hotel.setProvCode(hotelDto.getProvCode());
            hotel.setCityCode(hotelDto.getCityCode());
            hotel.setDisCode(hotelDto.getDisCode());
            hotel.setIsValid(hotelDto.getIsValid());
            hotel.setTownCode(townCode);
            hotel.setBusinessZoneInfo(hotelLandMark.getBusinessZoneInfo().toString());
            hotel.setAirportStationInfo(hotelLandMark.getAirportStationInfo().toString());
            hotel.setScenicSpotsInfo(hotelLandMark.getScenicSpotsInfo().toString());
            hotel.setHospitalInfo(hotelLandMark.getHospitalInfo().toString());
            hotel.setCollegesInfo(hotelLandMark.getCollegesInfo().toString());
            hotel.setOpenTime(hotelDto.getOpenTime());
            hotel.setRegTime(hotelDto.getRegTime());
            hotel.setPic(hotelDto.getPics());

            hotel.setUpdateDate(new Date());
            hotel.setUpdateBy("hotel_system");
            hotel.setSourceType(hotelSourceEnum.getId());

            hotelId = hotel.getId();
            this.hotelMapper.updateByPrimaryKeyWithBLOBs(hotel);
            hotelDto.setId(hotelId);
            this.updateRedisHotel(hotelId, hotel, "HotelService.saveOrUpdateByFangId(HotelDto)");

        }

        //roomTypeList
        if (null != hotelDto.getRoomTypeDtoList() && !hotelDto.getRoomTypeDtoList().isEmpty()) {
            this.roomTypeService.saveOrUpdateByHotelId(hotelId, hotelDto.getRoomTypeDtoList(), hotelSourceEnum);
        }
    }

    @Override
    public List<HotelDto> findHotelByName(String hotelName, String cityCode) {
        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andNameEqualTo(hotelName.trim()).andCityCodeEqualTo(cityCode.trim());
        List<Hotel> hotelList = hotelMapper.selectByExampleWithBLOBs(hotelExample);
        if(CollectionUtils.isEmpty(hotelList)){
            return null;
        }
        List<HotelDto> hotelDtoList = new ArrayList<HotelDto>();
        for(Hotel hotel : hotelList){
            HotelDto dto = new HotelDto();
            BeanUtils.copyProperties(hotel, dto);
            hotelDtoList.add(dto);
        }

        return hotelDtoList;
    }

    @Override
    public HotelDto findById(Long id) {

        Hotel hotel = hotelMapper.selectByPrimaryKey(id);

        HotelDto dto = new HotelDto();
        BeanUtils.copyProperties(hotel, dto);
        return dto;
    }

    @Override
    public HotelDto findByName(String name) {
        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andNameEqualTo(name);
        List<Hotel> hotelList = hotelMapper.selectByExampleWithBLOBs(hotelExample);
        if(CollectionUtils.isEmpty(hotelList)){
            return null;
        }
        HotelDto dto = new HotelDto();
        BeanUtils.copyProperties(hotelList.get(0), dto);
        return dto;
    }

    public HotelDto findByFangId(Long fangId, HotelSourceEnum hotelSourceEnum) {

        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andFangIdEqualTo(fangId).andSourceTypeEqualTo(hotelSourceEnum.getId());
        List<Hotel> hotelList = hotelMapper.selectByExampleWithBLOBs(hotelExample);

        if (hotelList.isEmpty()) {
            return null;
        }

        HotelDto dto = new HotelDto();
        BeanUtils.copyProperties(hotelList.get(0), dto);
        return dto;
    }


    public List<Hotel> findHotelByPage(HotelExample example, int pageNo, int size){
        example.setStart((pageNo-1)*size);
        example.setPageCount(size);
        List<Hotel> hotelList = hotelMapper.selectByExample(example);
        return hotelList;
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
        if(response == null || response.getData() == null || CollectionUtils.isEmpty(response.getData().getHotels())){
            return;
        }
        List<HotelQueryListResponse.HotelInfo> hotelInfoList = response.getData().getHotels();
        hotelProxyService.saveHotel(hotelInfoList);
        logger.info("end mergePmsHotel pageNo {}", pageNo);
        pageNo++;
        mergePmsHotel(pageNo);
    }

    public HotelDto mergeCrmHotel(Long fangId){
        return this.hotelProxyService.mergeCrmHotel(fangId);
    }

    public void mergePmsHotelByHotelId(Long hotelId){
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        hotelProxyService.mergeHotel(hotel.getFangId());
    }

    public void updateRedisHotel(Long hotelId, Hotel hotel, String cacheFrom) {
        if (null == hotelId || null == hotel) {
            return;
        }

        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                if (null != originalCityCode) {
                    //originalCityKeyName
                    String originalCityKeyName = HotelCacheEnum.getCityHotelSetName(String.valueOf(originalCityCode));

                    jedis.srem(originalCityKeyName, originalHotelJson);
                }
            }

            //roomtype pic
            String strPics = hotel.getPic();
            List<PicList> picLists = new ArrayList<PicList>();
            if (StringUtils.isNotBlank(strPics) ) {
                JSONArray picArray = JSONArray.parseArray(strPics);
                for (int i = 0; i < picArray.size(); i++) {
                    String strPic = picArray.getString(i);
                    PicList picList = JsonUtils.fromJson(strPic, PicList.class);
                    picList = hotelPicService.replacePicList(hotelId, null, picList);
                    picLists.add(picList);
                }
            }

            //hotel type
            Integer intHotelType = null;
            try {
                intHotelType = Integer.parseInt(hotel.getHotelType());
            } catch (Exception e) {
                e.printStackTrace();
                Cat.logError(e);
            }

            //town code
            AddressByLocationResponse townLocation = addressInfoRemoteService.findAddressByLocation(
                    String.valueOf(hotel.getLat()), String.valueOf(hotel.getLon()));
            String townCode = null;
            String townName = null;

            if (null != townLocation) {
                AddressByLocationResponse.Regeocode regeocode = townLocation.getRegeocode();
                if (null != regeocode) {
                    AddressByLocationResponse.AddressComponent addressComponent = regeocode.getAddresscomponent();
                    if (null != addressComponent) {
                        townCode = addressComponent.getTowncode();
                        if(StringUtils.isNotBlank(townCode) && townCode.length() >=9){
                            townCode = townCode.substring(0,9);
                        }
                        townName = addressComponent.getTownship();
                    }
                }
            }

            //getProvCode
            Integer intProvCode = null;
            try {
                intProvCode = Integer.parseInt(hotel.getProvCode());
            } catch (Exception e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            //getCityCode
            Integer intCityCode = null;
            try {
                intCityCode = Integer.valueOf(hotel.getCityCode());
            } catch (Exception e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            //getDisCode
            Integer intDisCode = null;
            try {
                intDisCode = Integer.valueOf(hotel.getDisCode());
            } catch (Exception e) {
                e.printStackTrace();
                Cat.logError(e);
            }
            //townCode
            Integer intTownCode = null;
            if(StringUtils.isNotBlank(townCode)){
                try {
                    intTownCode = Integer.parseInt(townCode);
                } catch (Exception e) {
                    e.printStackTrace();
                    Cat.logError(e);
                }
            }
            //online
            String online = null;
            String isValid = hotel.getIsValid();
            if ("T".equals(isValid)) {
                online = "T";
            } else {
                online = "F";
            }

            //hotelLandMark
            if(CollectionUtils.isEmpty(this.getAllLandMarkList())){
                LandMarkExample example = new LandMarkExample();

                List<LandMark> landMarks = landMarkMapper.selectByExample(example);
                this.setAllLandMarkList(landMarks);
            }
            HotelLandMark hotelLandMark = this.getHotelLandMark(
                    hotel.getLon().doubleValue(), hotel.getLat().doubleValue(), Constant.HOTEL_TO_HOT_AREA_DISTANCE, this.getAllLandMarkList());

            //
            com.mk.hotel.hotelinfo.redisbean.Hotel hotelInRedis = new com.mk.hotel.hotelinfo.redisbean.Hotel();

            hotelInRedis.setId(hotelId);
            hotelInRedis.setSourceId(String.valueOf(hotel.getFangId()));
            hotelInRedis.setHotelName(hotel.getName());
            hotelInRedis.setDetailAddr(hotel.getAddr());
            hotelInRedis.setLongitude(hotel.getLon());
            hotelInRedis.setLatitude(hotel.getLat());
            hotelInRedis.setOpenTime(hotel.getOpenTime());
            hotelInRedis.setRepairTime(hotel.getRepairTime());
            hotelInRedis.setRegTime(hotel.getRegTime());
            hotelInRedis.setOnline(online);
            hotelInRedis.setRetentionTime(hotel.getRetentionTime());
            hotelInRedis.setDefaultLeaveTime(hotel.getDefaultLeaveTime());
            hotelInRedis.setHotelPhone(hotel.getPhone());
            hotelInRedis.setProvCode(intProvCode);
            hotelInRedis.setCityCode(intCityCode);
            hotelInRedis.setDisCode(intDisCode);
            hotelInRedis.setTownCode(intTownCode);
            hotelInRedis.setTownName(townName);
            hotelInRedis.setHotelType(intHotelType);
            hotelInRedis.setIntroduction(hotel.getIntroduction());
            hotelInRedis.setHotelPics(picLists);
            hotelInRedis.setBusinessZoneInfo(hotelLandMark.getBusinessZoneInfo().toString());
            hotelInRedis.setAirportStationInfo(hotelLandMark.getAirportStationInfo().toString());
            hotelInRedis.setScenicSpotsInfo(hotelLandMark.getScenicSpotsInfo().toString());
            hotelInRedis.setHospitalInfo(hotelLandMark.getHospitalInfo().toString());
            hotelInRedis.setCollegesInfo(hotelLandMark.getCollegesInfo().toString());

            //
            hotelInRedis.setCacheTime(strDate);
            hotelInRedis.setCacheFrom(cacheFrom);

            hotelInRedis.setHotelSource(hotel.getSourceType());
            //
            jedis.set(hotelKeyName, JsonUtils.toJson(hotelInRedis));

            //
            String cityKeyName = HotelCacheEnum.getCityHotelSetName(String.valueOf(hotel.getCityCode()));
            jedis.sadd(cityKeyName, JsonUtils.toJson(hotelInRedis));

            //
//            OtsInterface.initHotel(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public HotelLandMark getHotelLandMark(Double lon, Double lat, Integer rang){
        if(CollectionUtils.isEmpty(this.getAllLandMarkList())){
            LandMarkExample example = new LandMarkExample();
            List<LandMark> landMarks = landMarkMapper.selectByExample(example);
            this.setAllLandMarkList(landMarks);
        }
        return getHotelLandMark(lon, lat, rang, this.getAllLandMarkList());
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

    public List<String> mergeFanqieHotel (Long innId, Long accountId){

        List<String> result = new ArrayList<String>();

        if (null == innId || null == accountId) {

            //
            SaleList saleList = this.fanqielaileRemoteService.queryHotelList();

            if (null == saleList) {
                return result;
            }

            //
            List<ProxyInns> proxyInnsList = saleList.getProxyInns();
            if (null != proxyInnsList) {

                for (ProxyInns proxyInns: proxyInnsList) {
                    String proxyInnJson = JsonUtils.toJson(proxyInns);
                    result.add(proxyInnJson);
                }
            }
        } else {
            result.add("{\n" +
                    "      \"innId\": "+ innId + ",\n" +
                    "      \"pricePatterns\": [\n" +
                    "        {\n" +
                    "          \"pattern\": \"2\",\n" +
                    "          \"accountId\": " + accountId + "\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }");
        }
        return result;
    }

    public void mergeFangqieHotelByProxyInnJson (String proxyInnJson) {

        ProxyInns proxyInns = JsonUtils.fromJson(proxyInnJson, ProxyInns.class);

        List<PricePatterns> pricePatternsList = proxyInns.getPricePatterns();
        Integer innId = proxyInns.getInnId();

        if (null != pricePatternsList) {

            for (PricePatterns pricePatterns : pricePatternsList) {

                //策略模式1:底价模式 2:卖价模式
                String pattern = pricePatterns.getPattern();
                Integer intPattern = null;
                try {
                    intPattern = Integer.parseInt(pattern);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //
                Integer accountId =  pricePatterns.getAccountId();

                //update
                this.fanqielaileHotelProxyService.saveOrUpdateMapping(innId.longValue(), intPattern, accountId.longValue());

                /*
                    1 酒店
                 */
                Hotel hotel = null;
                InnList innList = this.fanqielaileRemoteService.queryInn(accountId.longValue());
                if (null != innList) {
                    List<Inn> inns = innList.getList();
                    for (Inn inn : inns) {
                        try {
                            hotel = this.fanqielaileHotelProxyService.saveOrUpdateHotel(innId, inn);
                        } catch (Exception e) {
                            Cat.logError(e);
                        }
                    }
                }

                /*
                    2 房型
                 */
                RoomTypeList roomTypeList = this.fanqielaileRemoteService.queryRoomType(
                        accountId.longValue());
                if (null != roomTypeList) {
                    List<RoomType> roomTypes = roomTypeList.getList();
                    for (RoomType roomType : roomTypes) {
                        this.fanqielaileRoomTypeProxyService.saveOrUpdateRoomType(innId, hotel.getId(), roomType);
                    }
                }

                //
                OtsInterface.initHotel(hotel.getId());
            }
        }
    }

    public List<String> mergeFangqieRoomStatus () {

        HotelFanqieMappingExample example = new HotelFanqieMappingExample();
        example.createCriteria().andIsValidEqualTo("T");
        List<HotelFanqieMapping> hotelFanqieMappingList = this.hotelFanqieMappingMapper.selectByExample(example);

        //
        List<String> result = new ArrayList<String>();
        for (HotelFanqieMapping mapping : hotelFanqieMappingList) {
            result.add(JsonUtils.toJson(mapping));
        }
        return result;
    }

    public void mergeFangqieRoomStatusByHotelFanqieMappingJson (String hotelFanqieMappingJson) {
        HotelFanqieMapping hotelFanqieMapping = JsonUtils.fromJson(hotelFanqieMappingJson, HotelFanqieMapping.class);

        Long accountId = hotelFanqieMapping.getAccountId();
        Long innId = hotelFanqieMapping.getInnId();

        //
        Hotel hotel = null;
        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andFangIdEqualTo(innId).andSourceTypeEqualTo(HotelSourceEnum.FANQIE.getId()).andIsValidEqualTo("T");
        List<Hotel> hotelList = this.hotelMapper.selectByExample(hotelExample);
        if (hotelList.isEmpty()) {
            return;
        } else {
            hotel = hotelList.get(0);
        }

        //查询30天的房态
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,30);
        Date nextMonth = calendar.getTime();

        //
        RoomList roomList = this.fanqielaileRemoteService.queryRoomStatus(
                accountId, new Date() , nextMonth);
        if (null != roomList) {

            List<RoomDetailList> roomDetailLists = roomList.getList();
            for (RoomDetailList detailList : roomDetailLists) {
                this.fanqielaileRoomTypeProxyService.saveOrUpdateRoomDetail(hotel.getId(), detailList);
            }
        }

        //
        OtsInterface.initHotel(hotel.getId());
    }


    public HotelFanqieMapping findHotelMappingByHotelId(Long hotelId) {
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        if(hotel == null) {
            return null;
        }
        HotelFanqieMappingExample example = new HotelFanqieMappingExample();
        example.createCriteria().andInnIdEqualTo(hotel.getFangId());
        List<HotelFanqieMapping> hotelFanqieMappingList =  hotelFanqieMappingMapper.selectByExample(example);
        if(org.apache.commons.collections.CollectionUtils.isEmpty(hotelFanqieMappingList)){
            return null;
        }else if(hotelFanqieMappingList.size() > 1){
            throw new MyException("酒店信息错误,根据hotelId找到多个映射酒店信息");
        }
        return hotelFanqieMappingList.get(0);
    }
}
