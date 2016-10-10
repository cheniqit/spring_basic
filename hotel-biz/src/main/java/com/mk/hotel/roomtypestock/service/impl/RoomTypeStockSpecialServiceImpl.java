package com.mk.hotel.roomtypestock.service.impl;

import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.hotel.common.Constant;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.message.MsgProducer;
import com.mk.hotel.roomtypestock.RoomTypeStockSpecialService;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialDto;
import com.mk.hotel.roomtypestock.mapper.RoomTypeStockSpecialMapper;
import com.mk.hotel.roomtypestock.model.RoomTypeStockSpecial;
import com.mk.hotel.roomtypestock.model.RoomTypeStockSpecialExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:45.
 */
@Service
public class RoomTypeStockSpecialServiceImpl implements RoomTypeStockSpecialService {

	@Autowired
	private MsgProducer msgProducer;
	@Autowired
	private RoomTypeStockSpecialMapper roomTypeStockSpecialMapper;

	@Override
	public int batchInsert(List<RoomTypeStockSpecialDto> roomTypeStockSpecialDtoList) {
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypeStockSpecialDto dto) {
		return 0;
	}

	@Override
	public RoomTypeStockSpecialDto selectById(Long id) {
		return null;
	}


	public void updateRoomTypeStockSpecialRule(Long roomTypeId, Date date, BigDecimal totalNumber, String operator){
		if(roomTypeId == null){
			throw new MyException("参数错误");
		}
		if(date == null){
			throw new MyException("参数错误");
		}
		if(totalNumber == null){
			throw new MyException("参数错误");
		}
		if(operator == null){
			throw new MyException("参数错误");
		}
		//转换保存
		RoomTypeStockSpecial roomTypeStockSpecial = convertToRoomTypeStockSpecial(roomTypeId, date, totalNumber, operator);
		//保存
		RoomTypeStockSpecialExample example = new RoomTypeStockSpecialExample();
		example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayEqualTo(date).andIsValidEqualTo(ValidEnum.VALID.getCode());
		List<RoomTypeStockSpecial> roomTypeStockSpecialList = roomTypeStockSpecialMapper.selectByExample(example);

		if(CollectionUtils.isEmpty(roomTypeStockSpecialList)){
			roomTypeStockSpecialMapper.insert(roomTypeStockSpecial);
		}else if(roomTypeStockSpecialList.size() == 1){
			roomTypeStockSpecial.setCreateDate(null);
			roomTypeStockSpecialMapper.updateByExample(roomTypeStockSpecial ,example);
		}else{
			throw new MyException("库存价格配置错误,根据房型和时间查到多条配置信息");
		}
		try{
			msgProducer.sendMsg(Constant.TOPIC_ROOMTYPE_STOCK, "special", "", JsonUtils.toJson(roomTypeStockSpecial));
		}catch (Exception e){
			throw new MyException("库存价格配置错误,发送消息错误");
		}
	}

	private RoomTypeStockSpecial convertToRoomTypeStockSpecial(Long roomTypeId, Date date, BigDecimal totalNumber, String operator) {
		RoomTypeStockSpecial roomTypeStockSpecial = new RoomTypeStockSpecial();
		roomTypeStockSpecial.setRoomTypeId(roomTypeId);
		roomTypeStockSpecial.setDay(date);
		roomTypeStockSpecial.setTotalNumber(totalNumber);
		roomTypeStockSpecial.setIsValid(ValidEnum.VALID.getCode());
		roomTypeStockSpecial.setUpdateBy(operator);
		roomTypeStockSpecial.setCreateBy(operator);
		roomTypeStockSpecial.setUpdateDate(new Date());
		roomTypeStockSpecial.setCreateDate(new Date());
		return roomTypeStockSpecial;
	}
}
