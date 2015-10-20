package com.mk.taskfactory.biz.impl;

import com.mk.taskfactory.api.RoomSaleService;
import com.mk.taskfactory.api.dtos.RoomSaleToOtsDto;
import com.mk.taskfactory.api.dtos.TRoomSaleConfigInfoDto;
import com.mk.taskfactory.api.dtos.TRoomSaleDto;
import com.mk.taskfactory.biz.mapper.RoomSaleConfigInfoMapper;
import com.mk.taskfactory.biz.mapper.RoomSaleMapper;
import com.mk.taskfactory.model.TRoomSale;
import com.mk.taskfactory.model.TRoomSaleConfigInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.util.*;

@Service
public class RoomSaleServiceImpl implements RoomSaleService {

    @Autowired
    private RoomSaleMapper roomSaleMapper;
    @Autowired
    private RoomSaleConfigInfoMapper roomSaleConfigInfoMapper;

    @Override
    public List<TRoomSaleDto> queryYesterdayRoomSale() {
        List<TRoomSale> roomSaleList = this.roomSaleMapper.queryYesterdayRoomSale();

        List<TRoomSaleDto> roomSaleDtoList = new ArrayList<TRoomSaleDto>();

        for (TRoomSale roomSale : roomSaleList) {
            TRoomSaleDto dto = new TRoomSaleDto();

            //copy
            BeanUtils.copyProperties(roomSale, dto);
            roomSaleDtoList.add(dto);
        }

        return roomSaleDtoList;
    }

    @Override
    public List<TRoomSaleDto> queryUnBackRoomSale() {
        List<TRoomSale> roomSaleList = this.roomSaleMapper.queryUnBackRoomSale();

        List<TRoomSaleDto> roomSaleDtoList = new ArrayList<TRoomSaleDto>();

        for (TRoomSale roomSale : roomSaleList) {
            TRoomSaleDto dto = new TRoomSaleDto();

            //copy
            BeanUtils.copyProperties(roomSale, dto);
            roomSaleDtoList.add(dto);
        }

        return roomSaleDtoList;
    }

    @Override
    public void saveRoomSale(TRoomSaleDto roomSaleDto) {
        if (null != roomSaleDto) {
            this.roomSaleMapper.saveRoomSale(roomSaleDto);
        }
    }

    @Override
    public void updateRoomSaleBack(TRoomSaleDto roomSaleDto) {
        if (null != roomSaleDto && null != roomSaleDto.getId() && StringUtil.isNotEmpty(roomSaleDto.getIsBack())) {
            this.roomSaleMapper.updateRoomSaleBack(roomSaleDto);
        }
    }

    public List<RoomSaleToOtsDto> querySaleRoom(TRoomSaleDto bean) {
        bean.setIsBack("F");
        List<TRoomSale> roomSaleList = this.roomSaleMapper.queryRoomSale(bean);
        TRoomSaleConfigInfoDto dto = new TRoomSaleConfigInfoDto();
        List<TRoomSaleConfigInfo> configInfos = roomSaleConfigInfoMapper.queryRoomSaleConfigInfoList(dto);
        Map<Integer,TRoomSaleConfigInfo> configInfoMap=new HashMap<Integer, TRoomSaleConfigInfo>();
        for (TRoomSaleConfigInfo configInfo:configInfos){
            configInfoMap.put(configInfo.getSaleTypeId(),configInfo);
        }
        List<RoomSaleToOtsDto> roomSaleToOtsDtoList = new ArrayList<RoomSaleToOtsDto>();
        if (roomSaleList.isEmpty()){
            return null;
        }
        for (TRoomSale roomSale : roomSaleList) {
            TRoomSaleConfigInfo configInfo=configInfoMap.get(roomSale.getSaleType());
            RoomSaleToOtsDto dto = buildTRoomSaleConfigDto(roomSale,configInfo);
            roomSaleToOtsDtoList.add(dto);
        }

        return roomSaleToOtsDtoList;
    }
    public RoomSaleToOtsDto getHotelSaleByHotelId(Integer hotelId) {
        TRoomSale roomSale = roomSaleMapper.getHotelSaleByHotelId(hotelId);
        TRoomSaleConfigInfoDto dto = new TRoomSaleConfigInfoDto();
        List<TRoomSaleConfigInfo> configInfos = roomSaleConfigInfoMapper.queryRoomSaleConfigInfoList(dto);
        Map<Integer,TRoomSaleConfigInfo> configInfoMap=new HashMap<Integer, TRoomSaleConfigInfo>();
        for (TRoomSaleConfigInfo configInfo:configInfos){
            configInfoMap.put(configInfo.getSaleTypeId(),configInfo);
        }
        if (roomSale==null){
            return null;
        }
        TRoomSaleConfigInfo configInfo=configInfoMap.get(roomSale.getSaleType());
        return buildTRoomSaleConfigDto(roomSale,configInfo);

    }

    private RoomSaleToOtsDto buildTRoomSaleConfigDto(TRoomSale roomSale,TRoomSaleConfigInfo configInfo) {
        RoomSaleToOtsDto dto = new RoomSaleToOtsDto();
        dto.setIsOnPromo("T");
        dto.setPromoText(configInfo.getSaleLabel());
        dto.setPromoTextColor(configInfo.getFontColor());
        dto.setPromoStartTime(roomSale.getStartTime());
        dto.setPromoEndTime(roomSale.getEndTime());
        dto.setSaleType(roomSale.getSaleType());
        dto.setSaleName(roomSale.getSaleName());
        dto.setSalePrice(roomSale.getSalePrice());
        dto.setRoomNo(roomSale.getRoomNo());
        dto.setRoomtypeid(roomSale.getOldRoomTypeId());
        dto.setUseDescribe(configInfo.getDescription());
        return dto;
    }

    public List<TRoomSaleDto> queryByConfigAndBack(String configId, String isBack){
        HashMap  hm = new HashMap();
        hm.put("configId", configId);
        hm.put("isBack", isBack);
        List<TRoomSale>  roomSaleList = this.roomSaleMapper.queryByConfigAndBack(hm);
        if(CollectionUtils.isEmpty(roomSaleList)){
            return null;
        }
        List<TRoomSaleDto> roomSaleDtoList = new ArrayList<TRoomSaleDto>();
        for (TRoomSale roomSale : roomSaleList) {
            TRoomSaleDto dto = new TRoomSaleDto();

            //copy
            BeanUtils.copyProperties(roomSale, dto);
            roomSaleDtoList.add(dto);
        }

       return  roomSaleDtoList;
    }

    public List<Integer>   queryByConfigGroup(Integer  configId,String  isBack){
        HashMap  hm = new HashMap();
        hm.put("configId", configId);
        hm.put("isBack", isBack);
        return  this.roomSaleMapper.queryByConfigGroup(hm);
    }


}
