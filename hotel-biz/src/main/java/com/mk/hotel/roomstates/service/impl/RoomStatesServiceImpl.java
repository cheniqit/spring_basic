package com.mk.hotel.roomstates.service.impl;

import com.mk.framework.DateUtils;
import com.mk.hotel.message.MsgProducer;
import com.mk.hotel.roomstates.IRoomStatesService;
import com.mk.hotel.roomstates.dto.RoomStatesDto;
import com.mk.hotel.roomtypeprice.service.impl.RoomTypePriceServiceImpl;
import com.mk.hotel.roomtypestock.service.impl.RoomTypeStockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 16/10/9.
 */
@Service
public class RoomStatesServiceImpl implements IRoomStatesService {

    private static Logger logger = LoggerFactory.getLogger(RoomStatesServiceImpl.class);

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private RoomTypePriceServiceImpl roomTypePriceService;

    @Autowired
    private RoomTypeStockServiceImpl roomTypeStockService;

    @Override
    public List<RoomStatesDto> queryStates(Long roomTypeId, Date startDate, Date endDate) {
        List<RoomStatesDto> roomStatesDtoList = new ArrayList<RoomStatesDto>();
        if (null == roomTypeId || null == startDate) {
            return roomStatesDtoList;
        }

        if (null == endDate) {
            endDate = DateUtils.addDays(startDate, 30);
        }

        //
        Date[] dates = DateUtils.getStartEndDate(startDate, endDate);
        for (Date date : dates) {
            RoomStatesDto dto = new RoomStatesDto();
            roomStatesDtoList.add(dto);
        }

        return roomStatesDtoList;
    }

    @Override
    public int updatePriceAndStock(Long roomTypeId, Date startDate, Date endDate, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, BigDecimal totalStock, String operator) {

        int i = 0;
        if (null != settlePrice) {
            i += this.updatePrice(roomTypeId, startDate, endDate, marketPrice, salePrice, settlePrice, operator);
        }

        if (null != totalStock) {
            i += this.updateStock(roomTypeId, startDate, endDate, totalStock, operator);
        }

        return i;
    }

    @Override
    public int updatePrice(Long roomTypeId, Date startDate, Date endDate, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator) {
        int diffDay = DateUtils.diffDay(startDate, endDate);
        int result;
        if(diffDay == 0){
            roomTypePriceService.updateRoomTypePriceSpecialRule(roomTypeId, startDate, marketPrice, salePrice, settlePrice, operator);
            result = 1;
        }else{
            for(int i =0; i<=diffDay; i++){
                Date date = DateUtils.addDays(startDate, i);
                roomTypePriceService.updateRoomTypePriceSpecialRule(roomTypeId, date, marketPrice, salePrice, settlePrice, operator);
            }
            result = diffDay+1;
        }
        return result;
    }

    @Override
    public int updateStock(Long roomTypeId, Date startDate, Date endDate, BigDecimal totalStock, String operator) {
        int diffDay = DateUtils.diffDay(startDate, endDate);
        int result;
        if(diffDay == 0){
            roomTypeStockService.updateRoomTypeStockSpecialRule(roomTypeId, startDate, totalStock, operator);
            result = 1;
        }else{
            for(int i =0; i<=diffDay; i++){
                Date date = DateUtils.addDays(startDate, i);
                roomTypeStockService.updateRoomTypeStockSpecialRule(roomTypeId, date, totalStock, operator);
            }
            result = diffDay+1;
        }
        return result;
    }
}
