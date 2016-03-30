package com.mk.taskfactory.biz.mapper.crawer;

import com.mk.taskfactory.api.dtos.crawer.TQunarHotelDto;
import com.mk.taskfactory.api.dtos.ht.QHotelRoomtypeDto;
import com.mk.taskfactory.model.crawer.TQunarHotel;
import com.mk.taskfactory.model.ht.QHotelRoomtype;

import java.util.List;

public interface TExRoomTypeImgMapper {
    public Integer getRoomtypeImg(QHotelRoomtypeDto bean);
}