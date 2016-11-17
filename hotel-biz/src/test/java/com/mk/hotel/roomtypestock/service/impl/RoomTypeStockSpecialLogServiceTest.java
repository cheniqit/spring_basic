package com.mk.hotel.roomtypestock.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialLogDto;
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
public class RoomTypeStockSpecialLogServiceTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(RoomTypeStockSpecialLogServiceTest.class);

	@Autowired
	private RoomTypeStockSpecialLogServiceImpl roomTypeStockSpecialLogService;

	@Test
	public void testBatchInsert() {
		List<RoomTypeStockSpecialLogDto> list = new ArrayList<RoomTypeStockSpecialLogDto>();
		for (int i = 2; i < 10; i++) {
			RoomTypeStockSpecialLogDto dto = new RoomTypeStockSpecialLogDto();
			dto.setId(new Long(i));
			dto.setRoomTypeId(new Long(i));
			dto.setCreateBy("id");
			dto.setCreateDate(new Date());
			dto.setIsValid("t");
			dto.setUpdateBy("id");
			dto.setUpdateDate(new Date());
			dto.setDay(new Date());
			dto.setTotalNumber(1l);

			list.add(dto);
		}
		int row = roomTypeStockSpecialLogService.batchInsert(list);
		logger.info("row:{}", row);
	}

	@Test
	public void testSelectById() {
		RoomTypeStockSpecialLogDto dto = roomTypeStockSpecialLogService.selectById(new Long(8));
		logger.info("id:{},date:{}", dto.getId(), dto.getCreateDate());
	}

	@Test
	public void testSaveOrUpdate() {
		RoomTypeStockSpecialLogDto dto = roomTypeStockSpecialLogService.selectById(new Long(8));
		dto.setIsValid("f");
		int affect = roomTypeStockSpecialLogService.saveOrUpdate(dto);
		logger.info("row:{}", affect);
	}
}
