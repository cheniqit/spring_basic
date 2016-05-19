package com.mk.hotel.roomtype.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dianping.cat.Cat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mk.framework.Constant;
import com.mk.framework.DateUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.common.bean.PageBean;
import com.mk.hotel.common.redisbean.PicList;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelPriceRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelPriceResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelRoomTypeQueryRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelRoomTypeQueryResponse;
import com.mk.hotel.remote.pms.hotelstock.HotelStockRemoteService;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockRequest;
import com.mk.hotel.remote.pms.hotelstock.json.QueryStockResponse;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.enums.BedTypeEnum;
import com.mk.hotel.roomtype.enums.RoomTypeCacheEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.model.RoomTypeExample;
import com.mk.hotel.roomtype.redisbean.BedType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

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

    private Logger logger = LoggerFactory.getLogger(RoomTypeServiceImpl.class);

    public RoomTypeDto selectByFangId(Long fangHotelId, Long fangRoomTypeId) {
        if (null == fangHotelId || null == fangRoomTypeId) {
            throw new MyException("-99", "-99", "fangId 不可为空");
        }

        //hotelDto
        HotelDto hotelDto = hotelService.findByFangId(fangHotelId);
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

    public RoomTypeDto selectByFangId(Long fangRoomTypeId) {
        if (null == fangRoomTypeId) {
            throw new MyException("-99", "-99", "fangId 不可为空");
        }

        //
        RoomTypeExample roomTypeExample = new RoomTypeExample();
        roomTypeExample.createCriteria().andFangIdEqualTo(fangRoomTypeId);

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

    public void saveOrUpdateByHotelId(Long hotelId, List<RoomTypeDto> roomTypeDtoList) {
        if (null == hotelId || null == roomTypeDtoList) {
            throw new MyException("-99", "-99", "hotelId、roomTypeDtoList 不可为空");
        }
        //本次上传的
        Map<Long, RoomTypeDto> dtoMap = new HashMap<Long, RoomTypeDto>();
        for (RoomTypeDto dto : roomTypeDtoList) {
            dtoMap.put(dto.getFangId(), dto);

            //
            this.saveOrUpdateByFangId(dto);
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
    }

    public int saveOrUpdateByFangId(RoomTypeDto roomTypeDto) {

        if (null == roomTypeDto) {
            throw new MyException("-99", "-99", "roomTypeDto 不可为空");
        }

        //hotelDto
        HotelDto hotelDto = hotelService.findByFangId(roomTypeDto.getFangHotelId());
        if (null == hotelDto) {
            throw new MyException("-99", "-99", "错误的roomTypeDto.FangHotelId");
        }
        roomTypeDto.setHotelId(hotelDto.getId());


        //db
        RoomTypeDto dbDto = this.selectByFangId(roomTypeDto.getFangHotelId(),roomTypeDto.getFangId());

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

            return this.roomTypeMapper.updateByPrimaryKeySelective(roomType);
        }
    }

    public void mergeRoomType(){
        int pageNo = 1;
        mergeRoomType(pageNo);
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
        }
        logger.info("end mergeRoomTypePrice pageNo {}", pageNo);
        pageNo++;
        mergeRoomType(pageNo);
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
            bedType.setName(BedTypeEnum.getById(roomType.getBedType()).getName());
            bedType.setLength(roomType.getBedSize());

            //roomtype pic
            String strPics = roomType.getRoomTypePics();
            List<PicList> picLists = new ArrayList<PicList>();

            if (StringUtils.isNotBlank(strPics)) {
                JSONArray picArray = JSONArray.parseArray(strPics);
                for (int i = 0; i < picArray.size(); i++) {
                    String strPic = picArray.getString(i);
                    PicList picList = JsonUtils.fromJson(strPic, PicList.class);
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
            if ("T".equals(roomType.getIsValid())) {
                roomTypeInRedis.setStatus(0);
            } else {
                roomTypeInRedis.setStatus(1);
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

    public void mergeRoomTypePrice(int pageNo) {
        logger.info("begin mergeRoomTypePrice pageNo {}", pageNo);
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
            HotelPriceRequest hotelPriceRequest = new HotelPriceRequest();
            hotelPriceRequest.setHotelid(hotel.getFangId().toString());
            hotelPriceRequest.setBegintime(DateUtils.formatDate(new Date()));
            hotelPriceRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 30)));
            HotelPriceResponse response = hotelRemoteService.queryHotelPrice(hotelPriceRequest);
            if(response == null || response.getData() == null || CollectionUtils.isEmpty(response.getData().getRoomtypeprices())){
                return;
            }
            roomTypeProxyService.saveRoomTypePrice(response.getData());
        }
        logger.info("end mergeRoomTypePrice pageNo {}", pageNo);
        pageNo++;
        mergeRoomType(pageNo);
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
        pageNo++;
        mergeRoomType(pageNo);
    }
}
