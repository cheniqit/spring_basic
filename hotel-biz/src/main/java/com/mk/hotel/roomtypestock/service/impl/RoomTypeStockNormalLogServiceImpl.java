package com.mk.hotel.roomtypestock.service.impl;

import com.mk.hotel.roomtypestock.RoomTypeStockNormalLogService;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalLogDto;
import com.mk.hotel.roomtypestock.mapper.RoomTypeStockNormalLogMapper;
import com.mk.hotel.roomtypestock.model.RoomTypeStockNormalLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:46.
 */
@Service
public class RoomTypeStockNormalLogServiceImpl implements RoomTypeStockNormalLogService {

	@Autowired
	private RoomTypeStockNormalLogMapper roomTypeStockNormalLogMapper;

	@Override
	public int batchInsert(List<RoomTypeStockNormalLogDto> roomTypeStockNormalLogDtoList) {
		if (null != roomTypeStockNormalLogDtoList) {
			List<RoomTypeStockNormalLog> list = new ArrayList<RoomTypeStockNormalLog>();
			for (RoomTypeStockNormalLogDto dto : roomTypeStockNormalLogDtoList) {
				list.add(toModel(dto));
			}
			return roomTypeStockNormalLogMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypeStockNormalLogDto dto) {
		if (null != dto) {
			return roomTypeStockNormalLogMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypeStockNormalLogDto selectById(Long id) {
		if (null != id) {
			RoomTypeStockNormalLog model = roomTypeStockNormalLogMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	/**
	 * dto转model
	 * @param dto
	 * @return
	 */
	public RoomTypeStockNormalLog toModel(RoomTypeStockNormalLogDto dto) {
		if (null != dto) {
			RoomTypeStockNormalLog model = new RoomTypeStockNormalLog();
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
	public RoomTypeStockNormalLogDto toDto(RoomTypeStockNormalLog model) {
		if (null != model) {
			RoomTypeStockNormalLogDto dto = new RoomTypeStockNormalLogDto();
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}
}
