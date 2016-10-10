package com.mk.hotel.roomtypestock.service.impl;

import com.mk.hotel.roomtypestock.RoomTypeStockSpecialLogService;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialLogDto;
import com.mk.hotel.roomtypestock.mapper.RoomTypeStockSpecialLogMapper;
import com.mk.hotel.roomtypestock.model.RoomTypeStockSpecialLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:44.
 */
@Service
public class RoomTypeStockSpecialLogServiceImpl implements RoomTypeStockSpecialLogService {

	@Autowired
	private RoomTypeStockSpecialLogMapper roomTypeStockSpecialLogMapper;

	@Override
	public int batchInsert(List<RoomTypeStockSpecialLogDto> roomTypeStockSpecialLogDtoList) {
		if (null != roomTypeStockSpecialLogDtoList) {
			List<RoomTypeStockSpecialLog> list = new ArrayList<RoomTypeStockSpecialLog>();
			for (RoomTypeStockSpecialLogDto dto : roomTypeStockSpecialLogDtoList) {
				list.add(toModel(dto));
			}
			return roomTypeStockSpecialLogMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypeStockSpecialLogDto dto) {
		if (null != dto) {
			return roomTypeStockSpecialLogMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypeStockSpecialLogDto selectById(Long id) {
		if (null != id) {
			RoomTypeStockSpecialLog model = roomTypeStockSpecialLogMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	/**
	 * dto转model
	 * @param dto
	 * @return
	 */
	public RoomTypeStockSpecialLog toModel(RoomTypeStockSpecialLogDto dto) {
		if (null != dto) {
			RoomTypeStockSpecialLog model = new RoomTypeStockSpecialLog();
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
	public RoomTypeStockSpecialLogDto toDto(RoomTypeStockSpecialLog model) {
		if (null != model) {
			RoomTypeStockSpecialLogDto dto = new RoomTypeStockSpecialLogDto();
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}
}
