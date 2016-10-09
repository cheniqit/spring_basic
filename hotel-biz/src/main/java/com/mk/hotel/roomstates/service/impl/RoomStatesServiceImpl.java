package com.mk.hotel.roomstates.service.impl;

import com.mk.framework.DateUtils;
import com.mk.hotel.common.Constant;
import com.mk.hotel.message.MsgProducer;
import com.mk.hotel.roomstates.IRoomStatesService;
import com.mk.hotel.roomstates.dto.RoomStatesDto;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeStockService;
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
    private RoomTypePriceService roomTypePriceService;

    @Autowired
    private RoomTypeStockService roomTypeStockService;

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
    public int updatePriceAndStock(Long roomTypeId, Date startDate, Date endDate, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, BigDecimal totalStock) {

        int i = 0;
        if (null != settlePrice) {
            i += this.updatePrice(roomTypeId, startDate, endDate, marketPrice, salePrice, settlePrice);
        }

        if (null != totalStock) {
            i += this.updateStock(roomTypeId, startDate, endDate, totalStock);
        }

        return i;
    }

    @Override
    public int updatePrice(Long roomTypeId, Date startDate, Date endDate, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice) {

        //TODO push message
        msgProducer.sendMsg(Constant.TOPIC_ROOMTYPE_PRICE, "test");

        return 0;
    }

    @Override
    public int updateStock(Long roomTypeId, Date startDate, Date endDate, BigDecimal totalStock) {

        //TODO push message
        return 0;
    }
}
