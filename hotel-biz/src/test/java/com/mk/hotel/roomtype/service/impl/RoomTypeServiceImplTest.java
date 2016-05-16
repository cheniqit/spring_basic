package com.mk.hotel.roomtype.service.impl;

import com.mk.common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by chenqi on 16/5/16.
 */
public class RoomTypeServiceImplTest extends BaseTest{
    @Autowired
    protected RoomTypeServiceImpl roomTypeService;

    @Test
    public void testMergeRoomType() throws Exception {
        roomTypeService.mergeRoomType();
    }

    @Test
    public void testMergeRoomTypePrice() throws Exception {
        roomTypeService.mergeRoomTypePrice();
    }

    @Test
    public void testMergeRoomTypeStock() throws Exception {
        roomTypeService.mergeRoomTypeStock();
    }
}