package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.EHotelPicDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.EHotelPic;

import java.util.List;

@MyBatisRepository
public interface HotelPicResMapper {
    public List<EHotelPic> queryEHotelPicRes(EHotelPicDto bean);
    public List<EHotelPic> queryEHotelPicResByJoinPic(EHotelPicDto bean);
    public Integer countEHotelPicRes(EHotelPicDto bean);
    public EHotelPic getEHotelPicResById(Long id);
    public Integer deleteEHotelPicResById(Long id);
    public Integer insertEHotelPicRes(EHotelPicDto bean);
    public Integer updateEHotelPicResById(EHotelPicDto bean);
    public Integer updateInvalidByHotelId (EHotelPicDto bean);
}
