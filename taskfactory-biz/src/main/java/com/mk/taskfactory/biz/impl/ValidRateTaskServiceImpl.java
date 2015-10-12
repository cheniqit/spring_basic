package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSaleConfigService;
import com.mk.taskfactory.api.RoomService;
import com.mk.taskfactory.api.RoomTypeService;
import com.mk.taskfactory.api.ValidRateTaskService;
import com.mk.taskfactory.api.dtos.TRoomDto;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigDto;
import com.mk.taskfactory.api.dtos.TRoomTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValidRateTaskServiceImpl implements ValidRateTaskService {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomSaleConfigService roomSaleConfigService;
    @Autowired
    private RoomTypeService roomTypeService;
    public void validRateTaskRun(){
        TRoomSaleConfigDto roomSaleConfigDto=new TRoomSaleConfigDto();
        List<TRoomSaleConfigDto> list=roomSaleConfigService.queryRoomSaleConfigByParams(roomSaleConfigDto);
        if (list==null){
            return;
        }
        Map<String,Object> saleRoomMap=getSaleRoom(list);
        List<TRoomDto>  saleRooms=(ArrayList)saleRoomMap.get("roomDtos");
        List<TRoomTypeDto>  roomTypes=(ArrayList)saleRoomMap.get("roomTypeDtos");
        for (TRoomTypeDto roomTypeDto:roomTypes){
            roomTypeService.
        }
        for (TRoomDto roomDto:saleRooms){

        }

    }
    public Map<String,Object> getSaleRoom(List<TRoomSaleConfigDto>  list){
        Map<Integer,TRoomDto> saleRooms=new HashMap<Integer, TRoomDto>();
        Map<Integer,TRoomTypeDto> roomTypeList=new HashMap<Integer, TRoomTypeDto>();

        for (TRoomSaleConfigDto roomSaleConfig:list){
            TRoomTypeDto roomTypeDto=new TRoomTypeDto();
            if (roomTypeList.get(roomSaleConfig.getRoomTypeId())==null){
                roomTypeDto.setId(roomSaleConfig.getRoomTypeId());
                roomTypeDto.setCost(roomSaleConfig.getSaleValue());
                roomTypeDto.setRoomNum(0);
                roomTypeList.put(roomSaleConfig.getRoomTypeId(),roomTypeDto);
            }else{
                roomTypeDto=roomTypeList.get(roomSaleConfig.getRoomTypeId());
            }
            int saleNum=roomTypeDto.getRoomNum();
            if (saleNum==roomSaleConfig.getNum()){
                break;
            }
            if(roomSaleConfig.getRoomId()!=null&&roomSaleConfig.getRoomId()!=0){
                TRoomDto room= roomService.findRoomsById(roomSaleConfig.getRoomId());
                if (room!=null&&"F".equals(room.getIsLock())){
                    saleRooms.put(room.getId(),room);
                    saleNum++;
                }
            }else{
                List<TRoomDto> rooms= roomService.findRoomsByRoomTypeId(roomSaleConfig.getRoomTypeId());

                for (TRoomDto room:rooms){
                    if (saleNum==roomSaleConfig.getNum()){
                        break;
                    }
                    if(saleRooms.get(room.getId())!=null){
                        continue;
                    }
                    if ("F".equals(room.getIsLock())){
                        saleRooms.put(room.getId(),room);
                        saleNum++;
                    }
                }
            }
            roomTypeList.put(roomSaleConfig.getRoomTypeId(), roomTypeDto);

        }
        List<TRoomDto> roomDtos=new ArrayList<TRoomDto>();
        for (Integer key : saleRooms.keySet()) {
            roomDtos.add(saleRooms.get(key));
        }
        List<TRoomTypeDto> roomTypeDtos=new ArrayList<TRoomTypeDto>();
        for (Integer key : roomTypeList.keySet()) {
            roomTypeDtos.add(roomTypeList.get(key));
        }
        Map<String,Object> rs=new HashMap<String, Object>(2);
        rs.put("roomDtos",roomDtos);
        rs.put("roomTypeDtos",roomTypeDtos);
        return  rs;
    }
}
