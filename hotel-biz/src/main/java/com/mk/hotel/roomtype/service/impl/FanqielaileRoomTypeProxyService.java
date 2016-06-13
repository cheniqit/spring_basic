package com.mk.hotel.roomtype.service.impl;


import com.dianping.cat.Cat;
import com.mk.framework.Constant;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.QiniuUtils;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.service.impl.HotelFacilityServiceImpl;
import com.mk.hotel.remote.fanqielaile.hotel.json.FacilitiesMap;
import com.mk.hotel.remote.fanqielaile.hotel.json.ImgList;
import com.mk.hotel.remote.pms.hotel.json.HotelTagResponse;
import com.mk.hotel.roomtype.RoomTypeFacilityService;
import com.mk.hotel.roomtype.dto.RoomTypeFacilityDto;
import com.mk.hotel.roomtype.enums.BedTypeEnum;
import com.mk.hotel.roomtype.enums.FanqieRoomTypeFacilityEnum;
import com.mk.hotel.roomtype.mapper.RoomTypeMapper;
import com.mk.hotel.roomtype.mapper.RoomTypePriceMapper;
import com.mk.hotel.roomtype.mapper.RoomTypeStockMapper;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.model.RoomTypeExample;
import com.mk.hotel.roomtype.model.RoomTypePrice;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
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
    private RoomTypePriceServiceImpl roomTypePriceService;
    @Autowired
    private RoomTypeStockServiceImpl roomTypeStockService;
    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;



    private static Logger logger = LoggerFactory.getLogger(FanqielaileRoomTypeProxyService.class);

    public RoomType saveOrUpdateRoomType(Integer fangHotelId,Long hotelId, com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomType fanqieRoomType) {
        RoomType roomType = convertRoomType(hotelId,fanqieRoomType);

        RoomTypeExample example = new RoomTypeExample();
        example.createCriteria().andHotelIdEqualTo(hotelId).andFangIdEqualTo(roomType.getFangId());
        List<RoomType> dbRoomTypeList = this.roomTypeMapper.selectByExample(example);

        if (dbRoomTypeList.isEmpty()) {
            this.roomTypeMapper.insert(roomType);
            saveOrUpdateRoomTypeFacility(fangHotelId.longValue(), roomType.getId(), fanqieRoomType.getFacilitiesMap());

        } else {
            RoomType dbRoomType = dbRoomTypeList.get(0);
            roomType.setId(dbRoomType.getId());
            roomType.setCreateBy(dbRoomType.getCreateBy());
            roomType.setCreateDate(dbRoomType.getCreateDate());

            this.roomTypeMapper.updateByPrimaryKeySelective(roomType);
            saveOrUpdateRoomTypeFacility(fangHotelId.longValue(), roomType.getId(), fanqieRoomType.getFacilitiesMap());
        }

        roomTypeService.updateRedisRoomType(roomType.getId(), roomType, "FanqielaileRoomTypeProxyService.saveOrUpdateRoomType");

        return roomType;
    }

    private void saveOrUpdateRoomTypeFacility(Long fangHotelId, Long fangRoomTypeId, List<FacilitiesMap> facilitiesMap) {
        List<RoomTypeFacilityDto> roomTypeFacilityDtoList = new ArrayList<RoomTypeFacilityDto>();
        for (FacilitiesMap faMap : facilitiesMap) {
            RoomTypeFacilityDto dto = new RoomTypeFacilityDto();
            dto.setFangHotelId(fangHotelId);
            dto.setFangRoomTypeId(fangRoomTypeId);
            dto.setFacilityId(faMap.getValue().longValue());
            dto.setFacilityName(faMap.getName());
            dto.setUpdateBy(Constant.SYSTEM_USER_NAME);
            dto.setUpdateDate(new Date());
            dto.setCreateBy(Constant.SYSTEM_USER_NAME);
            dto.setCreateDate(new Date());
            dto.setIsValid(ValidEnum.VALID.getCode());
            roomTypeFacilityDtoList.add(dto);
        }
        roomTypeFacilityService.saveOrUpdateByFangid(roomTypeFacilityDtoList, HotelSourceEnum.FANQIE);
    }



    private RoomType convertRoomType(Long hotelId, com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomType fanqieRoomType){


        String pic = processPic(fanqieRoomType.getImgList());


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

        roomType.setBedSize(String.valueOf(fanqieRoomType.getBedWid()));

        //TODO 待 设施解析
        roomType.setBreakfast(0);
        roomType.setFangId(fanqieRoomType.getRoomTypeId().longValue());
        roomType.setHotelId(hotelId);
        roomType.setMaxRoomNum(8);
        roomType.setName(fanqieRoomType.getRoomTypeName());

        //1、预付
        roomType.setPrepay(1);
        roomType.setRoomNum(0);
        roomType.setRoomTypePics(pic);


        roomType.setUpdateBy(Constant.SYSTEM_USER_NAME);
        roomType.setUpdateDate(new Date());
        roomType.setCreateBy(Constant.SYSTEM_USER_NAME);
        roomType.setCreateDate(new Date());
        roomType.setIsValid(ValidEnum.VALID.getCode());
        return roomType;
    }

//    private RoomTypePrice convertRoomTypePrice(Long roomTypeId, HotelPriceResponse.Priceinfos priceinfo) throws ParseException {
//        RoomTypePrice roomTypePrice = new RoomTypePrice();
//        roomTypePrice.setRoomTypeId(roomTypeId);
//        roomTypePrice.setDay(DateUtils.parseDate(priceinfo.getDate(), DateUtils.FORMAT_DATE));
//        BigDecimal price = new BigDecimal(priceinfo.getPrice());
//        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
//        roomTypePrice.setPrice(price);
//        BigDecimal cost = new BigDecimal(priceinfo.getCost());
//        cost = cost.setScale(2, BigDecimal.ROUND_HALF_UP);
//        roomTypePrice.setCost(cost);
//
//        roomTypePrice.setUpdateBy(Constant.SYSTEM_USER_NAME);
//        roomTypePrice.setUpdateDate(new Date());
//        roomTypePrice.setCreateBy(Constant.SYSTEM_USER_NAME);
//        roomTypePrice.setCreateDate(new Date());
//        roomTypePrice.setIsValid(ValidEnum.VALID.getCode());
//        return roomTypePrice;
//    }

    public String processPic(List<ImgList> imgList){
        String fanqieImgDomain = "http://img.fanqiele.com";

        if (null == imgList) {
            return "[{\"name\":\"def\",\"pic\":[{\"url\":\"\"}]},{\"name\":\"lobby\",\"pic\":[{\"url\":\"\"}]},{\"name\":\"mainHousing\",\"pic\":[{\"url\":\"\"}]}]";
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
                if (Integer.valueOf(1).equals(isCover)) {
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
                    .append("\"}]},{\"name\":\"lobby\",\"pic\":[{\"url\":\"\"}，{\"url\":\"\"}]},{\"name\":\"mainHousing\",\"pic\":[")
                    .append(notCoverImgUrl.toString())
                    .append("]}]");
            return returnUrl.toString();
        }
    }
}
