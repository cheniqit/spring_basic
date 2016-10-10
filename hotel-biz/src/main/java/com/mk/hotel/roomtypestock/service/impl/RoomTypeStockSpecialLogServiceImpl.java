package com.mk.hotel.roomtypestock.service.impl;

import com.mk.hotel.roomtypestock.RoomTypeStockSpecialLogService;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialLogDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:44.
 */
@Service
public class RoomTypeStockSpecialLogServiceImpl implements RoomTypeStockSpecialLogService {

	@Override
	public int batchInsert(List<RoomTypeStockSpecialLogDto> roomTypeStockSpecialLogDtoList) {
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypeStockSpecialLogDto dto) {
		return 0;
	}

	@Override
	public RoomTypeStockSpecialLogDto selectById(Long id) {
		return null;
	}
}
