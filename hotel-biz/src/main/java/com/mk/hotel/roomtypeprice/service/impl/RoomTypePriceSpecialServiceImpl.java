package com.mk.hotel.roomtypeprice.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.date.DateUtils;
import com.mk.framework.excepiton.MyException;
import com.mk.framework.json.JsonUtils;
import com.mk.framework.redis.MkJedisConnectionFactory;
import com.mk.hotel.common.enums.ValidEnum;
import com.mk.hotel.common.utils.OtsInterface;
import com.mk.hotel.consume.enums.TopicEnum;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.service.impl.HotelServiceImpl;
import com.mk.hotel.mq.producer.MsgProducer;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.dto.RoomTypePriceDto;
import com.mk.hotel.roomtype.model.RoomType;
import com.mk.hotel.roomtype.service.impl.RoomTypePriceServiceImpl;
import com.mk.hotel.roomtype.service.impl.RoomTypeServiceImpl;
import com.mk.hotel.roomtypeprice.RoomTypePriceSpecialService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;
import com.mk.hotel.roomtypeprice.mapper.RoomTypePriceSpecialMapper;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecial;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecialExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by yanbaobin on 2016/10/10 9:45.
 */
@Service
public class RoomTypePriceSpecialServiceImpl implements RoomTypePriceSpecialService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MsgProducer msgProducer;
	@Autowired
	private RoomTypePriceSpecialMapper roomTypePriceSpecialMapper;

	@Autowired
	private RoomTypePriceServiceImpl roomTypePriceService;

	@Autowired
	private RoomTypeServiceImpl roomTypeService;

	public int updateRoomTypePriceSpecialRule(Long roomTypeId, Date date, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator){
		if(settlePrice == null){
			throw new MyException("参数错误");
		}

		//
		if (marketPrice == null) {
			marketPrice = settlePrice.multiply(new BigDecimal("0.15"));
			marketPrice = marketPrice.setScale(0, BigDecimal.ROUND_UP);
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

		RoomTypePriceSpecialDto dto = this.selectByDay(roomTypeId, date);

		if (null == dto) {
			//转换保存
			dto = convertToDto(null, roomTypeId, date, marketPrice, salePrice, settlePrice, operator);
		} else {
			dto = convertToDto(dto, roomTypeId, date, marketPrice, salePrice, settlePrice, operator);
		}

		this.saveOrUpdate(dto);

		//send msg
		try {
			String message = JsonUtils.toJson(dto, DateUtils.FORMAT_DATETIME);

			String key = new StringBuilder(TopicEnum.ROOM_TYPE_PRICE.getName())
					.append(System.currentTimeMillis())
					.append(dto.getId()).toString();
			msgProducer.sendMsg(TopicEnum.ROOM_TYPE_PRICE.getName(), "special", key, message);

		}catch (Exception e){
			e.printStackTrace();
			throw new MyException("房型价格配置错误,发送消息错误");
		}

		return 1;
	}

	private RoomTypePriceSpecialDto convertToDto(RoomTypePriceSpecialDto dbDto, Long roomTypeId, Date date, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator) {
		RoomTypePriceSpecialDto dto = null;

		if (null == dbDto) {
			dto = new RoomTypePriceSpecialDto();

			dto.setRoomTypeId(roomTypeId);
			dto.setDay(date);
			dto.setCreateBy(operator);
			dto.setCreateDate(new Date());
		} else {
			dto = dbDto;
		}

		dto.setMarketPrice(marketPrice);
		dto.setSalePrice(salePrice);
		dto.setSettlePrice(settlePrice);
		dto.setIsValid(ValidEnum.VALID.getCode());
		dto.setUpdateBy(operator);
		dto.setUpdateDate(new Date());
		return dto;
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
			RoomTypePriceSpecial model = toModel(dto);
			if (null == dto.getId()) {
				int result = roomTypePriceSpecialMapper.insert(model);
				dto.setId(model.getId());
				return result;
			} else {
				return roomTypePriceSpecialMapper.updateByPrimaryKey(model);
			}
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

	@Override
	public RoomTypePriceSpecialDto selectByDay(Long roomTypeId, Date day) {
		RoomTypePriceSpecialExample example = new RoomTypePriceSpecialExample();
		example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayEqualTo(day).andIsValidEqualTo(ValidEnum.VALID.getCode());
		List<RoomTypePriceSpecial> list = this.roomTypePriceSpecialMapper.selectByExample(example);

		if (list.isEmpty()) {
			return null;
		} else if (list.size() == 1) {
			RoomTypePriceSpecial special = list.get(0);

			return toDto(special);
		} else {
			throw new MyException("价格配置错误,根据房型和时间查到多条配置信息");
		}
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

	public void executeConsumeMessage(RoomTypePriceSpecialDto roomTypePriceSpecial, TopicEnum topicEnum) {
		//查找对应的fangid
		RoomType roomType = roomTypeService.selectRoomTypeById(roomTypePriceSpecial.getRoomTypeId());
		if(roomType == null){
			logger.info("topic name:{} msg :{} roomType is null", topicEnum.getName(), JsonUtils.toJson(roomTypePriceSpecial));
			throw new MyException("roomType is null");
		}
//		HotelDto hotelDto = hotelService.findById(roomType.getHotelId());
//		if(hotelDto == null){
//			logger.info("topic name:{} msg :{} hotel is null", topicEnum.getName(), JsonUtils.toJson(roomTypePriceSpecial));
//			throw new MyException("hotelDto is null");
//		}
//		//
//		RoomTypePriceDto roomTypePriceDto = new RoomTypePriceDto();
//		roomTypePriceDto.setRoomTypeId(roomType.getId());
//		roomTypePriceDto.setDay(roomTypePriceSpecial.getDay());
//		roomTypePriceDto.setCost(roomTypePriceSpecial.getMarketPrice());
//		roomTypePriceDto.setPrice(roomTypePriceSpecial.getSalePrice());
//		roomTypePriceDto.setSettlePrice(roomTypePriceSpecial.getSettlePrice());
//		roomTypePriceService.saveOrUpdateByRoomTypeId(roomTypePriceDto , roomTypePriceDto.getCreateBy());


		roomTypePriceService.updateRedisPrice(roomTypePriceSpecial.getRoomTypeId(), roomType.getName(), roomTypePriceSpecial.getDay(),
				roomTypePriceSpecial.getSalePrice(), roomTypePriceSpecial.getMarketPrice(), roomTypePriceSpecial.getSettlePrice(), "specialTopic");

	}
}
