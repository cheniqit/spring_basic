package com.mk.taskfactory.biz.mapper.ots;


import com.mk.taskfactory.api.dtos.EHotelPicDto;
import com.mk.taskfactory.api.dtos.EHotelPicResDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;
import com.mk.taskfactory.model.EHotelPic;
import com.mk.taskfactory.model.EHotelPicRes;

import java.util.List;

@MyBatisRepository
public interface HotelPicResMapper {
    public List<EHotelPicRes> queryEHotelPicRes(EHotelPicResDto bean);
    //public List<EHotelPicRes> queryEHotelPicResByJoinPic(EHotelPicResDto bean);
    public Integer countEHotelPicRes(EHotelPicResDto bean);
    public EHotelPicRes getEHotelPicResById(Long id);
    public Integer deleteEHotelPicResById(Long id);
    public Integer insertEHotelPicRes(EHotelPicResDto bean);
    public Integer updateEHotelPicResById(EHotelPicResDto bean);
    public Integer updateInvalidByHotelId (EHotelPicResDto bean);
}
