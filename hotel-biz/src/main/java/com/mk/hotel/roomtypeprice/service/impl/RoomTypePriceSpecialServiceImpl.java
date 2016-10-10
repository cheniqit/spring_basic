package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.hotel.roomtypeprice.RoomTypePriceSpecialService;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:45.
 */
@Service
public class RoomTypePriceSpecialServiceImpl implements RoomTypePriceSpecialService {
	@Override
	public int batchInsert(List<RoomTypePriceSpecialDto> roomTypePriceSpecialDtoList) {
		return 0;
	}

	@Override
	public int saveOrUpdate(RoomTypePriceSpecialDto dto) {
		return 0;
	}

	@Override
	public RoomTypePriceSpecialDto selectById(Long id) {
		return null;
	}
}
