package com.mk.hotel.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenqi on 16/5/27.
 */
public class OtsInterfaceTest {

    @Test
    public void testInitHotel() throws Exception {

    }

    @Test
    public void testUpdateOrderStatusByPms() throws Exception {
        OtsInterface.updateOrderStatusByPms("1136704", 200);
    }
}