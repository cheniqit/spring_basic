package com.mk.taskfactory.biz.mapper.ods;

import com.common.BaseCpsTest;
import com.mk.taskfactory.model.ods.TRoomTypeOnlinePrice;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fisher.wu on 16/3/4.
 */
public class RoomTypeOnlinePriceMapperTest extends BaseCpsTest {
    @Autowired
    private RoomTypeOnlinePriceMapper roomTypeOnlinePriceMapper;

    @Test
    public void testInsert() {
        String hotelId = "1";
        String roomTypeId = "1";
        BigDecimal price = new BigDecimal("1");

        Integer id = roomTypeOnlinePriceMapper.insert(new TRoomTypeOnlinePrice(new BigInteger(hotelId), new BigInteger(roomTypeId), price));
        Assert.assertNotNull(id);
    }

    @Test
    public void testDelete(){
        String todayDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int num = roomTypeOnlinePriceMapper.deleteByCreateDate(todayDateStr);
        Assert.assertEquals(num, 213);
    }
}
