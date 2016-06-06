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
import org.apache.poi.ss.usermodel.Picture;
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

    public void saveHotelPic(Long hotelId, Long roomTypeId, String picType, String url, String updateBy){
        RoomType roomType = null;
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

        if(roomTypeId != null){
            roomType = roomTypeMapper.selectByPrimaryKey(roomTypeId);
            if(roomType == null){
                throw new MyException("上传房型图片时,roomTypeId参数错误");
            }
        }

        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        if(hotel == null || hotel.getId() ==null){
            throw new MyException("hotelId参数错误,没有找对对应的酒店信息");
        }
        if(StringUtils.isBlank(url)){
            throw new MyException("没有图片地址信息");
        }
        //将对应的hotel_pic, pic_resource表资源信息更新为无效
        updateOldResourceInValid(hotelId, hotelPicTypeEnum, updateBy);

        //保存图片
        String[] urlList = url.split(",");
        for(String u : urlList){
            PicResource picResource = convertPicResource(u, null, updateBy);
            picResourceMapper.insertSelective(picResource);
            HotelPic hotelPic = convertHotelPic(picResource.getId() ,hotelId, roomTypeId, hotelPicTypeEnum, updateBy);
            hotelPicMapper.insertSelective(hotelPic);

        }
        //刷新索引
        hotelService.updateRedisHotel(hotelId, hotel, "HotelPicServiceImpl.saveHotelPic");
        if(roomType != null){
            roomTypeService.updateRedisRoomType(roomTypeId, roomType, "HotelPicServiceImpl.saveHotelPic");
        }
        OtsInterface.initHotel(hotelId);
    }

    public void delPicture(Long hotelId, Long roomTypeId, String picType, String url, String updateBy) {
        RoomType roomType = null;
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
            throw new MyException("删除房型图片时必传roomTypeId");
        }else{
            if(roomTypeId != null){
                roomType = roomTypeMapper.selectByPrimaryKey(roomTypeId);
            }
            if(roomType == null){
                throw new MyException("删除房型图片时,roomTypeId参数错误");
            }
        }

        Hotel hotel = hotelMapper.selectByPrimaryKey(hotelId);
        if(hotel == null || hotel.getId() ==null){
            throw new MyException("hotelId参数错误,没有找对对应的酒店信息");
        }
        if(StringUtils.isBlank(url)){
            throw new MyException("没有图片地址信息");
        }
        String[] urlList = url.split(",");
        updateOldResourceInValid(hotelId, urlList, updateBy);
    }

    private void updateOldResourceInValid(Long hotelId, String[] urlList, String updateBy) {
        if(urlList == null || urlList.length == 0){
            return;
        }
        for(String u : urlList){
            PicResourceExample picExample = new PicResourceExample();
            picExample.createCriteria().andUrlEqualTo(u).andIsValidEqualTo(ValidEnum.VALID.getCode());
            List<PicResource> picResourceList = picResourceMapper.selectByExample(picExample);
            for(PicResource p : picResourceList){
                updatePicResourceToInValid(p);
                HotelPicExample hotelPicExample = new HotelPicExample();
                hotelPicExample.createCriteria().andHotelIdEqualTo(hotelId).andPicResourceIdEqualTo(p.getId());
                HotelPic hotelPic = new HotelPic();
                hotelPic.setUpdateBy(updateBy);
                hotelPic.setUpdateDate(new Date());
                hotelPic.setIsValid(ValidEnum.INVALID.getCode());
                hotelPicMapper.updateByExampleSelective(hotelPic, hotelPicExample);

            }
        }
    }

    public void updatePicResourceListToInValid(List<PicResource> picResourceList){
        if(CollectionUtils.isEmpty(picResourceList)){
            throw new MyException("更新图片信息失效时候参数错误");
        }
        for(PicResource p : picResourceList){
            updatePicResourceToInValid(p);
        }
    }

    public void updatePicResourceToInValid(PicResource picResource){
        if(picResource == null || picResource.getId() == null){
            throw new MyException("更新图片信息失效时候参数错误");
        }
        PicResourceExample picExample = new PicResourceExample();
        picExample.createCriteria().andIdEqualTo(picResource.getId());
        picResource.setIsValid(ValidEnum.INVALID.getCode());
        picResource.setUpdateDate(new Date());
        picResource.setIsValid(ValidEnum.INVALID.getCode());
        picResourceMapper.updateByExampleSelective(picResource, picExample);
    }

    private void updateOldResourceInValid(Long hotelId, HotelPicTypeEnum hotelPicTypeEnum, String updateBy) {
        HotelPicExample hotelPicExample = new HotelPicExample();
        hotelPicExample.createCriteria().andHotelIdEqualTo(hotelId).andPicTypeEqualTo(hotelPicTypeEnum.getCode()+"").andIsValidEqualTo(ValidEnum.VALID.getCode());
        List<HotelPic> hotelPicList = hotelPicMapper.selectByExample(hotelPicExample);
        HotelPic hotelPic = new HotelPic();
        hotelPic.setUpdateBy(updateBy);
        hotelPic.setUpdateDate(new Date());
        hotelPic.setIsValid(ValidEnum.INVALID.getCode());
        hotelPicMapper.updateByExampleSelective(hotelPic, hotelPicExample);

        for(HotelPic org : hotelPicList){
            PicResource picResource = new PicResource();
            picResource.setIsValid(ValidEnum.INVALID.getCode());

            PicResourceExample picExample = new PicResourceExample();
            picExample.createCriteria().andIdEqualTo(org.getId()).andIsValidEqualTo(ValidEnum.VALID.getCode());
            picResourceMapper.updateByExampleSelective(picResource, picExample);
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
