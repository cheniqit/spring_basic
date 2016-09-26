package com.mk.hotel.roomtype.service.impl;


import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.framework.DateUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.framework.security.MD5;
import com.mk.hotel.common.enums.FacilityEnum;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.QiniuUtils;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelFanqieMappingMapper;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.hotelinfo.model.HotelFanqieMapping;
import com.mk.hotel.hotelinfo.model.HotelFanqieMappingExample;
import com.mk.hotel.remote.fanqielaile.hotel.json.FacilitiesMap;
import com.mk.hotel.remote.fanqielaile.hotel.json.ImgList;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus.RoomDetail;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus.RoomDetailList;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.bean.PushRoomType;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import com.mk.hotel.roomtype.enums.BedTypeEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import com.mk.hotel.roomtype.mapper.RoomTypeStockMapper;
import com.mk.hotel.roomtype.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/5/13.
 */

@Service
public class FanqielaileRoomTypeProxyService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Autowired
    private RoomTypePriceMapper roomTypePriceMapper;
    @Autowired
    private RoomTypeStockMapper roomTypeStockMapper;
    @Autowired
    private RoomTypeServiceImpl roomTypeService;
    @Autowired
    private RoomTypePriceService roomTypePriceService;
    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;
    @Autowired
    private RoomTypeStockService roomTypeStockService;
    @Autowired
    private HotelFanqieMappingMapper hotelFanqieMappingMapper;
    @Autowired
    private HotelMapper hotelMapper;

    private static Logger logger = LoggerFactory.getLogger(FanqielaileRoomTypeProxyService.class);

    public RoomType saveOrUpdateRoomType(Integer innId,Long hotelId, com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomType fanqieRoomType) {


        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andHotelIdEqualTo(hotelId).andFangIdEqualTo(fanqieRoomType.getRoomTypeId().longValue());
        List<RoomType> dbRoomTypeList = this.roomTypeMapper.selectByExampleWithBLOBs(example);

        RoomType roomType = null;
        if (dbRoomTypeList.isEmpty()) {
            roomType = convertRoomType(hotelId,fanqieRoomType, null);
            this.roomTypeMapper.insert(roomType);
            saveOrUpdateRoomTypeFacility(innId.longValue(), roomType.getFangId(), roomType.getId(), fanqieRoomType.getFacilitiesMap());

        } else {
            RoomType dbRoomType = dbRoomTypeList.get(0);

            roomType = convertRoomType(hotelId,fanqieRoomType, dbRoomType);
            roomType.setId(dbRoomType.getId());
            roomType.setCreateBy(dbRoomType.getCreateBy());
            roomType.setCreateDate(dbRoomType.getCreateDate());

            this.roomTypeMapper.updateByPrimaryKeyWithBLOBs(roomType);
            saveOrUpdateRoomTypeFacility(innId.longValue(), roomType.getFangId(), roomType.getId(), fanqieRoomType.getFacilitiesMap());
        }

        roomTypeService.updateRedisRoomType(roomType.getId(), roomType, "FanqielaileRoomTypeProxyService.saveOrUpdateRoomType");

        return roomType;
    }

    private void saveOrUpdateRoomTypeFacility(Long fangHotelId, Long fangRoomTypeId, Long roomTypeID, List<FacilitiesMap> facilitiesMap) {
        List<RoomTypeFacilityDto> roomTypeFacilityDtoList = new ArrayList<RoomTypeFacilityDto>();

        for (FacilitiesMap facility : facilitiesMap) {
            FacilityEnum facilityEnum = FacilityEnum.getByFanqieType(facility.getValue());

            RoomTypeFacilityDto dto = new RoomTypeFacilityDto();
            dto.setFangHotelId(fangHotelId);
            dto.setFangRoomTypeId(fangRoomTypeId);
            dto.setRoomTypeId(roomTypeID);
            dto.setFacilityId(facilityEnum.getId().longValue());
            dto.setFacilityName(facilityEnum.getTitle());
            dto.setUpdateBy(Constant.SYSTEM_USER_NAME);
            dto.setUpdateDate(new Date());
            dto.setCreateBy(Constant.SYSTEM_USER_NAME);
            dto.setCreateDate(new Date());
            dto.setIsValid(ValidEnum.VALID.getCode());
            roomTypeFacilityDtoList.add(dto);
        }
        roomTypeFacilityService.saveOrUpdateByFangid(roomTypeFacilityDtoList, HotelSourceEnum.FANQIE);
    }



    private RoomType convertRoomType(Long hotelId, com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomType fanqieRoomType,RoomType dbRoomType){
        //pic
        String picSign = this.calcPicSign(fanqieRoomType.getImgList());
        String pic = null;
        if (null != picSign && null != dbRoomType && picSign.equals(dbRoomType.getPicsSign())) {
            //签名相同不转换
            pic = dbRoomType.getRoomTypePics();
        } else {
            //其他情况
            pic = this.processPic(fanqieRoomType.getImgList());
        }

        //hasBreakfast
        boolean hasBreakfast = hasBreakfast(fanqieRoomType.getFacilitiesMap());

        //
        RoomType roomType = new RoomType();

        //
        String strArea = fanqieRoomType.getRoomArea();
        if(StringUtils.isNotBlank(strArea)) {
            try {
                roomType.setArea(new BigDecimal(strArea).intValue());
            } catch (Exception e) {
                Cat.logError(e);
            }
        }

        //大床1, 双床2,大/双床3, 单人床4,圆床 5,三张床 6, 床位  7,  榻榻米  8,
        String strBedType = fanqieRoomType.getBedTypeValue();
        if(StringUtils.isNotBlank(strBedType)){
            BedTypeEnum bedTypeEnum = BedTypeEnum.getByFanqieType(strBedType);
            roomType.setBedType(bedTypeEnum.getId());
        }

        Integer bedSize = fanqieRoomType.getBedWid();
        if (null == bedSize) {
            roomType.setBedSize(null);
        } else {
            if(bedSize > 1000) {
                roomType.setBedSize(String.valueOf(bedSize / 1000f));
            } else {
                roomType.setBedSize(String.valueOf(bedSize / 100f));
            }
        }

        //0、无早；1、含早
        if (hasBreakfast) {
            roomType.setBreakfast(1);
        } else {
            roomType.setBreakfast(0);
        }
        roomType.setFangId(fanqieRoomType.getRoomTypeId().longValue());
        roomType.setHotelId(hotelId);
        roomType.setMaxRoomNum(8);
        roomType.setName(fanqieRoomType.getRoomTypeName());

        //1、预付
        roomType.setPrepay(1);
        roomType.setRoomNum(0);
        roomType.setPicsSign(picSign);
        roomType.setRoomTypePics(pic);

        roomType.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomType.setUpdateDate(new Date());
        roomType.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomType.setCreateDate(new Date());
        roomType.setIsValid(ValidEnum.VALID.getCode());
        return roomType;
    }

    private String calcPicSign(List<ImgList> imgList) {

        //全刷图片开关,当有值时返回null,全部刷新图片
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
            String key = jedis.get("fanqieFlushAllPic");
            if (StringUtils.isNotBlank(key)) {
                return null;
            }
        } catch (Exception e) {

        }finally {
            if (null != jedis) {
                jedis.close();
            }
        }

        //
        if (null == imgList || imgList.isEmpty()) {
            return null;
        }

        //
        StringBuilder pics = new StringBuilder();

        for (ImgList img : imgList) {
            pics.append(img.toString());
        }
        return MD5.MD5Encode(pics.toString());
    }

    private boolean hasBreakfast(List<FacilitiesMap> facilitiesMap) {
        for (FacilitiesMap facility : facilitiesMap) {
            FacilityEnum facilityEnum = FacilityEnum.getByFanqieType(facility.getValue());
            if (FacilityEnum.FREE_WIRELESS.equals(facilityEnum)) {
                return true;
            }
        }
        return false;
    }
    public void saveOrUpdateRoomDetail(Long hotelId, RoomDetailList roomDetailList) {

        Integer roomTypeId = roomDetailList.getRoomTypeId();
        RoomTypeDto roomTypeDto = this.roomTypeService.selectByFangId(roomTypeId.longValue(), hotelId);

        if (null == roomTypeDto) {
            throw new MyException("-99","-99","房型未找到");
        }

        //
        List<RoomDetail> roomDetails = roomDetailList.getRoomDetail();
        for (RoomDetail detail : roomDetails) {

            this.saveOrUpdateRoomTypePrice(roomTypeDto, detail);
            this.saveOrUpdateRoomTypeStock(roomTypeDto, detail);
        }
    }

    private void saveOrUpdateRoomTypePrice (RoomTypeDto roomTypeDto, RoomDetail detail) {
        RoomTypePrice roomTypePrice = this.convertRoomTypePrice(roomTypeDto.getId(), detail);

        //
        RoomTypePriceExample roomTypePriceExample = new RoomTypePriceExample();
        roomTypePriceExample.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId()).andDayEqualTo(roomTypePrice.getDay());
        List<RoomTypePrice> roomTypePriceList = this.roomTypePriceMapper.selectByExample(roomTypePriceExample);

        //redis
        this.roomTypePriceService.updateRedisPrice(
                roomTypeDto.getId(), roomTypeDto.getName(),
                roomTypePrice.getDay(), roomTypePrice.getPrice(), roomTypePrice.getCost(), roomTypePrice.getSettlePrice(),
                "FanqielaileRoomTypeProxyService.saveOrUpdateRoomDetail");

        //db
        if (roomTypePriceList.isEmpty()) {
            this.roomTypePriceMapper.insert(roomTypePrice);

        } else {
            RoomTypePrice dbPrice = roomTypePriceList.get(0);
            dbPrice.setPrice(roomTypePrice.getPrice());
            dbPrice.setCost(roomTypePrice.getCost());

            dbPrice.setUpdateDate(new Date());
            dbPrice.setUpdateBy("hotel_system");
            this.roomTypePriceMapper.updateByPrimaryKeySelective(dbPrice);
        }
    }
    private RoomTypePrice convertRoomTypePrice(Long roomTypeId, RoomDetail roomDetail) {

        //
        String strDate = roomDetail.getRoomDate();
        Date day = null;
        try {
            day = DateUtils.parseDate(strDate, DateUtils.FORMAT_DATE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //下单价格
        BigDecimal price = roomDetail.getRoomPrice();
        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);

        //原价格
        BigDecimal cost = roomDetail.getPriRoomPrice();
        cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);

        //
        RoomTypePrice roomTypePrice = new RoomTypePrice();
        roomTypePrice.setRoomTypeId(roomTypeId);
        roomTypePrice.setDay(day);
        roomTypePrice.setPrice(price);
        roomTypePrice.setCost(cost);
        roomTypePrice.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomTypePrice.setUpdateDate(new Date());
        roomTypePrice.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomTypePrice.setCreateDate(new Date());
        roomTypePrice.setIsValid(ValidEnum.VALID.getCode());
        return roomTypePrice;
    }
    private void saveOrUpdateRoomTypeStock (RoomTypeDto roomTypeDto, RoomDetail detail) {
        RoomTypeStock stock = this.convertRoomTypeStock(roomTypeDto.getId(), detail);

        //
        this.roomTypeStockService.lock(
                roomTypeDto.getHotelId().toString(),
                roomTypeDto.getId().toString(),
                stock.getDay(),
                RoomTypeStockService.LOCK_TIIME,
                RoomTypeStockService.MAX_WAIT_TIME_OUT);
        try {
            this.roomTypeStockService.updateRedisStock(
                    roomTypeDto.getHotelId(),
                    roomTypeDto.getId(),
                    stock.getDay(),
                    detail.getRoomNum(),
                    0);
        } finally {
            this.roomTypeStockService.unlock(
                    roomTypeDto.getHotelId().toString(),
                    roomTypeDto.getId().toString(),
                    stock.getDay());
        }

        //
        RoomTypeStockExample example = new RoomTypeStockExample();
        example.createCriteria().andRoomTypeIdEqualTo(roomTypeDto.getId()).andDayEqualTo(stock.getDay());
        List<RoomTypeStock> roomTypeStockList = this.roomTypeStockMapper.selectByExample(example);

        if (roomTypeStockList.isEmpty()) {
            this.roomTypeStockMapper.insert(stock);
        } else {
            RoomTypeStock dbStock = roomTypeStockList.get(0);
            dbStock.setNumber(stock.getNumber());
            dbStock.setUpdateBy(Constant.SYSTEM_USER_NAME);
            dbStock.setUpdateDate(new Date());

            this.roomTypeStockMapper.updateByPrimaryKeySelective(dbStock);
        }
    }
    private RoomTypeStock convertRoomTypeStock(Long roomTypeId, RoomDetail roomDetail) {

        //
        String strDate = roomDetail.getRoomDate();
        Date day = null;
        try {
            day = DateUtils.parseDate(strDate, DateUtils.FORMAT_DATE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //
        Integer roomNum = roomDetail.getRoomNum();

        //
        RoomTypeStock roomTypeStock = new RoomTypeStock();
        roomTypeStock.setDay(day);
        roomTypeStock.setRoomTypeId(roomTypeId);
        roomTypeStock.setNumber(roomNum.longValue());
        roomTypeStock.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomTypeStock.setUpdateDate(new Date());
        roomTypeStock.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomTypeStock.setCreateDate(new Date());
        roomTypeStock.setIsValid(ValidEnum.VALID.getCode());

        return roomTypeStock;
    }

    public String processPic(List<ImgList> imgList){
        String fanqieImgDomain = "http://img.fanqiele.com";

        if (null == imgList || imgList.isEmpty()) {
            return null;
        } else {
            //
            String coverImgUrl = "";
            List<String> notCoverUrlList = new ArrayList<String>();

            //
            for (ImgList img : imgList) {

                //
                String imgUrl = fanqieImgDomain + img.getImgUrl();

                //(imgList) isCover 是否封面 number (1封面,0 null不是封面)
                Integer isCover = img.getIsCover();
                if (Integer.valueOf(1).equals(isCover) && "".equals(coverImgUrl)) {
                    //
                    try {
                        coverImgUrl = QiniuUtils.upload(imgUrl, com.mk.hotel.common.Constant.QINIU_BUCKET);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String uploadImgUrl = null;
                    //
                    try {
                        uploadImgUrl = QiniuUtils.upload(imgUrl, com.mk.hotel.common.Constant.QINIU_BUCKET);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //
                    if (null != uploadImgUrl) {
                        notCoverUrlList.add(uploadImgUrl);
                    }
                }
            }

            //
            StringBuilder notCoverImgUrl = new StringBuilder();
            for (String imgUrl : notCoverUrlList) {
                //非空,追加前加","
                if (!"".equals(notCoverImgUrl.toString())) {
                    notCoverImgUrl.append(",");
                }

                notCoverImgUrl.append("{\"url\":\"").append(imgUrl).append("\"}");
            }

            //
            StringBuilder returnUrl = new StringBuilder()
                    .append("[{\"name\":\"def\",\"pic\":[{\"url\":\"")
                    .append(coverImgUrl)
                    .append("\"}]},{\"name\":\"bed\",\"pic\":[")
                    .append(notCoverImgUrl.toString())
                    .append("]},{\"name\":\"toilet\",\"pic\":[")
                    .append("]}]");
            return returnUrl.toString();
        }
    }

    public void updateFanqieRoomTypeInfo(PushRoomType pushRoomType) {
        List<PushRoomType.RoomType> pushRoomTypeList = pushRoomType.getRoomType();
        for(PushRoomType.RoomType proom : pushRoomTypeList){
            String accId = proom.getAccountId();
            HotelFanqieMappingExample example = new HotelFanqieMappingExample();
            example.createCriteria().andAccountIdEqualTo(Long.valueOf(accId));
            List<HotelFanqieMapping> hotelFanqieMappingList = hotelFanqieMappingMapper.selectByExample(example);
            if(CollectionUtils.isEmpty(hotelFanqieMappingList)){
                continue;
            }
            Long innId = hotelFanqieMappingList.get(0).getInnId();
            HotelExample hotelExample = new HotelExample();
            hotelExample.createCriteria().andFangIdEqualTo(innId);
            List<Hotel> hotelList = hotelMapper.selectByExample(hotelExample);
            if(CollectionUtils.isEmpty(hotelList)){
                continue;
            }
            RoomDetailList roomDetailList = new RoomDetailList();
            roomDetailList.setRoomTypeId(Integer.valueOf(proom.getRoomTypeId()));
            roomDetailList.setRoomTypeName(proom.getRoomTypeName());
            List<RoomDetail> roomDetails = convertRoomTypeDetails(proom.getRoomDetails());
            roomDetailList.setRoomDetail(roomDetails);
            saveOrUpdateRoomDetail(hotelList.get(0).getId(), roomDetailList);


        }

    }

    private List<RoomDetail> convertRoomTypeDetails(List<PushRoomType.RoomDetail> roomDetails) {
        if(CollectionUtils.isEmpty(roomDetails)){
            return null;
        }
        List<RoomDetail> roomDetailList = new ArrayList<RoomDetail>();
        for(PushRoomType.RoomDetail r : roomDetails){
            RoomDetail roomDetail = new RoomDetail();
            roomDetail.setRoomDate(r.getRoomDate());
            roomDetail.setRoomNum(Integer.valueOf(r.getRoomNum()));
            roomDetail.setPriRoomPrice(new BigDecimal(r.getPriRoomPrice()));
            roomDetail.setRoomPrice(new BigDecimal(r.getRoomPrice()));
            roomDetailList.add(roomDetail);
        }
        return roomDetailList;
    }
}
