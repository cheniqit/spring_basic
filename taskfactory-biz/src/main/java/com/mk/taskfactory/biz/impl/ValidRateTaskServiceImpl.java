package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.*;
import com.mk.taskfactory.api.dtos.*;
import com.mk.taskfactory.biz.utils.DateUtils;
import com.mk.taskfactory.biz.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ValidRateTaskServiceImpl implements ValidRateTaskService {
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomSaleConfigService roomSaleConfigService;
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private RoomTypeInfoService roomTypeInfoService;
    @Autowired
    private RoomSettingService roomSettingService;
    @Autowired
    private RoomTypeFacilityService roomTypeFacilityService;
    @Autowired
    private RoomSaleService roomSaleService;
    private final String otsUrl="http://smlt-ots.imike.cn/ots/";
    public void validRateTaskRun(){
        TRoomSaleConfigDto roomSaleConfigDto=new TRoomSaleConfigDto();
        //��ȡ����ñ�����
        List<TRoomSaleConfigDto> list=roomSaleConfigService.queryRoomSaleConfigByParams(roomSaleConfigDto);
        if (list==null){
            return;
        }
        //��ȡ���ñ��ж�Ӧ��������ķ�����Ϣ
        Map<String,Object> saleRoomMap=getSaleRoom(list);
        //�õ����з��������������
        List<TRoomSaleDto>  saleRooms=(ArrayList)saleRoomMap.get("roomDtos");
        //�õ����з��������Ӧ�ķ���
        List<TRoomTypeDto>  roomTypes=(ArrayList)saleRoomMap.get("roomTypeDtos");
        //����roomTypeId����roomTypeId��Ӧ����
        Map<Integer,Integer> roomTypeMap=new HashMap<Integer, Integer>();
        //����ǰ�۸�
        Map<Integer,Double> roomTypePriceMap=new HashMap<Integer, Double>();

        for (TRoomTypeDto roomTypeDto:roomTypes){
            Integer newRoomTypeId=0;
            TRoomTypeDto roomTypeModel=roomTypeService.findTRoomTypeById(roomTypeDto.getId());

            //��ԭ�۸������
            roomTypePriceMap.put(roomTypeDto.getId(), roomTypeModel.getCost());
            roomTypeModel.setRoomNum(roomTypeDto.getRoomNum());
            roomTypeModel.setCost(roomTypeDto.getCost());
            roomTypeModel.setName(roomTypeDto.getName());
            //���Ʋ����������
            roomTypeService.saveTRoomType(roomTypeModel);
            newRoomTypeId=roomTypeModel.getId();
            if (newRoomTypeId==null){
                continue;
            }
            roomTypeModel.setRoomNum(-roomTypeDto.getRoomNum());
            roomTypeService.updatePlusRoomNum(roomTypeModel);
            //����roomTypeId����roomTypeId��Ӧ����
            roomTypeMap.put(roomTypeDto.getId(), newRoomTypeId);

            //�õ�����������Ϣ
            TRoomTypeInfoDto roomTypeInfo=roomTypeInfoService.findByRoomTypeId(roomTypeDto.getId());
            roomTypeInfo.setRoomTypeId(newRoomTypeId);
            //���Ʋ���������������Ϣ
            roomTypeInfoService.saveRoomTypeInfo(roomTypeInfo);
            //�õ����۶�Ӧ������Ϣ
            List<TRoomTypeFacilityDto> roomTypeFacilityDtos=roomTypeFacilityService.findByRoomTypeId(roomTypeDto.getId());
           for(TRoomTypeFacilityDto roomTypeFacilityDto:roomTypeFacilityDtos){
               roomTypeFacilityDto.setRoomTypeId(newRoomTypeId);
               roomTypeFacilityService.saveRoomSaleConfig(roomTypeFacilityDto);
           }

        }
        //ѭ�����������
        for (TRoomSaleDto roomDto:saleRooms){
            Integer newRoomTypeId=roomTypeMap.get(roomDto.getOldRoomTypeId());
            Integer oldRoomTypeId=roomDto.getOldRoomTypeId();
            if (newRoomTypeId==null){
                continue;
            }
            TRoomSettingDto roomSettingBean=new TRoomSettingDto();
            roomSettingBean.setRoomTypeId(oldRoomTypeId);
            roomSettingBean.setRoomNo(roomDto.getRoomNo());
            //ȡ�÷���������Ϣ
            TRoomSettingDto roomSetting=roomSettingService.selectByRoomTypeIdAndRoomNo(roomSettingBean);
            roomSetting.setRoomTypeId(newRoomTypeId);
            //���·���������Ϣ
            roomSettingService.updateTRoomSetting(roomSetting);
            //���·�����Ϣ
            TRoomDto room=roomService.findRoomsById(roomDto.getRoomId());
            room.setRoomTypeId(newRoomTypeId);
            roomService.saveTRoom(room);
            //��������ؼ۷�����Ϣ
            roomDto.setCreateDate(DateUtils.format_yMd(new Date()));
            roomDto.setCostPrice(roomTypePriceMap.get(oldRoomTypeId));
            roomDto.setRoomTypeId(newRoomTypeId);
            roomDto.setIsBack("F");
            roomSaleService.saveRoomSale(roomDto);
        }
        ServiceUtils.post_data(otsUrl+"/roomsale/saleBegin", "POST","");

    }
    public Map<String,Object> getSaleRoom(List<TRoomSaleConfigDto>  list){
        Map<Integer,TRoomSaleDto> saleRooms=new HashMap<Integer, TRoomSaleDto>();
        Map<Integer,TRoomTypeDto> roomTypeList=new HashMap<Integer, TRoomTypeDto>();
        //ѭ�����ñ���������
        for (TRoomSaleConfigDto roomSaleConfig:list){
            TRoomTypeDto roomTypeDto=new TRoomTypeDto();
            //������Ͳ�����map�У��򽫷���put��map��
            if (roomTypeList.get(roomSaleConfig.getRoomTypeId())==null){
                roomTypeDto.setId(roomSaleConfig.getRoomTypeId());
                roomTypeDto.setCost(roomSaleConfig.getSaleValue());
                roomTypeDto.setName(roomSaleConfig.getSaleName());
                roomTypeDto.setRoomNum(0);
                roomTypeList.put(roomSaleConfig.getRoomTypeId(),roomTypeDto);
            }else{
                roomTypeDto=roomTypeList.get(roomSaleConfig.getRoomTypeId());
            }
            //ȡ�õ�ǰ��������Ŀ����
            int saleNum=roomTypeDto.getRoomNum();
            //��������ļ���roomId������ֱ�ӽ���ӵ���б���
            if(roomSaleConfig.getRoomId()!=null&&roomSaleConfig.getRoomId()!=0){
                TRoomDto room= roomService.findRoomsById(roomSaleConfig.getRoomId());
                if (room!=null&&"F".equals(room.getIsLock())){//�жϷ����Ƿ�����
                    TRoomSaleDto roomSale=new TRoomSaleDto();
                    roomSale.setOldRoomTypeId(room.getRoomTypeId());
                    roomSale.setRoomId(room.getId());
                    roomSale.setRoomNo(room.getName());
                    roomSale.setPms(room.getPms());
                    roomSale.setSalePrice(roomSaleConfig.getSaleValue());
                    roomSale.setStartTime(roomSaleConfig.getStartTime());
                    roomSale.setEndTime(roomSaleConfig.getEndDate());
                    roomSale.setConfigId(roomSaleConfig.getId());
                    roomSale.setSaleName(roomSaleConfig.getSaleName());
                    roomSale.setSaleType(roomSaleConfig.getType());
                    saleRooms.put(room.getId(),roomSale);
                    saleNum++;
                }
                if (saleNum==roomSaleConfig.getNum()){
                    break;
                }
            }else{
                //������ȡ�ö�Ӧ�ķ�����Ϣ
                List<TRoomDto> rooms= roomService.findRoomsByRoomTypeId(roomSaleConfig.getRoomTypeId());
                for (TRoomDto room:rooms){
                    //���������Ϣ�Ѿ���ӵ�������б��򲻼������
                    if(saleRooms.get(room.getId())!=null){
                        continue;
                    }
                    if ("F".equals(room.getIsLock())){
                        TRoomSaleDto roomSale=new TRoomSaleDto();
                            roomSale.setOldRoomTypeId(room.getRoomTypeId());
                            roomSale.setRoomId(room.getId());
                            roomSale.setRoomNo(room.getName());
                            roomSale.setPms(room.getPms());
                            roomSale.setSalePrice(roomSaleConfig.getSaleValue());
                            roomSale.setStartTime(roomSaleConfig.getStartTime());
                            roomSale.setEndTime(roomSaleConfig.getEndDate());
                            roomSale.setConfigId(roomSaleConfig.getId());
                            roomSale.setSaleName(roomSaleConfig.getSaleName());
                            roomSale.setSaleType(roomSaleConfig.getType());
                            saleRooms.put(room.getId(),roomSale);
                        saleNum++;
                        if (saleNum==roomSaleConfig.getNum()){
                            break;
                        }
                    }
                }
            }
            roomTypeDto.setRoomNum(saleNum);
            //�����������map
            roomTypeList.put(roomSaleConfig.getRoomTypeId(), roomTypeDto);

        }
        List<TRoomSaleDto> roomDtos=new ArrayList<TRoomSaleDto>();
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
