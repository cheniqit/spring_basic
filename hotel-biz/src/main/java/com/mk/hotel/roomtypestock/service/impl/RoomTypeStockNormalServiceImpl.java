package com.mk.hotel.roomtypestock.service.impl;

import com.mk.hotel.roomtypestock.RoomTypeStockNormalService;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/9 15:51.
 */
@Service
public class RoomTypeStockNormalServiceImpl implements RoomTypeStockNormalService {


	@Override
	public int batchInsert(List<RoomTypeStockNormalDto> roomTypeStockNormalDtoList) {
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypeStockNormalDto dto) {
		return 0;
	}

	@Override
	public RoomTypeStockNormalDto selectById(Long id) {
		return null;
	}
}
