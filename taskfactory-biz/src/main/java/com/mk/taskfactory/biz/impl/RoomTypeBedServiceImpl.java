package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomTypeBedService;
import com.mk.taskfactory.api.dtos.TRoomTypeBedDto;
import com.mk.taskfactory.biz.mapper.ots.RoomTypeBedMapper;
import com.mk.taskfactory.model.TRoomTypeBed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RoomTypeBedServiceImpl implements RoomTypeBedService {

    private static Logger logger = LoggerFactory.getLogger(RoomTypeBedServiceImpl.class);
    @Autowired
    private RoomTypeBedMapper roomTypeBedMapper;

    public List<TRoomTypeBedDto> queryParams(TRoomTypeBedDto dto) {

        List<TRoomTypeBed> bedList = this.roomTypeBedMapper.queryParams(dto);
        List<TRoomTypeBedDto> resultList = new ArrayList<TRoomTypeBedDto>();
        for (TRoomTypeBed bed : bedList) {
            TRoomTypeBedDto bedDto = new TRoomTypeBedDto();
            BeanUtils.copyProperties(bed, bedDto);
            resultList.add(bedDto);
        }
        return resultList;
    }

    public int saveRoomTypeBed(TRoomTypeBedDto dto) {
        return this.roomTypeBedMapper.saveRoomTypeBed(dto);
    }

    public int deleteById(Long id) {
        return this.roomTypeBedMapper.deleteById(id);
    }

    public int deleteByRoomTypeId(Long roomTypeId) {
        return this.roomTypeBedMapper.deleteByRoomTypeId(roomTypeId);
    }

    public int updateById(TRoomTypeBedDto dto) {
        return this.roomTypeBedMapper.updateById(dto);
    }


    public int createByRoomTypeId(Long oldRoomTypeId, Long newRoomTypeId) {
        Map<String,Long> param = new HashMap<String, Long>();
        param.put("newRoomTypeId", newRoomTypeId);
        param.put("roomTypeId",oldRoomTypeId);

        return this.roomTypeBedMapper.createByRoomTypeId(param);
    }
}
