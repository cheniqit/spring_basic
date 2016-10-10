package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.hotel.roomtypeprice.RoomTypePriceNormalLogService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalLogDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:46.
 */

@Service
public class RoomTypePriceNormalLogServiceImpl implements RoomTypePriceNormalLogService {
	@Override
	public int batchInsert(List<RoomTypePriceNormalLogDto> roomTypePriceNormalLogDtoList) {
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypePriceNormalLogDto dto) {
		return 0;
	}

	@Override
	public RoomTypePriceNormalLogDto selectById(Long id) {
		return null;
	}
}
