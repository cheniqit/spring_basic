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

        //��Ҫ���˵Ľ��
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
             *��1������t_room_sale roomtypeid,count��old_roomtypeid ��t_roomtype �е�roomNum����ȥ
             */
            this.updatePlusRoomNum(roomTypeId, oldRoomTypeId);

            /*
             *��2������t_room_sale roomtypeid��old_roomtypeid update t_room�е�roomtypeidΪold_roomtypeid
             */
            this.updateRoomType(roomTypeId, oldRoomTypeId);

            /*
             *��3������t_room_sale roomtypeidɾ����t_roomtype_info��where roomtypeid=${roomtypeid}������
             */
            this.roomTypeInfoService.deleteByRoomType(roomTypeId);

            /*
             *��4������t_room_sale roomtypeid roomNo  ��ԭt_room_setting��where roomtypeid=${roomtypeid}������ΪoldRoomTypeId
             */
            this.updateRoomSetting(roomTypeId, oldRoomTypeId);

            /*
             *��5������t_room_sale roomtypeidɾ����t_roomtype_facilit��where roomtypeid=${roomtypeid}������
             */
            this.roomTypeFacilityService.deleteByRoomType(roomTypeId);

             /*
             *��5������t_room_sale roomtypeidɾ����t_roomtype_facilit��where roomtypeid=${roomtypeid}������
             */
            this.roomTypeService.delTRoomTypeById(roomTypeId);
            ServiceUtils.post_data(otsUrl + "/roomsale/saleBegin", "POST", "");
            //���ø�����
            TRoomSaleDto dto = new TRoomSaleDto();
            dto.setId(roomSaleDto.getId());
            dto.setIsBack("T");
            this.roomSaleService.updateRoomSaleBack(dto);
        }
    }

    /**
     * ����t_room_sale roomtypeid��old_roomtypeid update t_room�е�roomtypeidΪold_roomtypeid
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
     * ����t_room_sale roomtypeid,count��old_roomtypeid ��t_roomtype �е�roomNum����ȥ
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
     * ����t_room_sale roomtypeid,count��old_roomtypeid ��t_roomtype �е�roomNum����ȥ
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
