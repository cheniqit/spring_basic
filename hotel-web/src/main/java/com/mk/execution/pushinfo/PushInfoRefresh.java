package com.mk.execution.pushinfo;

import com.mk.hotel.hotelinfo.service.HotelCopyService;
import com.mk.hotel.log.enums.LogPushTypeEnum;
import com.mk.hotel.log.model.LogPush;
import com.mk.hotel.roomtype.service.RoomTypeCopyService;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by 振涛 on 2016/2/18.
 */
class PushInfoRefresh implements Runnable {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PushInfoRefresh.class);

    private JobQueueMessage msg;

    PushInfoRefresh(JobQueueMessage msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        //IBaseIndexerService baseIndexerService = AppUtils.getBean(IBaseIndexerService.class);
        try {
            if (msg!= null && msg.pushInfo != null){
                String body  = msg.pushInfo.body;
                LogPushTypeEnum type = msg.pushInfo.type;
                try {
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (InterruptedException e1) {

                }

                HotelCopyService hotelCopyService = new HotelCopyService();

                RoomTypeCopyService roomTypeCopyService = new RoomTypeCopyService();
                switch (type) {
                    case hotelAll:
                        hotelCopyService.handleHotelAll(body);
                        break;
                    case hotelDelete:
                        hotelCopyService.handleHotelDel(body);
                        break;
                    case hotel:
                        hotelCopyService.handleHotelDetail(body);
                        break;
                    case hotelFacility:
                        hotelCopyService.handleHotelFacility(body);
                        break;
                    case roomType:
                        roomTypeCopyService.handleRoomType(body);
                        break;
                    case roomTypeDelete:
                        roomTypeCopyService.handleRoomTypeDel(body);
                        break;
                    case roomTypePrice:
                        roomTypeCopyService.handleRoomTypePrice(body);
                        break;
                    case hotelFanqie:
                        hotelCopyService.handleHotelFanqie(body);
                        break;

                    case roomTypeStatusFanqie:
                        hotelCopyService.handleRoomStatusFanqie(body);
                        break;
                    default:
                        //TODO
                }

                JobQueue.getInstance().rem(msg);
                LOGGER.info("hotelId:[{}] index refresh finished.", msg.pushInfo.body);
            }

        } catch (Exception e) {
            LOGGER.error("error: ", e);
        }
    }
}
