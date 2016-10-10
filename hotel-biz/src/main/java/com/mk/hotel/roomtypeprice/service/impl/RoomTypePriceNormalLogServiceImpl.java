package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.hotel.roomtypeprice.RoomTypePriceNormalLogService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalLogDto;
import com.mk.hotel.roomtypeprice.mapper.RoomTypePriceNormalLogMapper;
import com.mk.hotel.roomtypeprice.model.RoomTypePriceNormalLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:46.
 */
@Service
public class RoomTypePriceNormalLogServiceImpl implements RoomTypePriceNormalLogService {

	@Autowired
	private RoomTypePriceNormalLogMapper roomTypePriceNormalLogMapper;

	@Override
	public int batchInsert(List<RoomTypePriceNormalLogDto> roomTypePriceNormalLogDtoList) {
		if (null != roomTypePriceNormalLogDtoList) {
			List<RoomTypePriceNormalLog> list = new ArrayList<RoomTypePriceNormalLog>();
			for (RoomTypePriceNormalLogDto dto : roomTypePriceNormalLogDtoList) {
				list.add(toModel(dto));
			}
			return roomTypePriceNormalLogMapper.batchInsert(list);
		}
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypePriceNormalLogDto dto) {
		if (null != dto) {
			return roomTypePriceNormalLogMapper.updateByPrimaryKey(toModel(dto));
		}
		return 0;
	}

	@Override
	public RoomTypePriceNormalLogDto selectById(Long id) {
		if (null != id) {
			RoomTypePriceNormalLog model = roomTypePriceNormalLogMapper.selectByPrimaryKey(id);
			return toDto(model);
		}
		return null;
	}

	/**
	 * dto转model
	 * @param dto
	 * @return
	 */
	public RoomTypePriceNormalLog toModel(RoomTypePriceNormalLogDto dto) {
		if (null != dto) {
			RoomTypePriceNormalLog model = new RoomTypePriceNormalLog();
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
	public RoomTypePriceNormalLogDto toDto(RoomTypePriceNormalLog model) {
		if (null != model) {
			RoomTypePriceNormalLogDto dto = new RoomTypePriceNormalLogDto();
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}
}
