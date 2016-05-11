package com.mk.hotel.hotelinfo.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chenqi on 16/5/10.
 */
public class HotelServiceImplTest extends BaseTest{
    @Autowired
    private HotelService hotelService;

    @Test
    public void findByIdTest(){
        Long hotelId = 1L;
        HotelDto hotel = hotelService.findById(hotelId);
        Assert.assertNotNull(hotel);
        Assert.assertEquals(new Long(1) , hotel.getId());
    }
}