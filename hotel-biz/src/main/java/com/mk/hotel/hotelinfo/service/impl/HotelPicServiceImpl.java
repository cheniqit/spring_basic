package com.mk.hotel.hotelinfo.service.impl;

import com.mk.framework.excepiton.MyException;
import com.mk.hotel.common.Constant;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.redisbean.Pic;
import com.mk.hotel.common.redisbean.PicList;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.enums.HotelPicTypeEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.mapper.HotelPicMapper;
import com.mk.hotel.hotelinfo.mapper.PicResourceMapper;
import com.mk.hotel.hotelinfo.model.*;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.service.impl.RoomTypeServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/31.
 */
@Service
public class HotelPicServiceImpl {
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private HotelPicMapper hotelPicMapper;
    @Autowired
    private PicResourceMapper picResourceMapper;
    @Autowired
    private HotelServiceImpl hotelService;
    @Autowired
    private RoomTypeServiceImpl roomTypeService;

    public void saveHotelPic(String oldUrl, Long hotelId, Long roomTypeId, String picType, String url, String fileName, String updateBy){
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
        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        if(hotel == null || hotel.getId() ==null){
            throw new MyException("hotelId参数错误,没有找对对应的酒店信息");
        }

        RoomType roomType = null;
        if(roomTypeId != null){
            roomType = roomTypeMapper.selectByPrimaryKey(roomTypeId);
        }
        //保存图片
        if(StringUtils.isNotBlank(oldUrl)){
            String convertUrl = convertUrl(oldUrl);
            updateOldInValid(hotelId, hotelPicTypeEnum, convertUrl);
        }
        PicResource picResource = convertPicResource(url, fileName, updateBy);
        picResourceMapper.insertSelective(picResource);
        HotelPic hotelPic = convertHotelPic(picResource.getId() ,hotelId, roomTypeId, hotelPicTypeEnum, updateBy);
        hotelPicMapper.insertSelective(hotelPic);
        //刷新索引
        hotelService.updateRedisHotel(hotelId, hotel, "HotelPicServiceImpl.saveHotelPic");
        if(roomType != null){
            roomTypeService.updateRedisRoomType(roomTypeId, roomType, "HotelPicServiceImpl.saveHotelPic");
        }
        OtsInterface.initHotel(hotelId);
    }

    private String convertUrl(String oldUrl) {
        return Constant.QINIU_DOWNLOAD_ADDRESS+ oldUrl;
    }

    private void updateOldInValid(Long hotelId, HotelPicTypeEnum hotelPicTypeEnum, String oldUrl) {
        PicResourceExample picExample = new PicResourceExample();
        picExample.createCriteria().andUrlEqualTo(oldUrl).andIsValidEqualTo(ValidEnum.VALID.getCode());
        List<PicResource> picResourceList = picResourceMapper.selectByExample(picExample);
        if(CollectionUtils.isEmpty(picResourceList)){
            throw new MyException("picResourceId参数错误,没有找到对应的picResource信息");
        }
        for(PicResource org : picResourceList){
            if(ValidEnum.INVALID.getCode().equals(org.getIsValid())){
                continue;
            }
            org.setIsValid(ValidEnum.INVALID.getCode());
            picResourceMapper.updateByPrimaryKey(org);
            HotelPicExample hotelPicExample = new HotelPicExample();
            hotelPicExample.createCriteria().andHotelIdEqualTo(hotelId).andPicResourceIdEqualTo(org.getId()).andIsValidEqualTo(ValidEnum.VALID.getCode());
            List<HotelPic> hotelPicList = hotelPicMapper.selectByExample(hotelPicExample);
            if(CollectionUtils.isEmpty(hotelPicList)){
                throw new MyException("picResourceId参数错误,没有找到对应的hotelPic信息");
            }
            for(HotelPic hp : hotelPicList){
                hp.setIsValid(ValidEnum.INVALID.getCode());
                hotelPicMapper.updateByPrimaryKey(hp);
            }
        }
    }

    public PicList replacePicList(Long hotelId, Long roomTypeId, PicList picList){
        HotelPicTypeEnum hotelPicTypeEnum = HotelPicTypeEnum.getHotelPicTypeEnumByPmsPicCode(picList.getName());
        if(hotelPicTypeEnum == null){
            return picList;
        }
        HotelPicExample hotelPicExample = new HotelPicExample();
        HotelPicExample.Criteria criteria = hotelPicExample.createCriteria();
        criteria.andPicTypeEqualTo(hotelPicTypeEnum.getCode()+"").andIsValidEqualTo(ValidEnum.VALID.getCode());
        if(hotelId != null){
            criteria.andHotelIdEqualTo(hotelId);
        }
        if(roomTypeId != null){
            criteria.andRoomTypeIdEqualTo(roomTypeId);
        }
        List<HotelPic> hotelPicList = hotelPicMapper.selectByExample(hotelPicExample);
        if(CollectionUtils.isEmpty(hotelPicList)){
            return picList;
        }
        PicList newPicList = new PicList();
        List<Pic> pics = new ArrayList<Pic>();
        newPicList.setName(hotelPicTypeEnum.getPmsPicCode());
        for(HotelPic hotelPic : hotelPicList){
            PicResourceExample picExample = new PicResourceExample();
            picExample.createCriteria().andIdEqualTo(hotelPic.getPicResourceId()).andIsValidEqualTo(ValidEnum.VALID.getCode());
            List<PicResource> picResourceList = picResourceMapper.selectByExample(picExample);
            for(PicResource picResource : picResourceList){
                Pic pic = new Pic();
                pic.setUrl(picResource.getUrl());
                pics.add(pic);
            }
        }
        newPicList.setPic(pics);
        return newPicList;
    }

    private HotelPic convertHotelPic(Long picResourceId, Long hotelId, Long roomTypeId, HotelPicTypeEnum hotelPicTypeEnum, String updateBy) {
        HotelPic hotelPic = new HotelPic();
        hotelPic.setHotelId(hotelId);
        hotelPic.setPicResourceId(picResourceId);
        hotelPic.setRoomTypeId(roomTypeId);
        hotelPic.setPicType(hotelPicTypeEnum.getCode()+"");

        hotelPic.setUpdateBy(updateBy);
        hotelPic.setUpdateDate(new Date());
        hotelPic.setCreateBy(updateBy);
        hotelPic.setCreateDate(new Date());
        hotelPic.setIsValid(ValidEnum.VALID.getCode());
        return hotelPic;
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
