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
        String oldUrl = "FqCPMp4v0ZsHNEXHctQQDPQq939q";
        Long hotelId = 1016L;
        Long roomTypeId = null;
        String picType = "1";
        String url = "http://7xixbl.com1.z0.glb.clouddn.com/FqCPMp4v0ZsHNEXHctQQDPQq939q";
        String fileName = "门头.jpg";
        String updateBy = "1";
        hotelPicService.saveHotelPic(oldUrl, hotelId, roomTypeId, picType, url, fileName, updateBy);
    }


    @Test
    public void testSaveHotelPicByUpdate() throws Exception {
        String oldUrl = "FqCPMp4v0ZsHNEXHctQQDPQq939q";
        Long hotelId = 1000L;
        Long roomTypeId = 1001L;
        String picType = "1";
        String url = "http://7xixbl.com1.z0.glb.clouddn.com/FqCPMp4v0ZsHNEXHctQQDPQq939q";
        String fileName = "门头.jpg";
        String updateBy = "1";
        hotelPicService.saveHotelPic(oldUrl, hotelId, roomTypeId, picType, url, fileName, updateBy);
    }
}