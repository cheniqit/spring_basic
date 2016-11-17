package com.mk.hotel.roomtype.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.json.JsonUtils;
import com.mk.framework.spring.AppUtils;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.json.roomtypeprice.PriceInfoJson;
import com.mk.hotel.roomtype.json.roomtypeprice.RoomTypePriceJson;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 16/6/6.
 */
public class RoomTypeCopyService {

    public void handleRoomType (String body) {

        //
        HotelService hotelService = AppUtils.getBean(HotelService.class);

        //
        RoomTypeService roomTypeService = AppUtils.getBean(RoomTypeService.class);

        Long fangHotelId = null;
        //
        List<com.mk.hotel.roomtype.json.roomtype.RoomTypeJson> roomTypeJsonList = new ArrayList<com.mk.hotel.roomtype.json.roomtype.RoomTypeJson>();
        try {
            //
            JSONArray bodyJson = JSON.parseArray(body);
            int bodySize = bodyJson.size();

            for (int i = 0; i < bodySize; i++) {
                String strRoomTypeJson = bodyJson.get(i).toString();
                //
                com.mk.hotel.roomtype.json.roomtype.RoomTypeJson roomTypeJson = JsonUtils.fromJson(strRoomTypeJson, com.mk.hotel.roomtype.json.roomtype.RoomTypeJson.class);
                roomTypeJsonList.add(roomTypeJson);

                //
                fangHotelId = roomTypeJson.getHotelid();
            }
        } catch (Exception e) {
            throw new MyException("格式错误");
        }

        //
        for (com.mk.hotel.roomtype.json.roomtype.RoomTypeJson roomTypeJson : roomTypeJsonList) {

            RoomTypeDto roomTypeDto = new RoomTypeDto();

            roomTypeDto.setFangHotelId(roomTypeJson.getHotelid());
            roomTypeDto.setFangId(roomTypeJson.getId());
            roomTypeDto.setName(roomTypeJson.getName());

            Integer intArea = null;
            try {
                intArea = new BigDecimal(roomTypeJson.getArea()).intValue();
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Cat.logError(e);
                throw new MyException("area格式错误");
            }

            roomTypeDto.setArea(intArea);

            String strBedType = roomTypeJson.getBedtype();
            if (StringUtils.isNotBlank(strBedType)) {
                try {
                    Integer bedType = Integer.valueOf(strBedType);
                    roomTypeDto.setBedType(bedType);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Cat.logError(e);
                    throw new MyException("BedType格式错误");
                }
            }

            roomTypeDto.setRoomNum(roomTypeJson.getRoomnum());
            roomTypeDto.setPrepay(roomTypeJson.getPrepay());
            roomTypeDto.setBreakfast(roomTypeJson.getBreakfast());

            //默认T,不考虑push状态
//            roomTypeDto.setStatus(roomTypeJson.getStatus());
            roomTypeDto.setIsValid("T");

            roomTypeDto.setRefund(roomTypeJson.getRefund());
            roomTypeDto.setMaxRoomNum(roomTypeJson.getMaxroomnum());
            roomTypeDto.setRoomTypePics(roomTypeJson.getRoomtypepics());

            roomTypeService.saveOrUpdateByFangId(roomTypeDto, HotelSourceEnum.LEZHU);
        }


        //
        HotelDto dbHotel = hotelService.findByFangId(fangHotelId, HotelSourceEnum.LEZHU);
        if (null != dbHotel) {
            //
            OtsInterface.initHotel(dbHotel.getId());
        }

    }

    public void handleRoomTypeDel(String body) {

        //
        HotelService hotelService = AppUtils.getBean(HotelService.class);

        //
        RoomTypeService roomTypeService = AppUtils.getBean(RoomTypeService.class);
        /*
        { 
            "hotelid":9999,
            "roomtypeid":"444,5555,333"
        }

         */
        try {
            //
            JSONObject bodyJson = JSONObject.parseObject(body);
            String strHotelId = bodyJson.get("hotelid").toString();
            String roomTypeId = bodyJson.get("roomtypeid").toString();

            Long hotelId = null;
            try {
                hotelId = Long.parseLong(strHotelId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException("hotelid 格式错误");
            }
            HotelDto hotelDto = hotelService.findByFangId(hotelId, HotelSourceEnum.LEZHU);
            if (null == hotelDto) {
                throw new MyException("hotel未找到");
            }

            //
            String[] ids = roomTypeId.split(",");

            List<Long> idList = new ArrayList<Long>();
            for (String strId : ids) {
                Long id = Long.parseLong(strId);
                idList.add(id);
            }

            //
            roomTypeService.deleteByHotelId(hotelDto.getId(), idList);

            //
            OtsInterface.initHotel(hotelDto.getId());

        } catch (Exception e) {
            throw new MyException("格式错误");
        }
    }

    public void handleRoomTypePrice(String body) {

        //
        HotelService hotelService = AppUtils.getBean(HotelService.class);

        //
        RoomTypePriceService roomTypePriceService = AppUtils.getBean(RoomTypePriceService.class);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //json
        RoomTypePriceJson roomTypePriceJson = null;
        try {
            //json
            roomTypePriceJson = JsonUtils.fromJson(body, RoomTypePriceJson.class);
        } catch (Exception e) {
            throw new MyException("格式错误");
        }

        //fang-hotelId
        Long fangHotelId = roomTypePriceJson.getHotelid();

        //roomTypeJsonList
        List<RoomTypePriceDto> roomTypePriceDtoList = new ArrayList<RoomTypePriceDto>();
        List<com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson> roomTypeJsonList = roomTypePriceJson.getRoomtypeprices();
        for (com.mk.hotel.roomtype.json.roomtypeprice.RoomTypeJson roomTypeJson : roomTypeJsonList) {
            //fang-roomTypeId
            Long fangRoomTypeId = roomTypeJson.getRoomtypeid();

            List<PriceInfoJson> priceInfoJsonList = roomTypeJson.getPriceinfos();
            for (PriceInfoJson priceInfoJson : priceInfoJsonList) {

                //day
                Date day = null;
                String strDay = priceInfoJson.getDate();
                if (StringUtils.isBlank(strDay)) {
                    continue;
                } else {
                    try {
                        day = format.parse(strDay);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }

                //cost
                BigDecimal cost = null;
                String strCost = priceInfoJson.getCost();
                if (StringUtils.isBlank(strCost)) {
                    continue;
                } else {
                    try {
                        cost = new BigDecimal(strCost);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }

                //price
                BigDecimal price = null;
                String strPrice = priceInfoJson.getPrice();
                if (StringUtils.isBlank(strPrice)) {
                    continue;
                } else {
                    try {
                        price = new BigDecimal(strPrice);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Cat.logError(e);
                        continue;
                    }
                }

                //dto
                RoomTypePriceDto roomTypePriceDto = new RoomTypePriceDto();
                roomTypePriceDto.setDay(day);
                roomTypePriceDto.setPrice(price);
                roomTypePriceDto.setCost(cost);
                roomTypePriceDto.setFangHotelId(fangHotelId);
                roomTypePriceDto.setFangRoomTypeId(fangRoomTypeId);
                roomTypePriceDtoList.add(roomTypePriceDto);
            }
        }

        roomTypePriceService.saveOrUpdateByFangId(roomTypePriceDtoList, HotelSourceEnum.LEZHU);

        //
        HotelDto dbHotel = hotelService.findByFangId(fangHotelId, HotelSourceEnum.LEZHU);
        if (null != dbHotel) {

            //
            OtsInterface.initHotel(dbHotel.getId());
        }

    }

}
