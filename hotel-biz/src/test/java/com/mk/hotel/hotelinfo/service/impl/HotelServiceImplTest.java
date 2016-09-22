package com.mk.hotel.hotelinfo.service.impl;

import com.mk.common.BaseTest;
import com.mk.framework.Constant;
import com.mk.hotel.hotelinfo.bean.HotelLandMark;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chenqi on 16/5/10.
 */
public class HotelServiceImplTest extends BaseTest{
    @Autowired
    private HotelServiceImpl hotelService;


    @Test
    public void findByIdTest(){
        Long hotelId = 1L;
        HotelDto hotel = hotelService.findById(hotelId);
        Assert.assertNotNull(hotel);
        Assert.assertEquals(new Long(1) , hotel.getId());
    }

    @Test
    public void testMergePmsHotel() throws Exception {
        hotelService.neverPmsHotel(1);
    }

    @Test
    public void testUpdateHotel() throws Exception {

    }

    @Test
    public void testSaveHotel() throws Exception {

    }

    @Test
    public void testGetHotelLandMark() throws Exception {
        HotelLandMark hotelLandMark = hotelService.getHotelLandMark(new Double("121.44610100") , new Double("31.28332200"), Constant.HOTEL_TO_HOT_AREA_DISTANCE);
    }
}