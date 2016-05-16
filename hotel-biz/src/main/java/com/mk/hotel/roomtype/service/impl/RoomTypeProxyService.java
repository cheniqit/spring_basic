package com.mk.hotel.roomtype.service.impl;

import com.mk.framework.Constant;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryDetailResponse;
import com.mk.hotel.remote.pms.hotel.json.HotelRoomTypeQueryResponse;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.model.RoomTypeExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/13.
 */

@Service
public class RoomTypeProxyService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    @Transactional
    public void saveRoomType(Hotel hotel, List<HotelRoomTypeQueryResponse.HotelRoomType> roomTypeList) {
        if(CollectionUtils.isEmpty(roomTypeList)){
            return;
        }
        for(HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType : roomTypeList){
            RoomTypeDto roomTypeDto = roomTypeService.selectByFangId(Long.valueOf(hotelRoomType.getId()+""));
            if(roomTypeDto == null || roomTypeDto.getId() == null){
                saveRoomType(hotel, hotelRoomType);
            }else{
                updateRoomType(hotel, hotelRoomType);
            }
        }
    }

    public void saveRoomType(Hotel hotel, HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType){
        RoomType roomType = convertRoomType(hotel, hotelRoomType);
        roomTypeMapper.insertSelective(roomType);
    }

    public void updateRoomType(Hotel hotel, HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType){
        RoomType roomType = convertRoomType(hotel, hotelRoomType);
        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andHotelIdEqualTo(hotel.getId());
        roomTypeMapper.updateByExampleSelective(roomType, example);
    }


    private RoomType convertRoomType(Hotel hotel, HotelRoomTypeQueryResponse.HotelRoomType hotelRoomType){
        RoomType roomType = new RoomType();
        roomType.setArea(hotelRoomType.getArea());
        if(StringUtils.isNotBlank(hotelRoomType.getBedtype())){
            roomType.setBedType(Integer.valueOf(hotelRoomType.getBedtype()));
        }

        roomType.setBedSize(hotelRoomType.getBedsize());
        if(StringUtils.isNotBlank(hotelRoomType.getBreakfast())) {
            roomType.setBreakfast(Integer.valueOf(hotelRoomType.getBreakfast()));
        }
        roomType.setFangId(Long.valueOf(hotelRoomType.getId()+""));
        roomType.setHotelId(hotel.getId());
        roomType.setMaxRoomNum(Integer.valueOf(hotelRoomType.getRoomnum()));
        roomType.setName(hotelRoomType.getName());
        if(StringUtils.isNotBlank(hotelRoomType.getPrepay())) {
            roomType.setPrepay(Integer.valueOf(hotelRoomType.getPrepay()));
        }
        roomType.setRoomNum(hotelRoomType.getRoomnum());
        roomType.setRoomTypePics(hotelRoomType.getRoomtypepics());


        roomType.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomType.setUpdateDate(new Date());
        roomType.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomType.setCreateDate(new Date());
        roomType.setIsValid(ValidEnum.VALID.getCode());
        return roomType;
    }
}
