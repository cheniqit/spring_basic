package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.OnSaleFallbackService;
import com.mk.taskfactory.api.RoomSaleService;
import com.mk.taskfactory.api.RoomService;
import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.mapper.RoomSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnSaleFallbackServiceImpl implements OnSaleFallbackService {

    @Autowired
    private RoomSaleService roomSaleService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    public void onSaleFallback() {
        //需要回退的结果
        List<TRoomSaleDto> roomSaleDtoList = this.roomSaleService.queryYesterdayRoomSale();

        for (TRoomSaleDto roomSaleDto : roomSaleDtoList) {
            Integer roomTypeId = roomSaleDto.getRoomTypeId();
            Integer oldRoomTypeId = roomSaleDto.getOldRoomTypeId();

            if (null == roomTypeId || null == oldRoomTypeId) {
                //TODO log
                continue;
            }

            /*
             *（1）根据t_room_sale roomtypeid,count和old_roomtypeid 将t_roomtype 中的roomNum还回去
             */
            int roomCount = this.roomService.countRoomByRoomType(roomTypeId);
            TRoomTypeDto roomTypeDto = new TRoomTypeDto();
            roomTypeDto.setId(oldRoomTypeId);
            roomTypeDto.setRoomNum(roomCount);

            this.roomTypeService.updatePlusRoomNum(roomTypeDto);

            /*
             *（2）更具t_room_sale roomtypeid和old_roomtypeid update t_room中的roomtypeid为old_roomtypeid
             */
            TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
            roomChangeTypeDto.setRoomTypeId(roomTypeId);
            roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
            this.roomService.updateRoomTypeByRoomType(roomChangeTypeDto);

            /*
             *（3）更具t_room_sale roomtypeid删除表t_roomtype_info中where roomtypeid=${roomtypeid}中数据
             */

            /*
             *（4）更具t_room_sale roomtypeid删除表t_room_setting中where roomtypeid=${roomtypeid}中数据
             */

            /*
             *（5）更具t_room_sale roomtypeid删除表t_roomtype_facilit中where roomtypeid=${roomtypeid}中数据
             */

        }
    }
}
