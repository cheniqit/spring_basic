package com.mk.taskfactory.biz.mapper;


import com.mk.taskfactory.api.dtos.TBasePriceDto;
import com.mk.taskfactory.biz.repository.MyBatisRepository;

@MyBatisRepository
public interface BasePriceMapper {

    public TBasePriceDto selectByPrimaryKey(Long id);

    public TBasePriceDto findByRoomtypeId(Long roomTypeId);

    public int saveBasePriceDto(TBasePriceDto dto);

    public int updateBasePriceDto(TBasePriceDto dto);
    public int deleteBasePriceByRoomType(Integer roomTypeId);

}
