package com.mk.hotel.hotelinfo.service.impl;

import com.mk.common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by chenqi on 16/5/16.
 */
public class HotelFacilityServiceImplTest extends BaseTest{
    @Autowired
    private HotelFacilityServiceImpl hotelFacilityService;
    @Test
    public void testMergeHotelFacility() throws Exception {
        hotelFacilityService.mergeHotelFacility();
    }
}