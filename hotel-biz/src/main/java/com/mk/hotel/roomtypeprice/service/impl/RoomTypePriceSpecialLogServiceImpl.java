package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.hotel.roomtypeprice.RoomTypePriceSpecialLogService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialLogDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:44.
 */
@Service
public class RoomTypePriceSpecialLogServiceImpl implements RoomTypePriceSpecialLogService {
	@Override
	public int batchInsert(List<RoomTypePriceSpecialLogDto> roomTypePriceSpecialLogDtoList) {
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypePriceSpecialLogDto dto) {
		return 0;
	}

	@Override
	public RoomTypePriceSpecialLogDto selectById(Long id) {
		return null;
	}
}
