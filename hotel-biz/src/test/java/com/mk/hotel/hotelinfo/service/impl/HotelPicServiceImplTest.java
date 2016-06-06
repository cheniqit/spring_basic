package com.mk.hotel.hotelinfo.service.impl;

import com.mk.common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chenqi on 16/5/31.
 */
public class HotelPicServiceImplTest extends BaseTest{
    @Autowired
    private HotelPicServiceImpl hotelPicService;

    @Test
    public void testSaveHotelPic() throws Exception {
        Long hotelId = 1016L;
        Long roomTypeId = null;
        String picType = "1";
        String url = "http://7xixbl.com1.z0.glb.clouddn.com/FqCPMp4v0ZsHNEXHctQQDPQq939q";
        String updateBy = "1";
        hotelPicService.saveHotelPic(hotelId, roomTypeId, picType, url, updateBy);
    }


    @Test
    public void testSaveHotelPicByRoomType() throws Exception {
        Long hotelId = 1000L;
        Long roomTypeId = 1001L;
        String picType = "1";
        String url = "http://7xixbl.com1.z0.glb.clouddn.com/FqCPMp4v0ZsHNEXHctQQDPQq939q";
        String updateBy = "1";
        hotelPicService.saveHotelPic(hotelId, roomTypeId, picType, url, updateBy);
    }

    @Test
    public void testDelPicture() throws Exception {
        Long hotelId = 1000L;
        Long roomTypeId = 1001L;
        String picType = "1";
        String url = "http://7xixbl.com1.z0.glb.clouddn.com/FqCPMp4v0ZsHNEXHctQQDPQq939q";
        String updateBy = "1";
        hotelPicService.delPicture(hotelId, roomTypeId, picType, url, updateBy);
    }
}