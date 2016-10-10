package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.hotel.common.Constant;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.message.MsgProducer;
import com.mk.hotel.roomtypeprice.mapper.RoomTypePriceSpecialMapper;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecial;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecialExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by chenqi on 16/10/9.
 */
public class RoomTypePriceServiceImpl {
    @Autowired
    private MsgProducer msgProducer;
    @Autowired
    private RoomTypePriceSpecialMapper roomTypePriceSpecialMapper;

    public void updateRoomTypePriceSpecialRule(Long roomTypeId, Date date, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator){
        if(settlePrice == null){
            throw new MyException("参数错误");
        }
        if(marketPrice == null || salePrice == null){
            salePrice = settlePrice;
            marketPrice = settlePrice.multiply(new BigDecimal("0.5")).add(settlePrice);
            marketPrice.setScale(0, BigDecimal.ROUND_HALF_UP);
        }
        if(roomTypeId == null){
            throw new MyException("参数错误");
        }
        if(date == null){
            throw new MyException("参数错误");
        }
        if(marketPrice == null){
            throw new MyException("参数错误");
        }
        if(salePrice == null){
            throw new MyException("参数错误");
        }

        if(operator == null){
            throw new MyException("参数错误");
        }
        //转换保存
        RoomTypePriceSpecial roomTypePriceSpecial = convertToRoomTypePriceSpecial(roomTypeId, date, marketPrice, salePrice, settlePrice, operator);
        //保存
        RoomTypePriceSpecialExample example = new RoomTypePriceSpecialExample();
        example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayEqualTo(date).andIsValidEqualTo(ValidEnum.VALID.getCode());
        List<RoomTypePriceSpecial> roomTypePriceSpecialList = roomTypePriceSpecialMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(roomTypePriceSpecialList)){
            roomTypePriceSpecialMapper.insert(roomTypePriceSpecial);
        }else if(roomTypePriceSpecialList.size() == 1){
            roomTypePriceSpecial.setCreateDate(null);
            roomTypePriceSpecialMapper.updateByExample(roomTypePriceSpecial ,example);
        }else{
            throw new MyException("房型价格配置错误,根据房型和时间查到多条配置信息");
        }
        try{
            msgProducer.sendMsg(Constant.TOPIC_ROOMTYPE_PRICE, "special", "", JsonUtils.toJson(roomTypePriceSpecial));
        }catch (Exception e){
            throw new MyException("房型价格配置错误,发送消息错误");
        }
    }

    private RoomTypePriceSpecial convertToRoomTypePriceSpecial(Long roomTypeId, Date date, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator) {
        RoomTypePriceSpecial roomTypePriceSpecial = new RoomTypePriceSpecial();
        roomTypePriceSpecial.setRoomTypeId(roomTypeId);
        roomTypePriceSpecial.setDay(date);
        roomTypePriceSpecial.setMarketPrice(marketPrice);
        roomTypePriceSpecial.setSalePrice(salePrice);
        roomTypePriceSpecial.setSettlePrice(settlePrice);
        roomTypePriceSpecial.setIsValid(ValidEnum.VALID.getCode());
        roomTypePriceSpecial.setUpdateBy(operator);
        roomTypePriceSpecial.setCreateBy(operator);
        roomTypePriceSpecial.setUpdateDate(new Date());
        roomTypePriceSpecial.setCreateDate(new Date());
        return roomTypePriceSpecial;
    }

}
