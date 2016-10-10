package com.mk.hotel.roomtypestock.service.impl;

import com.mk.framework.DateUtils;
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
public class RoomTypeStockSpecialServiceImpl implements RoomTypeStockSpecialService {

	@Autowired
	private MsgProducer msgProducer;
	@Autowired
	private RoomTypeStockSpecialMapper roomTypeStockSpecialMapper;

	public int updateRoomTypeStockSpecialRule(Long roomTypeId, Date date, Long totalNumber, String operator){
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
			String message = JsonUtils.toJson(roomTypeStockSpecial, DateUtils.FORMAT_DATETIME);
			msgProducer.sendMsg(Constant.TOPIC_ROOMTYPE_STOCK, "special", "", message);
		}catch (Exception e){
			throw new MyException("库存价格配置错误,发送消息错误");
		}

		return 1;
	}

	private RoomTypeStockSpecial convertToRoomTypeStockSpecial(Long roomTypeId, Date date, Long totalNumber, String operator) {
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

	@Override
	public int batchInsert(List<RoomTypeStockSpecialDto> roomTypeStockSpecialDtoList) {
		if (null != roomTypeStockSpecialDtoList) {
			List<RoomTypeStockSpecial> list = new ArrayList<RoomTypeStockSpecial>();
			for (RoomTypeStockSpecialDto dto : roomTypeStockSpecialDtoList) {
				list.add(toModel(dto));
			}
			return roomTypeStockSpecialMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypeStockSpecialDto dto) {
		if (null != dto) {
			return roomTypeStockSpecialMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypeStockSpecialDto selectById(Long id) {
		if (null != id) {
			RoomTypeStockSpecial model = roomTypeStockSpecialMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	@Override
	public RoomTypeStockSpecialDto selectByDay(Long roomTypeId, Date day) {
		RoomTypeStockSpecialExample example = new RoomTypeStockSpecialExample();
			example.createCriteria().andRoomTypeIdEqualTo(roomTypeId).andDayEqualTo(day).andIsValidEqualTo(ValidEnum.VALID.getCode());
		List<RoomTypeStockSpecial> roomTypeStockSpecialList = roomTypeStockSpecialMapper.selectByExample(example);
		if (null != roomTypeStockSpecialList && 0 < roomTypeStockSpecialList.size()) {
			return toDto(roomTypeStockSpecialList.get(0));
		}
		return null;
	}

	/**
	 * dto转model
	 * @param dto
	 * @return
	 */
	public RoomTypeStockSpecial toModel(RoomTypeStockSpecialDto dto) {
		if (null != dto) {
			RoomTypeStockSpecial model = new RoomTypeStockSpecial();
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
	public RoomTypeStockSpecialDto toDto(RoomTypeStockSpecial model) {
		if (null != model) {
			RoomTypeStockSpecialDto dto = new RoomTypeStockSpecialDto();
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}
}
