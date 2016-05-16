package com.mk.hotel.hotelinfo.bean;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenqi on 16/5/16.
 */
public class HotelLandMarkTest {

    @Test
    public void test(){
        List<HotelLandMark.AreaInfo> list = new ArrayList();

        list.add(HotelLandMark.instanceAreaInfo("1", 3F));
        list.add(HotelLandMark.instanceAreaInfo("2", 1F));
        list.add(HotelLandMark.instanceAreaInfo("3", 2F));

        Collections.sort(list, new HotelLandMark.AreaInfo());
        for(HotelLandMark.AreaInfo areaInfo : list){
            System.out.println(areaInfo.getDistance());
        }
        Assert.assertEquals("2", list.get(0).getAreaInfoName());
    }

}