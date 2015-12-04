package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.EHotelPicDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.EHotelPic;

import java.util.List;

@MyBatisRepository
public interface HotelPicMapper {
    public List<EHotelPic> queryEHotelPic(EHotelPicDto bean);
    public Integer countEHotelPic(EHotelPicDto bean);
    public EHotelPic getEHotelPicById(Long id);
    public Integer deleteEHotelPicById(Long id);
    public Integer insertEHotelPic(EHotelPicDto bean);
    public Integer updateEHotelPicById(EHotelPicDto bean);
    public Integer updateEHotelPicByHotelId(EHotelPicDto bean);
}
