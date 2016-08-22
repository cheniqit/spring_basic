package com.mk.hotel.roomtype.service.impl;

import com.mk.hotel.roomtype.RoomTypeFullStockLogService;
import com.mk.hotel.roomtype.dto.RoomTypeFullStockLogDto;
import com.mk.hotel.roomtype.mapper.RoomTypeFullStockLogMapper;
import com.mk.hotel.roomtype.model.RoomTypeFullStockLog;
import com.mk.hotel.roomtype.model.RoomTypeFullStockLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class RoomTypeFullStockLogServiceImpl implements RoomTypeFullStockLogService {
    @Autowired
    private RoomTypeFullStockLogMapper roomTypeFullStockLogMapper;

    public int saveLog(RoomTypeFullStockLogDto logDto){

        if (null == logDto) {
            return -1;
        }

        //
        RoomTypeFullStockLog log = convertToDto(logDto);
        log.setCreateDate(new Date());
        log.setUpdateDate(new Date());

        return this.roomTypeFullStockLogMapper.insert(log);
    }

    public List<RoomTypeFullStockLogDto> queryByDay (Date day) {

        if (null == day) {
            return new ArrayList<RoomTypeFullStockLogDto>();
        }

        //
        RoomTypeFullStockLogExample logExample = new RoomTypeFullStockLogExample();
        logExample.createCriteria().andDayEqualTo(day);

        List<RoomTypeFullStockLog> logList = this.roomTypeFullStockLogMapper.selectByExample(logExample);

        //
        List<RoomTypeFullStockLogDto> dtoList = new ArrayList<RoomTypeFullStockLogDto>();

        for (RoomTypeFullStockLog log :  logList) {
            RoomTypeFullStockLogDto dto = convertToBean(log);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private RoomTypeFullStockLog convertToDto(RoomTypeFullStockLogDto logDto) {
        //
        RoomTypeFullStockLog log = new RoomTypeFullStockLog();
        log.setRoomTypeId(logDto.getRoomTypeId());
        log.setDay(logDto.getDay());
        log.setTotalNumber(logDto.getTotalNumber());
        log.setTotalPromoNumber(logDto.getTotalPromoNumber());
        log.setNormalNumber(logDto.getNormalNumber());
        log.setPromoNumber(logDto.getPromoNumber());

        log.setCreateBy(logDto.getCreateBy());
        log.setCreateDate(logDto.getCreateDate());
        log.setUpdateBy(logDto.getUpdateBy());
        log.setIsValid(logDto.getIsValid());

        return log;
    }

    private RoomTypeFullStockLogDto convertToBean (RoomTypeFullStockLog log) {
        RoomTypeFullStockLogDto dto = new RoomTypeFullStockLogDto();
        dto.setId(log.getId());
        dto.setRoomTypeId(log.getRoomTypeId());
        dto.setDay(log.getDay());
        dto.setTotalNumber(log.getTotalNumber());
        dto.setTotalPromoNumber(log.getTotalPromoNumber());
        dto.setNormalNumber(log.getNormalNumber());
        dto.setPromoNumber(log.getPromoNumber());
        dto.setCreateBy(log.getCreateBy());
        dto.setCreateDate(log.getCreateDate());
        dto.setUpdateBy(log.getUpdateBy());
        dto.setUpdateDate(log.getUpdateDate());
        dto.setIsValid(log.getIsValid());

        return dto;
    }

}
