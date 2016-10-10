package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.framework.JsonUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.hotel.common.Constant;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.message.MsgProducer;
import com.mk.hotel.roomtypeprice.RoomTypePriceSpecialService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;
import com.mk.hotel.roomtypeprice.mapper.RoomTypePriceSpecialMapper;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecial;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecialExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:45.
 */
@Service
public class RoomTypePriceSpecialServiceImpl implements RoomTypePriceSpecialService {
	@Autowired
	private MsgProducer msgProducer;
	@Autowired
	private RoomTypePriceSpecialMapper roomTypePriceSpecialMapper;

	public int updateRoomTypePriceSpecialRule(Long roomTypeId, Date date, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator){
		if(settlePrice == null){
			throw new MyException("参数错误");
		}

		//
		if (marketPrice == null) {
			marketPrice = settlePrice.multiply(new BigDecimal("0.15"));
			marketPrice.setScale(0, BigDecimal.ROUND_UP);
			marketPrice = marketPrice.multiply(BigDecimal.TEN);
		}

		//
		if (salePrice == null) {
			salePrice = settlePrice;
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
		}else if(!CollectionUtils.isEmpty(roomTypePriceSpecialList)){
			roomTypePriceSpecial.setCreateDate(null);
			roomTypePriceSpecial.setCreateBy(null);
			roomTypePriceSpecialMapper.updateByExampleSelective(roomTypePriceSpecial ,example);
		}else{
			throw new MyException("房型价格配置错误,根据房型和时间查到多条配置信息");
		}

		//send msg
		try{
			msgProducer.sendMsg(Constant.TOPIC_ROOMTYPE_PRICE, "special", "", JsonUtils.toJson(roomTypePriceSpecial));
		}catch (Exception e){
			throw new MyException("房型价格配置错误,发送消息错误");
		}

		return 1;
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


	@Override
	public int batchInsert(List<RoomTypePriceSpecialDto> roomTypePriceSpecialDtoList) {
		if (null != roomTypePriceSpecialDtoList) {
			List<RoomTypePriceSpecial> list = new ArrayList<RoomTypePriceSpecial>();
			for (RoomTypePriceSpecialDto dto : roomTypePriceSpecialDtoList) {
				list.add(toModel(dto));
			}
			return roomTypePriceSpecialMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypePriceSpecialDto dto) {
		if (null != dto) {
			return roomTypePriceSpecialMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypePriceSpecialDto selectById(Long id) {
		if (null != id) {
			RoomTypePriceSpecial model = roomTypePriceSpecialMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	/**
	 * dto转model
	 * @param dto
	 * @return
	 */
	public RoomTypePriceSpecial toModel(RoomTypePriceSpecialDto dto) {
		if (null != dto) {
			RoomTypePriceSpecial model = new RoomTypePriceSpecial();
			BeanUtils.copyProperties(dto, model);
			return model;
		}
		return null;
	}

	/**
	 * model转dto
	 * @param model
	 * @return
	 */
	public RoomTypePriceSpecialDto toDto(RoomTypePriceSpecial model) {
		if (null != model) {
			RoomTypePriceSpecialDto dto = new RoomTypePriceSpecialDto();
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}
}
