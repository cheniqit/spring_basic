package com.mk.execution.pushinfo;

import com.mk.framework.AppUtils;
import com.mk.hotel.hotelinfo.service.HotelCopyService;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.roomtype.RoomTypeService;
import com.mk.hotel.roomtype.service.RoomTypeCopyService;
import org.slf4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * Created by 振涛 on 2016/2/18.
 */
class ClearRefresh implements Runnable {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ClearRefresh.class);

    @Override
    public void run() {
        try {

            LOGGER.info("ClearRefresh running");
            RoomTypeService roomTypeService = AppUtils.getBean(RoomTypeService.class);

            //TODO
//            roomTypeService.clearStockAndPrice();

            LOGGER.info("ClearRefresh end");
        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }
    }
}
