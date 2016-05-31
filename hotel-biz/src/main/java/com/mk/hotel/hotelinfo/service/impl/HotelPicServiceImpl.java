package com.mk.hotel.hotelinfo.service.impl;

import com.mk.framework.Constant;
import com.mk.framework.excepiton.MyException;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.hotelinfo.enums.HotelPicTypeEnum;
import com.mk.hotel.hotelinfo.mapper.HotelPicMapper;
import com.mk.hotel.hotelinfo.mapper.PicResourceMapper;
import com.mk.hotel.hotelinfo.model.HotelPic;
import com.mk.hotel.hotelinfo.model.PicResource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chenqi on 16/5/31.
 */
@Service
public class HotelPicServiceImpl {
    @Autowired
    private HotelPicMapper hotelPicMapper;
    @Autowired
    private PicResourceMapper picResourceMapper;

    public void saveHotelPic(Long hotelId,Long roomTypeId, String picType, String url, String fileName, String updateBy){
        if(hotelId == null){
            throw new MyException("hotelId参数为空");
        }
        if(StringUtils.isBlank(picType)){
            throw new MyException("picType参数为空");
        }
        HotelPicTypeEnum hotelPicTypeEnum = HotelPicTypeEnum.getHotelPicTypeEnumByCode(Integer.valueOf(picType));
        if(hotelPicTypeEnum == null){
            throw new MyException("picType参数错误");
        }
        if(hotelPicTypeEnum.getCode() == HotelPicTypeEnum.roomType.getCode() && roomTypeId == null){
            throw new MyException("上传房型图片时必传roomTypeId");
        }
        //保存图片
        PicResource picResource = convertPicResource(url, fileName, updateBy);
        picResourceMapper.insertSelective(picResource);
        //HotelPic hotelPic = convertHotelPic(picResource.getId() ,hotelId, roomTypeId, hotelPicTypeEnum, updateBy);
        //hotelPicMapper.insertSelective()
    }

    private PicResource convertPicResource(String url, String fileName, String updateBy) {
        PicResource picResource = new PicResource();
        picResource.setUrl(url);
        picResource.setName(fileName);
        picResource.setUpdateBy(updateBy);
        picResource.setUpdateDate(new Date());
        picResource.setCreateBy(updateBy);
        picResource.setCreateDate(new Date());
        picResource.setIsValid(ValidEnum.VALID.getCode());
        return picResource;
    }
}
