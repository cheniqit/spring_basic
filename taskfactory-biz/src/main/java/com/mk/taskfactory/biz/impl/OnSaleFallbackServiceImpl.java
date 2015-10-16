package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.TRoomChangeTypeDto;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import com.mk.taskfactory.biz.utils.ServiceUtils;
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

    @Autowired
    private RoomTypeInfoService roomTypeInfoService;

    @Autowired
    private RoomSettingService roomSettingService;

    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;
    private final String otsUrl="http://smlt-ots.imike.cn/ots/";
    public void onSaleFallback() {

        //需要回退的结果
        List<TRoomSaleDto> roomSaleDtoList = this.roomSaleService.queryUnBackRoomSale();

        for (TRoomSaleDto roomSaleDto : roomSaleDtoList) {
            Integer roomTypeId = roomSaleDto.getRoomTypeId();
            Integer oldRoomTypeId = roomSaleDto.getOldRoomTypeId();

            if (null == roomTypeId || null == oldRoomTypeId) {
                //TODO log
                continue;
            }

            //TODO LOG roomTypeId oldRoomTypeId
            /*
             *（1）根据t_room_sale roomtypeid,count和old_roomtypeid 将t_roomtype 中的roomNum还回去
             */
            this.updatePlusRoomNum(roomTypeId, oldRoomTypeId);

            /*
             *（2）根据t_room_sale roomtypeid和old_roomtypeid update t_room中的roomtypeid为old_roomtypeid
             */
            this.updateRoomType(roomTypeId, oldRoomTypeId);

            /*
             *（3）根据t_room_sale roomtypeid删除表t_roomtype_info中where roomtypeid=${roomtypeid}中数据
             */
            this.roomTypeInfoService.deleteByRoomType(roomTypeId);

            /*
             *（4）根据t_room_sale roomtypeid roomNo  还原t_room_setting中where roomtypeid=${roomtypeid}中数据为oldRoomTypeId
             */
            this.updateRoomSetting(roomTypeId, oldRoomTypeId);

            /*
             *（5）根据t_room_sale roomtypeid删除表t_roomtype_facilit中where roomtypeid=${roomtypeid}中数据
             */
            this.roomTypeFacilityService.deleteByRoomType(roomTypeId);

             /*
             *（5）根据t_room_sale roomtypeid删除表t_roomtype_facilit中where roomtypeid=${roomtypeid}中数据
             */
            this.roomTypeService.delTRoomTypeById(roomTypeId);
            ServiceUtils.post_data(otsUrl + "/roomsale/saleBegin", "POST", "");
            //设置更新完
            TRoomSaleDto dto = new TRoomSaleDto();
            dto.setId(roomSaleDto.getId());
            dto.setIsBack("T");
            this.roomSaleService.updateRoomSaleBack(dto);
        }
    }

    /**
     * 根据t_room_sale roomtypeid和old_roomtypeid update t_room中的roomtypeid为old_roomtypeid
     * @param roomTypeId
     * @param oldRoomTypeId
     */
    private void updateRoomType(Integer roomTypeId, Integer oldRoomTypeId) {
        TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
        roomChangeTypeDto.setRoomTypeId(roomTypeId);
        roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
        this.roomService.updateRoomTypeByRoomType(roomChangeTypeDto);
    }

    /**
     * 根据t_room_sale roomtypeid,count和old_roomtypeid 将t_roomtype 中的roomNum还回去
     * @param roomTypeId
     * @param oldRoomTypeId
     */
    private void updatePlusRoomNum(Integer roomTypeId, Integer oldRoomTypeId) {
        int roomCount = this.roomService.countRoomByRoomType(roomTypeId);
        if (roomCount > 0) {
            TRoomTypeDto roomTypeDto = new TRoomTypeDto();
            roomTypeDto.setId(oldRoomTypeId);
            roomTypeDto.setRoomNum(roomCount);

            this.roomTypeService.updatePlusRoomNum(roomTypeDto);
        }
    }
    /**
     * 根据t_room_sale roomtypeid,count和old_roomtypeid 将t_roomtype 中的roomNum还回去
     * @param roomTypeId
     * @param oldRoomTypeId
     */
    private void updateRoomSetting(Integer roomTypeId, Integer oldRoomTypeId) {
        TRoomChangeTypeDto roomChangeTypeDto = new TRoomChangeTypeDto();
        roomChangeTypeDto.setRoomTypeId(roomTypeId);
        roomChangeTypeDto.setOldRoomTypeId(oldRoomTypeId);
        this.roomSettingService.updateTRoomSettingByRoomTypeId(roomChangeTypeDto);

    }
}
