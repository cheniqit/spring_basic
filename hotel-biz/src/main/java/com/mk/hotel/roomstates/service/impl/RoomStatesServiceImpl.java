package com.mk.hotel.roomstates.service.impl;

import com.mk.framework.DateUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.security.MD5;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.hotelinfo.dto.UpdatePriceAndStock;
import com.mk.hotel.roomstates.IRoomStatesService;
import com.mk.hotel.roomstates.dto.RoomStatesDto;
import com.mk.hotel.roomtype.RoomTypePriceService;
import com.mk.hotel.roomtype.RoomTypeStockService;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.dto.RoomTypeStockRedisDto;
import com.mk.hotel.roomtypeprice.RoomTypePriceNormalLogService;
import com.mk.hotel.roomtypeprice.RoomTypePriceNormalService;
import com.mk.hotel.roomtypeprice.RoomTypePriceSpecialLogService;
import com.mk.hotel.roomtypeprice.RoomTypePriceSpecialService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalDto;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalLogDto;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialLogDto;
import com.mk.hotel.roomtypestock.RoomTypeStockNormalLogService;
import com.mk.hotel.roomtypestock.RoomTypeStockNormalService;
import com.mk.hotel.roomtypestock.RoomTypeStockSpecialLogService;
import com.mk.hotel.roomtypestock.RoomTypeStockSpecialService;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalDto;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalLogDto;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialLogDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    private RoomTypePriceSpecialService roomTypePriceSpecialService;

    @Autowired
    private RoomTypePriceSpecialLogService roomTypePriceSpecialLogService;

    @Autowired
    private RoomTypeStockSpecialService roomTypeStockSpecialService;

    @Autowired
    private RoomTypeStockSpecialLogService roomTypeStockSpecialLogService;

    @Autowired
    private RoomTypePriceService priceService;

    @Autowired
    private RoomTypeStockService stockService;

    @Autowired
    private RoomTypePriceNormalService roomTypePriceNormalService;

    @Autowired
    private RoomTypePriceNormalLogService roomTypePriceNormalLogService;

    @Autowired
    private RoomTypeStockNormalService roomTypeStockNormalService;

    @Autowired
    private RoomTypeStockNormalLogService roomTypeStockNormalLogService;

    @Override
    public List<RoomStatesDto> queryStates(Long roomTypeId, Date startDate, Date endDate) {

        //
        List<RoomStatesDto> roomStatesDtoList = new ArrayList<RoomStatesDto>();
        if (null == roomTypeId || null == startDate) {
            return roomStatesDtoList;
        }

        if (null == endDate) {
            endDate = DateUtils.addDays(startDate, 31);
        } else {
            endDate = DateUtils.addDays(endDate, 1);
        }

        //
        Date[] dates = DateUtils.getStartEndDate(startDate, endDate);
        for (Date date : dates) {
            //
            RoomTypePriceDto priceDto = this.priceService.queryPriceFromRedis(roomTypeId, date);
            RoomTypeStockRedisDto stockDto = this.stockService.queryStockFromRedis(roomTypeId, date);

            //
            RoomStatesDto dto = new RoomStatesDto();
            dto.setDay(date);
            if (null != priceDto) {
                dto.setMarketPrice(priceDto.getCost());
                dto.setSalePrice(priceDto.getPrice());
                dto.setSettlePrice(priceDto.getSettlePrice());
            }
            if (null != stockDto) {
                dto.setTotalStock(stockDto.getTotalNum());
                Integer availableNum = stockDto.getAvailableNum();
                Integer promoNum = stockDto.getPromoNum();

                if (null != availableNum && null != promoNum) {
                    dto.setStock(availableNum + promoNum);
                } else if (null != availableNum) {
                    dto.setStock(availableNum);
                } else if (null != promoNum) {
                    dto.setStock(promoNum);
                }

            }

            roomStatesDtoList.add(dto);
        }

        return roomStatesDtoList;
    }

    @Override
    public int updatePriceAndStock(Long roomTypeId, Date startDate, Date endDate,
                                   BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice,
                                   Long totalStock, String optionId) {


        //
        int i = 0;
        if (null != settlePrice) {
            i += this.updatePrice(roomTypeId, startDate, endDate, marketPrice, salePrice, settlePrice, optionId);
        }

        if (null != totalStock) {
            i += this.updateStock(roomTypeId, startDate, endDate, totalStock, optionId);
        }

        return i;
    }

    @Override
    public int updatePrice(Long roomTypeId, Date startDate, Date endDate, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operatorId) {

        //
        int diffDay = DateUtils.diffDay(startDate, endDate);

        //
        List<RoomTypePriceSpecialLogDto> dtoList = new ArrayList<RoomTypePriceSpecialLogDto>();
        //
        int result = 0;
        for(int i =0; i<=diffDay; i++){
            Date date = DateUtils.addDays(startDate, i);
            result += roomTypePriceSpecialService.updateRoomTypePriceSpecialRule(roomTypeId, date, marketPrice, salePrice, settlePrice, operatorId);
            dtoList.add(this.convertToRoomTypePriceSpecialLog(roomTypeId, date, marketPrice, salePrice, settlePrice, operatorId));
        }

        //save log
        this.roomTypePriceSpecialLogService.batchInsert(dtoList);
        return result;
    }

    @Override
    public int updateStock(Long roomTypeId, Date startDate, Date endDate, Long totalStock, String operatorId) {

        //
        int diffDay = DateUtils.diffDay(startDate, endDate);

        //
        List<RoomTypeStockSpecialLogDto> dtoList = new ArrayList<RoomTypeStockSpecialLogDto>();

        //
        int result =0;
        for(int i =0; i<=diffDay; i++){
            Date date = DateUtils.addDays(startDate, i);
            result += roomTypeStockSpecialService.updateRoomTypeStockSpecialRule(roomTypeId, date, totalStock, operatorId);
            dtoList.add(this.convertToRoomTypeStockSpecialLog(roomTypeId, date, totalStock, operatorId));
        }

        //save log
        this.roomTypeStockSpecialLogService.batchInsert(dtoList);
        return result;
    }

    @Override
    public int updateNormalStock(RoomTypeStockNormalDto dto, String operatorId) {
        if (null != dto) {
            dto.setUpdateBy(operatorId);
            dto.setCreateBy(operatorId);
            int row = roomTypeStockNormalService.saveOrUpdate(dto);
            RoomTypeStockNormalLogDto logDto = new RoomTypeStockNormalLogDto();
            BeanUtils.copyProperties(dto, logDto);
            List<RoomTypeStockNormalLogDto> list = new ArrayList<RoomTypeStockNormalLogDto>();
            list.add(logDto);
            int rowLog = roomTypeStockNormalLogService.batchInsert(list);
            return row + rowLog;
        }
        return 0;
    }

    @Override
    public void updatePriceAndStock(UpdatePriceAndStock updatePriceAndStock) {
        List<UpdatePriceAndStock.DateList> dateList = updatePriceAndStock.getDateList();
        for(UpdatePriceAndStock.DateList date : dateList){
            updatePrice(updatePriceAndStock.getRoomTypeId(), date.getDay(), date.getDay(),
                    date.getPrice(), date.getPrePayPrice(), date.getSettlementPrice(), updatePriceAndStock.getUserId());

            updateStock(updatePriceAndStock.getRoomTypeId(), date.getDay(), date.getDay(),
                    date.getNumber(), updatePriceAndStock.getUserId());
        }

    }

    @Override
    public int updateNormalPrice(RoomTypePriceNormalDto dto, String operatorId) {
        if (null != dto) {
            dto.setUpdateBy(operatorId);
            dto.setCreateBy(operatorId);
            int row = roomTypePriceNormalService.saveOrUpdate(dto);
            RoomTypePriceNormalLogDto logDto = new RoomTypePriceNormalLogDto();
            BeanUtils.copyProperties(dto, logDto);
            List<RoomTypePriceNormalLogDto> list = new ArrayList<RoomTypePriceNormalLogDto>();
            list.add(logDto);
            int rowLog = roomTypePriceNormalLogService.batchInsert(list);
            return row + rowLog;
        }
        return 0;
    }

    /**
     * 认证
     * @param token
     * @return
     */
    public boolean checkToken(Long roomTypeId, String token) {
        if (null == roomTypeId || StringUtils.isBlank(token)) {
            throw new MyException("认证错误");
        }

        //
        String origin = roomTypeId + "{boss}";
        String security = MD5.MD5Encode(origin);

        if (security.equals(token)) {
            return true;
        } else {
            logger.info("roomTypeId:{} token:{} security:{}", roomTypeId, token, security);
            throw new MyException("认证错误");
        }
    }


    private RoomTypePriceSpecialLogDto convertToRoomTypePriceSpecialLog(Long roomTypeId, Date date, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator) {
        RoomTypePriceSpecialLogDto logDto = new RoomTypePriceSpecialLogDto();
        logDto.setRoomTypeId(roomTypeId);
        logDto.setDay(date);
        logDto.setMarketPrice(marketPrice);
        logDto.setSalePrice(salePrice);
        logDto.setSettlePrice(settlePrice);
        logDto.setIsValid(ValidEnum.VALID.getCode());
        logDto.setUpdateBy(operator);
        logDto.setCreateBy(operator);
        logDto.setUpdateDate(new Date());
        logDto.setCreateDate(new Date());
        return logDto;
    }

    private RoomTypeStockSpecialLogDto convertToRoomTypeStockSpecialLog(Long roomTypeId, Date date, Long totalNumber, String operator) {
        RoomTypeStockSpecialLogDto logDto = new RoomTypeStockSpecialLogDto();
        logDto.setRoomTypeId(roomTypeId);
        logDto.setDay(date);
        logDto.setTotalNumber(totalNumber);
        logDto.setIsValid(ValidEnum.VALID.getCode());
        logDto.setUpdateBy(operator);
        logDto.setCreateBy(operator);
        logDto.setUpdateDate(new Date());
        logDto.setCreateDate(new Date());
        return logDto;
    }
}
