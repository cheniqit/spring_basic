package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialLogDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 11:19.
 */
public class RoomTypePriceSpecialLogServiceTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(RoomTypePriceSpecialLogServiceTest.class);

	@Autowired
	private RoomTypePriceSpecialLogServiceImpl roomTypePriceSpecialLogService;

	@Test
	public void testBatchInsert() {
		List<RoomTypePriceSpecialLogDto> list = new ArrayList<RoomTypePriceSpecialLogDto>();
		for (int i = 2; i < 10; i++) {
			RoomTypePriceSpecialLogDto dto = new RoomTypePriceSpecialLogDto();
			dto.setId(new Long(i));
			dto.setRoomTypeId(new Long(i));
			dto.setCreateBy("id");
			dto.setCreateDate(new Date());
			dto.setIsValid("t");
			dto.setUpdateBy("id");
			dto.setUpdateDate(new Date());
			dto.setDay(new Date());
			dto.setMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			list.add(dto);
		}
		int row = roomTypePriceSpecialLogService.batchInsert(list);
		logger.info("row:{}", row);
	}

	@Test
	public void testSelectById() {
		RoomTypePriceSpecialLogDto dto = roomTypePriceSpecialLogService.selectById(new Long(8));
		logger.info("id:{},date:{}", dto.getId(), dto.getCreateDate());
	}

	@Test
	public void testSaveOrUpdate() {
		RoomTypePriceSpecialLogDto dto = roomTypePriceSpecialLogService.selectById(new Long(8));
		dto.setIsValid("f");
		int affect = roomTypePriceSpecialLogService.saveOrUpdate(dto);
		logger.info("row:{}", affect);
	}
}
