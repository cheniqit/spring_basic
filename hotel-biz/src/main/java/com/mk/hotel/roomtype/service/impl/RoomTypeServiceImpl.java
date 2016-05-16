package com.mk.hotel.roomtype.service.impl;

import com.dianping.cat.Cat;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public int save(RoomTypeDto roomTypeDto) {
        if (null == roomTypeDto) {
            throw new MyException("-99", "-99", "roomTypeDto 不可为空");
        }
        RoomType roomType = new RoomType();
        BeanUtils.copyProperties(roomTypeDto, roomType);

        return this.roomTypeMapper.insert(roomType);
    }

    public int saveOrUpdateByFangId(RoomTypeDto roomTypeDto) {

        if (null == roomTypeDto) {
            throw new MyException("-99", "-99", "roomTypeDto 不可为空");
        }

        //hotelDto
        HotelDto hotelDto = hotelService.findByFangId(roomTypeDto.getFangHotelId());
        if (null == hotelDto) {
            return -1;
        }
        roomTypeDto.setHotelId(hotelDto.getId());

        //
        RoomTypeDto dbDto = this.selectByFangId(roomTypeDto.getFangHotelId(),roomTypeDto.getFangId());

        if (null == dbDto) {
            return this.save(roomTypeDto);
        } else {
            RoomType dbRoomType = new RoomType();
            dbRoomType.setId(dbDto.getId());
            dbRoomType.setHotelId(roomTypeDto.getHotelId());
            dbRoomType.setName(roomTypeDto.getName());
            dbRoomType.setArea(roomTypeDto.getArea());
            dbRoomType.setBedType(roomTypeDto.getBedType());
            dbRoomType.setRoomNum(roomTypeDto.getRoomNum());
            dbRoomType.setPrepay(roomTypeDto.getPrepay());
            dbRoomType.setBreakfast(roomTypeDto.getBreakfast());
            dbRoomType.setRefund(roomTypeDto.getRefund());
            dbRoomType.setMaxRoomNum(roomTypeDto.getMaxRoomNum());
            dbRoomType.setRoomTypePics(roomTypeDto.getRoomTypePics());
            dbRoomType.setIsValid(roomTypeDto.getIsValid());

            return this.roomTypeMapper.updateByPrimaryKeySelective(dbRoomType);
        }
    }

    public void mergeRoomType(){
        int pageNo = 1;
        mergeRoomType(pageNo);
    }

    public void mergeRoomType(int pageNo) {
        HotelRoomTypeQueryRequest hotelRoomTypeQueryRequest = new HotelRoomTypeQueryRequest();
        //酒店分页
        HotelExample hotelExample = new HotelExample();
        int count = hotelMapper.countByExample(hotelExample);
        PageBean pageBean = new PageBean(pageNo, count, Constant.DEFAULT_REMOTE_PAGE_SIZE);
        HotelExample example = new HotelExample();
        example.setStart(pageBean.getStart());
        example.setEnd(pageBean.getEnd());
        List<Hotel> hotelList = hotelMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(hotelList)){
            return;
        }
        for(Hotel hotel : hotelList){
            hotelRoomTypeQueryRequest.setHotelid(hotel.getFangId().toString());
            HotelRoomTypeQueryResponse response = hotelRemoteService.queryRoomType(hotelRoomTypeQueryRequest);
            if(response.getData() == null || CollectionUtils.isEmpty(response.getData())){
                return;
            }
            roomTypeProxyService.saveRoomType(hotel, response.getData());
        }
        pageNo++;
        mergeRoomType(pageNo);
    }


    public void updateRedisRoomType(String roomTypeId, RoomTypeDto roomTypeDto, String cacheFrom) {
        if (StringUtils.isBlank(roomTypeId) || null == roomTypeDto || null == roomTypeDto.getHotelId()) {
            return;
        }

        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = formatTime.format(new Date());
        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
            String roomTypeKeyName = RoomTypeCacheEnum.getRoomTypeKeyName(roomTypeId);
            //
            BedType bedType = new BedType();
            bedType.setType(roomTypeDto.getBedType());
            bedType.setName(BedTypeEnum.getById(roomTypeDto.getBedType()).getName());
            bedType.setLength(roomTypeDto.getBedSize());

            com.mk.hotel.roomtype.redisbean.RoomType roomType = new com.mk.hotel.roomtype.redisbean.RoomType();

            roomType.setHotelId(roomTypeDto.getHotelId());
            roomType.setRoomTypeId(roomTypeDto.getId());
            roomType.setRoomTypeName(roomTypeDto.getName());
            roomType.setArea(roomTypeDto.getArea());
            roomType.setBedType(bedType);
            roomType.setBreakFast(roomTypeDto.getBreakfast());
            roomType.setStatus(roomTypeDto.getStatus());
            roomType.setRoomNum(roomTypeDto.getRoomNum());
            roomType.setRoomTypePics(null);

            roomType.setCacheTime(strDate);
            roomType.setCacheFrom(cacheFrom);

            jedis.set(roomTypeKeyName, JsonUtils.toJson(roomType));

            //
            String roomTypeSetName = RoomTypeCacheEnum.getRoomTypeSetName(String.valueOf(roomTypeDto.getHotelId()));
            //TODO add
            Set<String> roomTypeSet = jedis.smembers(roomTypeSetName);
            //先删除
            for (String roomTypeJson : roomTypeSet) {
                if (1 == 1) {
                    jedis.srem(roomTypeSetName,roomTypeJson);
                }
            }
            //
            jedis.sadd(roomTypeKeyName,null);

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

        //酒店分页
        HotelExample hotelExample = new HotelExample();
        int count = hotelMapper.countByExample(hotelExample);
        PageBean pageBean = new PageBean(pageNo, count, Constant.DEFAULT_REMOTE_PAGE_SIZE);
        HotelExample example = new HotelExample();
        example.setStart(pageBean.getStart());
        example.setEnd(pageBean.getEnd());
        List<Hotel> hotelList = hotelMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(hotelList)){
            return;
        }
        for(Hotel hotel : hotelList){
            HotelPriceRequest hotelPriceRequest = new HotelPriceRequest();
            hotelPriceRequest.setHotelid(hotel.getFangId().toString());
            hotelPriceRequest.setBegintime(DateUtils.formatDate(new Date()));
            hotelPriceRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 5)));
            HotelPriceResponse response = hotelRemoteService.queryHotelPrice(hotelPriceRequest);
            if(response.getData() == null || response.getData() == null || CollectionUtils.isEmpty(response.getData().getRoomtypeprices())){
                return;
            }
            roomTypeProxyService.saveRoomTypePrice(response.getData());
        }
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
        example.setEnd(pageBean.getEnd());
        List<Hotel> hotelList = hotelMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(hotelList)){
            return;
        }
        for(Hotel hotel : hotelList){
            QueryStockRequest queryStockRequest = new QueryStockRequest();
            queryStockRequest.setHotelid(hotel.getFangId().toString());
            queryStockRequest.setBegintime(DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_DATE));
            queryStockRequest.setEndtime(DateUtils.formatDate(DateUtils.addDays(new Date(), 5)));
            QueryStockResponse response = hotelStockRemoteService.queryStock(queryStockRequest);
            if(response == null || response.getData() == null){
                return;
            }
            roomTypeProxyService.saveRoomTypeStock(response.getData());
        }
        pageNo++;
        mergeRoomType(pageNo);
    }
}
