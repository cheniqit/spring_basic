package com.mk.hotel.roomtype.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.framework.DateUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyErrorEnum;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.common.bean.PageBean;
import com.mk.hotel.common.redisbean.PicList;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelPicTypeEnum;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.hotelinfo.service.impl.HotelPicServiceImpl;
import com.mk.hotel.remote.hawk.HawkRemoteService;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelPriceRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelPriceResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelRoomTypeQueryRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelRoomTypeQueryResponse;
import com.mk.hotel.remote.pms.hotelstock.HotelStockRemoteService;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import com.mk.hotel.roomtype.RoomTypeFullStockLogService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypeFullStockLogDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.enums.BedTypeEnum;
import com.mk.hotel.roomtype.enums.RoomTypeCacheEnum;
import com.mk.hotel.roomtype.enums.RoomTypePriceCacheEnum;
import com.mk.hotel.roomtype.enums.RoomTypeStockCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.model.RoomTypeExample;
import com.mk.hotel.roomtype.model.RoomTypeStock;
import com.mk.hotel.roomtype.redisbean.BedType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private HotelStockRemoteService hotelStockRemoteService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomTypeProxyService roomTypeProxyService;
    @Autowired
    private HotelPicServiceImpl hotelPicService;

    @Autowired
    private HawkRemoteService hawkRemoteService;
    @Autowired
    private RoomTypePriceServiceImpl roomTypePriceService;
    @Autowired
    private RoomTypeStockServiceImpl roomTypeStockService;
    @Autowired
    private RoomTypeFullStockLogService roomTypeFullStockLogService;

    private Logger logger = LoggerFactory.getLogger(RoomTypeServiceImpl.class);

    public RoomTypeDto selectByFangId(Long fangHotelId, Long fangRoomTypeId, HotelSourceEnum hotelSourceEnum) {
        if (null == fangHotelId || null == fangRoomTypeId) {
            throw MyErrorEnum.ROOM_FANG_ID_IS_NULL.getMyException();
        }

        //hotelDto
        HotelDto hotelDto = hotelService.findByFangId(fangHotelId, hotelSourceEnum);
        if (null == hotelDto) {
            return null;
        }
        //
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andHotelIdEqualTo(hotelDto.getId()).andFangIdEqualTo(fangRoomTypeId);

        //
        List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);
        if (roomTypeList.isEmpty()) {
            return null;
        }
        RoomType roomType = roomTypeList.get(0);

        //
        RoomTypeDto dto = new RoomTypeDto();
        BeanUtils.copyProperties(roomType, dto);

        return dto;
    }

    public RoomTypeDto selectById(Long roomTypeId) {
        if (null == roomTypeId) {
            throw MyErrorEnum.ROOM_TYPE_ID_IS_NULL.getMyException();
        }
//        //
//        RoomTypeExample roomTypeExample = new RoomTypeExample();
//        roomTypeExample.createCriteria().andIdEqualTo(roomTypeId);
//        //
//        List<RoomType> roomTypeList = this.roomTypeMapper.selectByExampleWithBLOBs(roomTypeExample);
//        if (roomTypeList.isEmpty()) {
//            return null;
//        }
//        RoomType roomType = roomTypeList.get(0);

        RoomType roomType = this.roomTypeMapper.selectByPrimaryKey(roomTypeId);
        //
        RoomTypeDto dto = new RoomTypeDto();
        BeanUtils.copyProperties(roomType, dto);

        return dto;
    }


    public RoomType selectRoomTypeById(Long roomTypeId) {
        if (null == roomTypeId) {
            throw MyErrorEnum.ROOM_TYPE_ID_IS_NULL.getMyException();
        }
        //
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andIdEqualTo(roomTypeId);
        //
        return this.roomTypeMapper.selectByPrimaryKey(roomTypeId);
    }

    public RoomTypeDto selectByHotelId(Long hotelId) {
        if (null == hotelId) {
            throw MyErrorEnum.ROOM_HOTEL_ID_IS_NULL.getMyException();
        }
        //
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andHotelIdEqualTo(hotelId);
        //
        List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);
        if (roomTypeList.isEmpty()) {
            return null;
        }
        RoomType roomType = roomTypeList.get(0);

        //
        RoomTypeDto dto = new RoomTypeDto();
        BeanUtils.copyProperties(roomType, dto);

        return dto;
    }


    public List<RoomTypeDto> selectRoomTypeByHotelId(Long hotelId) {
        if (null == hotelId) {
            throw MyErrorEnum.ROOM_HOTEL_ID_IS_NULL.getMyException();
        }
        //
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andHotelIdEqualTo(hotelId);
        //
        List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);

        List<RoomTypeDto> roomTypeDtoList = new ArrayList<RoomTypeDto>();
        for (RoomType roomType : roomTypeList) {
            roomTypeDtoList.add(this.convertToDto(roomType));
        }

        return roomTypeDtoList;
    }


    public RoomTypeDto selectByFangId(Long fangRoomTypeId, Long hotelId) {
        if (null == fangRoomTypeId) {
            throw MyErrorEnum.ROOM_FANG_ID_IS_NULL.getMyException();
        }

        //
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andFangIdEqualTo(fangRoomTypeId).andHotelIdEqualTo(hotelId);

        //
        List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);
        if (roomTypeList.isEmpty()) {
            return null;
        }
        RoomType roomType = roomTypeList.get(0);

        //
        RoomTypeDto dto = new RoomTypeDto();
        BeanUtils.copyProperties(roomType, dto);

        return dto;
    }


    @Override
    public RoomTypeDto selectByName(Long hotelId ,String name) {
        if (StringUtils.isBlank(name)) {
            throw MyErrorEnum.ROOM_NAME_IS_NULL.getMyException();
        }
        if (hotelId == null) {
            throw MyErrorEnum.ROOM_HOTEL_ID_IS_NULL.getMyException();
        }
        //
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andNameEqualTo(name).andHotelIdEqualTo(hotelId);

        //
        List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);
        if (roomTypeList.isEmpty()) {
            return null;
        }
        RoomType roomType = roomTypeList.get(0);

        //
        RoomTypeDto dto = new RoomTypeDto();
        BeanUtils.copyProperties(roomType, dto);
        return dto;
    }


    public void deleteByHotelId(Long hotelId, List<Long> idList) {
        if (null == hotelId || null == idList) {
            throw MyErrorEnum.ROOM_PARAMS_IS_NULL.getMyException();
        }

        //
        for (Long id : idList) {
            RoomTypeExample roomTypeExample = new RoomTypeExample();
            roomTypeExample.createCriteria().andHotelIdEqualTo(hotelId).andFangIdEqualTo(id);
            List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);

            if (!roomTypeList.isEmpty()) {
                RoomType roomType = roomTypeList.get(0);
                roomType.setIsValid("F");
                roomType.setUpdateDate(new Date());
                roomType.setUpdateBy("hotel_system");
                this.roomTypeMapper.updateByPrimaryKeySelective(roomType);
                this.updateRedisRoomType(roomType.getId(), roomType, "RoomTypeService.deleteByHotelId");

                //通知hawk
                hawkRemoteService.roomTypeOffline(roomType.getId());
            }
        }

    }


    public void updateOnlineByHotelId(Long hotelId, List<Long> idList) {
        if (null == hotelId || null == idList) {
            throw MyErrorEnum.ROOM_PARAMS_IS_NULL.getMyException();
        }

        //
        for (Long id : idList) {
            RoomTypeExample roomTypeExample = new RoomTypeExample();
            roomTypeExample.createCriteria().andHotelIdEqualTo(hotelId).andFangIdEqualTo(id);
            List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);

            if (!roomTypeList.isEmpty()) {
                RoomType roomType = roomTypeList.get(0);
                roomType.setIsValid("T");
                roomType.setUpdateDate(new Date());
                roomType.setUpdateBy("hotel_system");
                this.roomTypeMapper.updateByPrimaryKeySelective(roomType);
                this.updateRedisRoomType(roomType.getId(), roomType, "RoomTypeService.deleteByHotelId");
            }
        }

    }
    public void saveOrUpdateByHotelId(Long hotelId, List<RoomTypeDto> roomTypeDtoList, HotelSourceEnum hotelSourceEnum) {
        if (null == hotelId || null == roomTypeDtoList) {
            throw MyErrorEnum.ROOM_PARAMS_IS_NULL.getMyException();
        }
        //本次上传的
        Map<Long, RoomTypeDto> dtoMap = new HashMap<Long, RoomTypeDto>();
        for (RoomTypeDto dto : roomTypeDtoList) {
            dtoMap.put(dto.getFangId(), dto);

            //
            this.saveOrUpdateByFangId(dto, hotelSourceEnum);
        }

        //找出hotel下所有roomType
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andHotelIdEqualTo(hotelId);
        List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(roomTypeExample);

        //将数据库有的, roomTypeDtoList没有的标记isValid = "F"
        for (RoomType roomType : roomTypeList) {
            Long fangId = roomType.getFangId();
            if(!dtoMap.containsKey(fangId)) {
                roomType.setIsValid("F");
                roomType.setUpdateDate(new Date());
                roomType.setUpdateBy("hotel_system");

                this.roomTypeMapper.updateByPrimaryKeySelective(roomType);
                this.updateRedisRoomType(roomType.getId(), roomType, "RoomTypeService.saveOrUpdateByFangId(hotelId,list)");
            }
        }

//        //stock
//        this.mergeRoomTypeStockByHotel(hotelId);
    }

    public int saveOrUpdateByFangId(RoomTypeDto roomTypeDto, HotelSourceEnum hotelSourceEnum) {

        if (null == roomTypeDto) {
            throw MyErrorEnum.ROOM_TYPE_DTO_IS_NULL.getMyException();
        }

        //hotelDto
        HotelDto hotelDto = hotelService.findByFangId(roomTypeDto.getFangHotelId(), hotelSourceEnum);
        if (null == hotelDto) {
            throw MyErrorEnum.ROOM_FANG_HOTEL_ID_ERROR.getMyException();
        }
        roomTypeDto.setHotelId(hotelDto.getId());


        //db
        RoomTypeDto dbDto = this.selectByFangId(roomTypeDto.getFangHotelId(),roomTypeDto.getFangId(), hotelSourceEnum);

        if (null == dbDto) {
            RoomType roomType = new RoomType();
            BeanUtils.copyProperties(roomTypeDto, roomType);

            roomType.setCreateDate(new Date());
            roomType.setCreateBy("hotel_system");
            roomType.setUpdateDate(new Date());
            roomType.setUpdateBy("hotel_system");

            int result = this.roomTypeMapper.insert(roomType);

            if (result > 0) {
                //redis
                this.updateRedisRoomType(roomType.getId(), roomType, "RoomTypeService.saveOrUpdateByFangId");
            }

            return result;
        } else {

            RoomType roomType = new RoomType();
            roomType.setId(dbDto.getId());
            roomType.setFangId(dbDto.getFangId());
            roomType.setHotelId(roomTypeDto.getHotelId());
            roomType.setName(roomTypeDto.getName());
            roomType.setArea(roomTypeDto.getArea());
            roomType.setBedType(roomTypeDto.getBedType());
            roomType.setRoomNum(roomTypeDto.getRoomNum());
            roomType.setPrepay(roomTypeDto.getPrepay());
            roomType.setBreakfast(roomTypeDto.getBreakfast());
            roomType.setRefund(roomTypeDto.getRefund());
            roomType.setMaxRoomNum(roomTypeDto.getMaxRoomNum());
            roomType.setRoomTypePics(roomTypeDto.getRoomTypePics());
            roomType.setIsValid(roomTypeDto.getIsValid());

            roomType.setUpdateBy("hotel_system");
            roomType.setUpdateDate(new Date());

            //redis
            this.updateRedisRoomType(dbDto.getId(), roomType, "RoomTypeService.saveOrUpdateByFangId");
            int result = this.roomTypeMapper.updateByPrimaryKeySelective(roomType);

//            //stock
//            this.mergeRoomTypeStockByHotel(hotelDto.getId());
            return result;
        }
    }

    public void mergeRoomType(){
        int pageNo = 1;
        mergeRoomType(pageNo);
    }

    public void mergeRoomTypeByHotelId(Hotel hotel) {
        HotelRoomTypeQueryRequest hotelRoomTypeQueryRequest = new HotelRoomTypeQueryRequest();
        hotelRoomTypeQueryRequest.setHotelid(String.valueOf(hotel.getId()));
        HotelRoomTypeQueryResponse response = hotelRemoteService.queryRoomType(hotelRoomTypeQueryRequest);
        if(response == null || response.getData() == null || CollectionUtils.isEmpty(response.getData())){
            return;
        }
        roomTypeProxyService.saveRoomType(hotel, response.getData());
        OtsInterface.initHotel(hotel.getId());
    }

    public void mergeRoomTypeByHotelId(Long hotelId) {
        //
        HotelExample example = new HotelExample();
        example.createCriteria().andIdEqualTo(hotelId);
        List<Hotel> hotelList = hotelMapper.selectByExample(example);
        if (hotelList.isEmpty()) {
            throw MyErrorEnum.HOTEL_NOT_FOUND.getMyException();
        }
        Hotel hotel = hotelList.get(0);
        //
        HotelRoomTypeQueryRequest hotelRoomTypeQueryRequest = new HotelRoomTypeQueryRequest();
        hotelRoomTypeQueryRequest.setHotelid(String.valueOf(hotelId));
        HotelRoomTypeQueryResponse response = hotelRemoteService.queryRoomType(hotelRoomTypeQueryRequest);
        if(response == null || response.getData() == null || CollectionUtils.isEmpty(response.getData())){
            return;
        }
        roomTypeProxyService.saveRoomType(hotel, response.getData());
        OtsInterface.initHotel(hotelId);
    }

    public void mergeRoomType(int pageNo) {
        logger.info("begin mergeRoomType pageNo {}", pageNo);
        HotelRoomTypeQueryRequest hotelRoomTypeQueryRequest = new HotelRoomTypeQueryRequest();
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
            hotelRoomTypeQueryRequest.setHotelid(hotel.getFangId().toString());
            HotelRoomTypeQueryResponse response = hotelRemoteService.queryRoomType(hotelRoomTypeQueryRequest);
            if(response == null || response.getData() == null || CollectionUtils.isEmpty(response.getData())){
                return;
            }
            roomTypeProxyService.saveRoomType(hotel, response.getData());
            OtsInterface.initHotel(new Long(hotel.getId()));
        }
        logger.info("end mergeRoomTypePrice pageNo {}", pageNo);
        pageNo++;
        mergeRoomType(pageNo);
    }

    public Long getHotelIdByRedis (Long roomTypeId) {
        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            //roomTypeKey
            String roomTypeKeyName = RoomTypeCacheEnum.getRoomTypeKeyName(String.valueOf(roomTypeId));
            String json = jedis.get(roomTypeKeyName);
            com.mk.hotel.roomtype.redisbean.RoomType roomType = JsonUtils.fromJson(json, com.mk.hotel.roomtype.redisbean.RoomType.class);

            if (null == roomType) {
                return null;
            } else {
                return roomType.getHotelId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return null;
    }

    public void updateRedisRoomType(Long roomTypeId, RoomType roomType, String cacheFrom) {
        if (null == roomTypeId || null == roomType || null == roomType.getHotelId()) {
            return;
        }

        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = formatTime.format(new Date());
        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();

            //roomTypeKey
            String roomTypeKeyName = RoomTypeCacheEnum.getRoomTypeKeyName(String.valueOf(roomTypeId));
            //bedtype
            BedType bedType = new BedType();
            bedType.setType(roomType.getBedType());
            if (null != roomType.getBedType()) {
                BedTypeEnum bedTypeEnum = BedTypeEnum.getById(roomType.getBedType());
                bedType.setName(bedTypeEnum.getName());
            }
            bedType.setLength(roomType.getBedSize());

            //roomtype pic
            String strPics = roomType.getRoomTypePics();
            List<PicList> picLists = new ArrayList<PicList>();

            if (StringUtils.isNotBlank(strPics)) {
                logger.info("updateRedisRoomType strPics :{}", strPics);
                JSONArray picArray = JSONArray.parseArray(strPics);
                for (int i = 0; i < picArray.size(); i++) {
                    String strPic = picArray.getString(i);
                    PicList picList = JsonUtils.fromJson(strPic, PicList.class);
                    picList = hotelPicService.replacePicList(null, roomTypeId, picList);
                    picLists.add(picList);
                }
            }else{
                PicList picList = new PicList();
                picList.setName(HotelPicTypeEnum.def.getPmsPicCode());
                picList = hotelPicService.replacePicList(null, roomTypeId, picList);
                if(org.apache.commons.collections.CollectionUtils.isNotEmpty(picList.getPic())){
                    picLists.add(picList);
                }
            }

            //roomtype
            com.mk.hotel.roomtype.redisbean.RoomType roomTypeInRedis = new com.mk.hotel.roomtype.redisbean.RoomType();
            roomTypeInRedis.setHotelId(roomType.getHotelId());
            roomTypeInRedis.setRoomTypeId(roomType.getId());
            roomTypeInRedis.setSourceId(String.valueOf(roomType.getFangId()));
            roomTypeInRedis.setRoomTypeName(roomType.getName());
            roomTypeInRedis.setArea(roomType.getArea());
            roomTypeInRedis.setBedType(bedType);
            roomTypeInRedis.setBreakFast(roomType.getBreakfast());
            //0、可定；1、不可订
            if ("F".equals(roomType.getIsValid())) {
                roomTypeInRedis.setStatus(1);
            } else {
                roomTypeInRedis.setStatus(0);
            }
            roomTypeInRedis.setRoomNum(roomType.getRoomNum());
            roomTypeInRedis.setRoomTypePics(picLists);

            roomTypeInRedis.setCacheTime(strDate);
            roomTypeInRedis.setCacheFrom(cacheFrom);

            jedis.set(roomTypeKeyName, JsonUtils.toJson(roomTypeInRedis));

            //roomTypeSet
            String roomTypeSetName = RoomTypeCacheEnum.getRoomTypeSetName(String.valueOf(roomType.getHotelId()));
            Set<String> roomTypeSet = jedis.smembers(roomTypeSetName);
            //先删除
            for (String roomTypeJsonInRedis : roomTypeSet) {
                com.mk.hotel.roomtype.redisbean.RoomType oriRoomTypeInRedis =
                        JsonUtils.fromJson(roomTypeJsonInRedis, com.mk.hotel.roomtype.redisbean.RoomType.class);

                if (roomTypeId.equals(oriRoomTypeInRedis.getRoomTypeId())) {
                    jedis.srem(roomTypeSetName, roomTypeJsonInRedis);
                    break;
                }
            }
            //
            jedis.sadd(roomTypeSetName, JsonUtils.toJson(roomTypeInRedis));

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    public void mergeRoomTypePrice(){
        int pageNo = 1;
        mergeRoomTypePrice(pageNo);
    }

    public void mergeRoomTypePriceByHotelId(Hotel hotel){
        HotelPriceRequest hotelPriceRequest = new HotelPriceRequest();
        hotelPriceRequest.setHotelid(hotel.getFangId().toString());
        hotelPriceRequest.setBegintime(DateUtils.formatDate(new Date()));
        hotelPriceRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
        HotelPriceResponse response = hotelRemoteService.queryHotelPrice(hotelPriceRequest);
        if(response == null || response.getData() == null || CollectionUtils.isEmpty(response.getData().getRoomtypeprices())){
            return;
        }
        roomTypeProxyService.saveRoomTypePrice(response.getData(), hotel.getId());
        OtsInterface.initHotel(new Long(hotel.getId()));
    }

    public void mergeRoomTypePriceByHotelId(Long hotelId){
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        mergeRoomTypePriceByHotelId(hotel);
    }

    public List<Hotel> getHotelByPage(Integer pageNo){
        return getHotelByPage(pageNo, null);
    }

    public List<Hotel> getHotelByPage(Integer pageNo, Integer pageSize){
        logger.info("begin getHotelByPage pageNo {}", pageNo);
        //酒店分页
        HotelExample hotelExample = new HotelExample();
        int count = hotelMapper.countByExample(hotelExample);
        if(pageSize == null){
            pageSize = Constant.DEFAULT_REMOTE_PAGE_SIZE;
        }
        PageBean pageBean = new PageBean(pageNo, count, pageSize);
        HotelExample example = new HotelExample();
        example.setStart(pageBean.getStart());
        example.setPageCount(pageBean.getPageCount());
        return hotelMapper.selectByExample(example);
    }

    public void mergeRoomTypePrice(int pageNo) {
        List<Hotel> hotelList = getHotelByPage(pageNo);
        if(CollectionUtils.isEmpty(hotelList)){
            return;
        }
        for(Hotel hotel : hotelList){
            HotelPriceRequest hotelPriceRequest = new HotelPriceRequest();
            hotelPriceRequest.setHotelid(hotel.getFangId().toString());
            hotelPriceRequest.setBegintime(DateUtils.formatDate(new Date()));
            hotelPriceRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
            HotelPriceResponse response = hotelRemoteService.queryHotelPrice(hotelPriceRequest);
            if(response == null || response.getData() == null || CollectionUtils.isEmpty(response.getData().getRoomtypeprices())){
                return;
            }
            roomTypeProxyService.saveRoomTypePrice(response.getData(), hotel.getId());
            OtsInterface.initHotel(new Long(hotel.getId()));
        }
        logger.info("end mergeRoomTypePrice pageNo {}", pageNo);
        pageNo++;
        mergeRoomTypePrice(pageNo);
    }

    public void mergeRoomTypeStock(){
        int pageNo = 1;
        mergeRoomTypeStock(pageNo);
    }

    public void mergeRoomTypeStock(int pageNo) {
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
            mergeRoomTypeStockByHotel(hotel);
        }
        pageNo++;
        mergeRoomTypeStock(pageNo);
    }




    @Override
    public void mergeRoomTypeStockByHotel(Long hotelId){
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        mergeRoomTypeStockByHotel(hotel);
    }

    public void mergeRoomTypeStockByHotel(Hotel hotel){
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
        QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);
        if(response == null || response.getData() == null){
            return;
        }
        roomTypeProxyService.saveRoomTypeStock(hotel, response.getData());
    }


    @Override
    public void mergeRoomTypeDayStockByHotel(Long hotelId){
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        mergeRoomTypeDayStockByHotel(hotel);
    }

    public void mergeRoomTypeDayStock(){
        mergeRoomTypeDayStock(1);
    }

    @Override
    public void mergeRoomTypeDayStock(Integer pageNo) {
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
            mergeRoomTypeDayStockByHotel(hotel);
        }
        pageNo++;
        mergeRoomTypeDayStock(pageNo);
    }

    public void mergeRoomTypeDayStockByHotel(Hotel hotel){
        QueryStockRequest queryStockRequest = new QueryStockRequest();
        queryStockRequest.setHotelid(hotel.getFangId().toString());
        queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
        queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 1)));
        QueryStockResponse response = hotelStockRemoteService.queryDatStock(queryStockRequest);
        if(response == null || response.getData() == null){
            return;
        }
        roomTypeProxyService.saveRoomTypeStock(hotel, response.getData());
    }

    public void clearStockAndPrice() {
        Jedis jedis = null;
        try {
            jedis = RedisUtil.getJedis();

            /*
                先清除hotel库
             */
            try {
                this.clearStockAndPriceByHotel(jedis);
            } catch (Exception e) {
                Cat.logError(e);
            }

            /*
                再清除 高德库
             */
            try {
                this.clearStockAndPriceByFullStockLog(jedis);
            } catch (Exception e) {
                Cat.logError(e);
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

    private void clearStockAndPriceByFullStockLog(Jedis jedis) {

        if (null == jedis) {
            return;
        }
        //
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE, -1);
        Date yesterday = calendar.getTime();

        List<RoomTypeFullStockLogDto> logDtoList = roomTypeFullStockLogService.queryByDay(yesterday);
        for (RoomTypeFullStockLogDto dto : logDtoList) {
            Long roomTypeId = dto.getRoomTypeId();

            //key
            String totalKey = RoomTypeStockCacheEnum.TOTAL_KEY.getName() + roomTypeId;
            this.clearRedisHash(jedis, totalKey, "yyyy-MM-dd");

            //
            String totalPromoKey = RoomTypeStockCacheEnum.TOTAL_PROMO_KEY.getName() + roomTypeId;
            this.clearRedisHash(jedis, totalPromoKey, "yyyy-MM-dd");

            //
            String availableKey = RoomTypeStockCacheEnum.AVAILABLE_KEY.getName() + roomTypeId;
            this.clearRedisHash(jedis, availableKey, "yyyy-MM-dd");

            //
            String promoKey = RoomTypeStockCacheEnum.PROMO_KEY.getName() + roomTypeId;
            this.clearRedisHash(jedis, promoKey, "yyyy-MM-dd");

            //
            String roomTypePriceKey = RoomTypePriceCacheEnum.PRICE_KEY.getName() + roomTypeId;
            this.clearRedisHash(jedis, roomTypePriceKey, "yyyyMMdd");
        }
    }

    private void clearStockAndPriceByHotel(Jedis jedis) {

        if (null == jedis) {
            return;
        }
        //
        int pageSize = 1000;

        //count
        RoomTypeExample example = new RoomTypeExample();
        example.setOrderByClause(" id");
        int count = this.roomTypeMapper.countByExample(example);
        int pageCount = 0;
        if (count <= 0) {
            pageCount = 0;
        } else {
            pageCount = (count - 1) / pageSize + 1;
        }

        //
        for (int i = 0; i < pageCount; i++) {
            int start = pageSize * i;
            int end = pageSize * (i + 1);
            example.setStart(start);
            example.setPageCount(pageSize);
            //
            List<RoomType> roomTypeList = this.roomTypeMapper.selectByExample(example);

            //key
            String totalKey = RoomTypeStockCacheEnum.TOTAL_KEY.getName();
            Set<String> totalStockSet = this.fillRedisKeyName(totalKey, roomTypeList);
            for (String key : totalStockSet) {
                this.clearRedisHash(jedis, key, "yyyy-MM-dd");
            }

            //
            String totalPromoKey = RoomTypeStockCacheEnum.TOTAL_PROMO_KEY.getName();
            Set<String> totalPromoStockSet = this.fillRedisKeyName(totalPromoKey, roomTypeList);
            for (String key : totalPromoStockSet) {
                this.clearRedisHash(jedis, key, "yyyy-MM-dd");
            }

            //
            String availableKey = RoomTypeStockCacheEnum.AVAILABLE_KEY.getName();
            Set<String> availableStockSet = this.fillRedisKeyName(availableKey, roomTypeList);
            for (String key : availableStockSet) {
                this.clearRedisHash(jedis, key, "yyyy-MM-dd");
            }

            //
            String promoKey = RoomTypeStockCacheEnum.PROMO_KEY.getName();
            Set<String> promoStockSet = this.fillRedisKeyName(promoKey, roomTypeList);
            for (String key : promoStockSet) {
                this.clearRedisHash(jedis, key, "yyyy-MM-dd");
            }

            //
            String roomTypePriceKey = RoomTypePriceCacheEnum.PRICE_KEY.getName();
            Set<String> priceSet = this.fillRedisKeyName(roomTypePriceKey, roomTypeList);
            for (String key : priceSet) {
                this.clearRedisHash(jedis, key, "yyyyMMdd");
            }
        }
    }

    private Set<String> fillRedisKeyName(String keyName, List<RoomType> roomTypeIdList) {

        Set<String> result = new HashSet<String>();

        for (RoomType roomType : roomTypeIdList) {
            result.add(keyName + roomType.getId());
        }
        return result;
    }
    private void clearRedisHash(Jedis jedis, String hKey, String fieldFormat) {
        if (null == jedis || StringUtils.isBlank(hKey) || StringUtils.isBlank(fieldFormat)) {
            return;
        }

        //
        SimpleDateFormat format = new SimpleDateFormat(fieldFormat);

        //toDay
        String strToday = format.format(new Date());
        Date today = null;
        try {
            today = format.parse(strToday);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //
        Map<String,String> map = jedis.hgetAll(hKey);
        for (String field : map.keySet()) {
            try {
                Date keyDate = format.parse(field);
                //若keyDate 早于 今天,清理掉
                if (today.after(keyDate)) {
                    logger.info("RoomTypeServiceImpl.clearRedisHash key:{} field:{}", hKey, field);
                    jedis.hdel(hKey, field);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    public String updateRoomTypeToRedis(Long hotelId, Long roomTypeId){
        RoomType roomType = selectRoomTypeById(roomTypeId);
        if(roomType == null || roomType.getId() == null){
            throw MyErrorEnum.ROOM_NOT_FOUND.getMyException();
        }
        //房型信息
        this.updateRedisRoomType(roomTypeId, roomType, "RoomTypeService.updateRoomTypeToRedis");
        List<RoomTypePriceDto> roomTypePriceList  = roomTypePriceService.getRoomTypePrice(roomTypeId, new Date(), DateUtils.addDays(new Date(), 30));
        //酒店价格
        for(RoomTypePriceDto roomTypePrice : roomTypePriceList){
            roomTypePriceService.updateRedisPrice(roomTypeId, roomType.getName(), roomTypePrice.getDay(), roomTypePrice.getPrice(), roomTypePrice.getCost(), "RoomTypeService.updateRoomTypeToRedis");
        }
        List<RoomTypeStock> roomTypeStockList = roomTypeStockService.queryRoomStockByRoomTypeId(roomTypeId, new Date(), DateUtils.addDays(new Date(), 30));
        //库存
        StringBuilder result = new StringBuilder();
        for(RoomTypeStock roomTypeStock : roomTypeStockList){
            String subResult = roomTypeStockService.updateRedisStockByTotal(hotelId, roomTypeId,
                    roomTypeStock.getDay(), roomTypeStock.getTotalNumber().intValue(), 0);
            result.append(subResult);
        }

        return result.toString();
    }


    private RoomTypeDto convertToDto (RoomType roomType) {
        RoomTypeDto dto = new RoomTypeDto();
        if (null == roomType) {
            return dto;
        }

        dto.setId(roomType.getId());
        dto.setFangId(roomType.getFangId());
        dto.setHotelId(roomType.getHotelId());
        dto.setName(roomType.getName());
        dto.setArea(roomType.getArea());
        dto.setBedType(roomType.getBedType());
        dto.setBedSize(roomType.getBedSize());
        dto.setRoomNum(roomType.getRoomNum());
        dto.setPrepay(roomType.getPrepay());
        dto.setBreakfast(roomType.getBreakfast());
        dto.setStatus(roomType.getStatus());
        dto.setRefund(roomType.getRefund());
        dto.setMaxRoomNum(roomType.getMaxRoomNum());
        dto.setRoomTypePics(roomType.getRoomTypePics());
        dto.setCreateDate(roomType.getCreateDate());
        dto.setCreateBy(roomType.getCreateBy());
        dto.setUpdateDate(roomType.getUpdateDate());
        dto.setUpdateBy(roomType.getUpdateBy());
        dto.setIsValid(roomType.getIsValid());
        return dto;

    }
}
