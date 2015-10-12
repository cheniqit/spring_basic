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
        //��Ҫ���˵Ľ��
        List<TRoomSaleDto> roomSaleDtoList = this.roomSaleService.queryYesterdayRoomSale();

        for (TRoomSaleDto roomSaleDto : roomSaleDtoList) {
            Integer roomTypeId = roomSaleDto.getRoomTypeId();
            Integer oldRoomTypeId = roomSaleDto.getOldRoomTypeId();

            if (null == roomTypeId || null == oldRoomTypeId) {
                //TODO log
                continue;
            }

            /*
             *��1������t_room_sale roomtypeid,count��old_roomtypeid ��t_roomtype �е�roomNum����ȥ
             */
            int roomCount = this.roomService.countRoomByRoomType(roomTypeId);
            TRoomTypeDto roomTypeDto = new TRoomTypeDto();
            roomTypeDto.setId(oldRoomTypeId);
            roomTypeDto.setRoomNum(roomCount);

            this.roomTypeService.updatePlusRoomNum(roomTypeDto);

            /*
             *��2������t_room_sale roomtypeid��old_roomtypeid update t_room�е�roomtypeidΪold_roomtypeid
             */
            TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
            roomChangeTypeDto.setRoomTypeId(roomTypeId);
            roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
            this.roomService.updateRoomTypeByRoomType(roomChangeTypeDto);

            /*
             *��3������t_room_sale roomtypeidɾ����t_roomtype_info��where roomtypeid=${roomtypeid}������
             */

            /*
             *��4������t_room_sale roomtypeidɾ����t_room_setting��where roomtypeid=${roomtypeid}������
             */

            /*
             *��5������t_room_sale roomtypeidɾ����t_roomtype_facilit��where roomtypeid=${roomtypeid}������
             */

        }
    }
}
