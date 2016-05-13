package com.mk.hotel.roomtype.service.impl;

import com.mk.framework.excepiton.MyException;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.model.RoomTypeExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    @Autowired
    private HotelService hotelService;
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

    public int update(RoomTypeDto roomTypeDto) {
        if (null == roomTypeDto) {
            throw new MyException("-99", "-99", "roomTypeDto 不可为空");
        }

        RoomType roomType = new RoomType();
        BeanUtils.copyProperties(roomTypeDto, roomType);

        return this.roomTypeMapper.updateByPrimaryKey(roomType);

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

            return this.roomTypeMapper.updateByPrimaryKeySelective(dbRoomType);
        }
    }
}
