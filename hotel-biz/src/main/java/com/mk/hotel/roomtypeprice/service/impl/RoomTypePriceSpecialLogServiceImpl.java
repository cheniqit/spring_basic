package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.hotel.roomtypeprice.RoomTypePriceSpecialLogService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialLogDto;
import com.mk.hotel.roomtypeprice.mapper.RoomTypePriceSpecialLogMapper;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceSpecialLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:44.
 */
@Service
public class RoomTypePriceSpecialLogServiceImpl implements RoomTypePriceSpecialLogService {

	@Autowired
	private RoomTypePriceSpecialLogMapper roomTypePriceSpecialLogMapper;

	@Override
	public int batchInsert(List<RoomTypePriceSpecialLogDto> roomTypePriceSpecialLogDtoList) {
		if (null != roomTypePriceSpecialLogDtoList) {
			List<RoomTypePriceSpecialLog> list = new ArrayList<RoomTypePriceSpecialLog>();
			for (RoomTypePriceSpecialLogDto dto : roomTypePriceSpecialLogDtoList) {
				list.add(toModel(dto));
			}
			return roomTypePriceSpecialLogMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypePriceSpecialLogDto dto) {
		if (null != dto) {
			return roomTypePriceSpecialLogMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypePriceSpecialLogDto selectById(Long id) {
		if (null != id) {
			RoomTypePriceSpecialLog model = roomTypePriceSpecialLogMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	/**
	 *
	 * @param dto
	 * @return
	 */
	public RoomTypePriceSpecialLog toModel(RoomTypePriceSpecialLogDto dto) {
		if (null != dto) {
			RoomTypePriceSpecialLog model = new RoomTypePriceSpecialLog();
			BeanUtils.copyProperties(dto, model);
			return model;
		}
		return null;
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	public RoomTypePriceSpecialLogDto toDto(RoomTypePriceSpecialLog model) {
		if (null != model) {
			RoomTypePriceSpecialLogDto dto = new RoomTypePriceSpecialLogDto();
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}
}
