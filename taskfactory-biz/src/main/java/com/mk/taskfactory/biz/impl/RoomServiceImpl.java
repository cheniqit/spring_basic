package com.mk.taskfactory.biz.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mk.taskfactory.api.RoomService;
import com.mk.taskfactory.api.dtos.OtsRoomStateDto;
import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomDto;
import com.mk.taskfactory.biz.mapper.RoomMapper;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import com.mk.taskfactory.common.Constants;
import com.mk.taskfactory.model.TRoom;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {

    private static Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public int countRoomByRoomType(int roomTypeId) {
        return this.roomMapper.countRoomByRoomType(roomTypeId);
    }

    @Override
    public int updateRoomTypeByRoomType(TRoomChangeTypeDto roomChangeTypeDto) {
        if (null == roomChangeTypeDto
                || null == roomChangeTypeDto.getId()
                || null == roomChangeTypeDto.getRoomTypeId()) {
            //TODO LOG
            return 0;
        }
        return this.roomMapper.updateRoomTypeByRoomType(roomChangeTypeDto);
    }

    public List<TRoomDto> queryRoomByParams(TRoomDto bean){
        List<TRoom> list=roomMapper.queryRoomByParams(bean);
        if (list==null){
            return  null;
        }
        List<TRoomDto> roomDtos=new ArrayList<TRoomDto>();
        for (TRoom room :list) {
            roomDtos.add(buildTRoomDto(room));
        }
        return  roomDtos;
    }
    public Integer saveTRoom(TRoomDto bean){
        return  roomMapper.saveTRoom(bean);
    }
    public Integer deleteTRoomByRoomTypeId(Integer roomTypeId){
        return  roomMapper.deleteTRoomByRoomTypeId(roomTypeId);
    }
    public Integer updateRoomById(TRoomDto bean){
        return roomMapper.updateRoomById(bean);
    }
    public List<TRoomDto> findRoomsByHotelId(Integer hotelId){
        List<TRoom> list=roomMapper.findRoomsByHotelId(hotelId);
        if (list==null){
            return  null;
        }
        List<TRoomDto> roomDtos=new ArrayList<TRoomDto>();
        for (TRoom room :list) {
            roomDtos.add(buildTRoomDto(room));
        }
        return  roomDtos;
    }
    public List<TRoomDto> findRoomsByRoomTypeId(Integer roomTypeId){
        List<TRoom> list=roomMapper.findRoomsByRoomTypeId(roomTypeId);
        if (list==null){
            return  null;
        }
        List<TRoomDto> roomDtos=new ArrayList<TRoomDto>();
        for (TRoom room :list) {
            roomDtos.add(buildTRoomDto(room));
        }
        return  roomDtos;
    }
    public TRoomDto findRoomsById(Integer id){
        TRoom room=roomMapper.findRoomsById(id);
        if (room==null){
            return  null;
        }
        return  buildTRoomDto(room);
    }
    private TRoomDto buildTRoomDto(TRoom bean) {
        if (bean==null){
            return new TRoomDto();
        }
        TRoomDto roomDto=new TRoomDto();
        BeanUtils.copyProperties(bean, roomDto);
        return roomDto;
    }

    public TRoomDto queryRoomByName(TRoomDto bean) throws Exception {
        List<TRoom> roomList = this.roomMapper.queryRoomByParams(bean);
        if (roomList.isEmpty()) {
            throw new Exception("无房间");
        }
        TRoom room = roomList.get(0);

        TRoomDto roomDto = new TRoomDto();
        BeanUtils.copyProperties(room, roomDto);

        return roomDto;
    }

    public  Integer  updateRoomTypeByRoomType(Integer id,Integer  roomTypeId){
        HashMap  hm = new HashMap();
        hm.put("id",id);
        hm.put("roomTypeId",roomTypeId);
        return  this.roomMapper.updateRoomTypeByName(hm);
    }

    @Override
    public OtsRoomStateDto getOtsRoomState(Integer hotelId, Integer roomTypeId, Date startDate, Date endDate) {
        OtsRoomStateDto result = new OtsRoomStateDto();
        result.setHotelId(hotelId);
        if (null == hotelId || null == roomTypeId) {
            return result;
        }
        //调用房态接口
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("isShowAllRoom","T");
        paramMap.put("hotelid", String.valueOf(hotelId));
        paramMap.put("roomtypeid", String.valueOf(roomTypeId));
        if (null != startDate) {
            paramMap.put("startdateday", format.format(startDate));
        } else {
            paramMap.put("startdateday", format.format(new Date()));
        }

        if (null != endDate) {
            paramMap.put("enddateday",format.format(endDate));
        } else {
            paramMap.put("enddateday",format.format(new Date()));
        }

        try {
            String jsonString = HttpUtils.doPost(Constants.OTS_URL + "/roomstate/querylist", paramMap);
            if(StringUtils.isEmpty(jsonString)){
                return new OtsRoomStateDto();
            }
            logger.info("====================init sales config job >> roomstate querylist:" + jsonString);
            JSONObject jsonObject = JsonUtils.parseObject(jsonString);
            JSONArray hotelListArray = jsonObject.getJSONArray("hotel");
            if (hotelListArray.isEmpty()) {
                return result;
            }
            JSONObject hotelJson = hotelListArray.getJSONObject(0);
            String hotelName = hotelJson.getString("hotelname");
            result.setHotelName(hotelName);

            //roomType
             JSONArray roomTypeListArray = hotelJson.getJSONArray("roomtype");
            if (roomTypeListArray.isEmpty()) {
                return result;
            }
            JSONObject roomTypeJson = roomTypeListArray.getJSONObject(0);

            BigDecimal price = roomTypeJson.getBigDecimal("roomtypeprice");
            BigDecimal pmsPrice = roomTypeJson.getBigDecimal("roomtypepmsprice");
            result.setPrice(price);
            result.setPmsPrice(pmsPrice);

            //rooms
            JSONArray roomArray = roomTypeJson.getJSONArray("rooms");
            int size = roomArray.size();

            List<Integer> roomIdList = new ArrayList<Integer>();
            for (int i = 0 ; i < size; i++) {
                JSONObject roomJson = roomArray.getJSONObject(i);
                Integer roomId = roomJson.getInteger("roomid");
                String roomStatus = roomJson.getString("roomstatus");
                if ("vc".equals(roomStatus)) {
                    roomIdList.add(roomId);
                }
            }
            result.setRoomIdList(roomIdList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new OtsRoomStateDto();
    }
}
