package com.mk.hotel.roomtypestock.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalLogDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 11:19.
 */
public class RoomTypeStockNormalLogServiceTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(RoomTypeStockNormalLogServiceTest.class);

	@Autowired
	private RoomTypeStockNormalLogServiceImpl roomTypeStockNormalLogService;

	@Test
	public void testBatchInsert() {
		List<RoomTypeStockNormalLogDto> list = new ArrayList<RoomTypeStockNormalLogDto>();
		for (int i = 2; i < 10; i++) {
			RoomTypeStockNormalLogDto dto = new RoomTypeStockNormalLogDto();
			dto.setId(new Long(i));
			dto.setRoomTypeId(new Long(i));
			dto.setCreateBy("id");
			dto.setCreateDate(new Date());
			dto.setIsValid("t");
			dto.setUpdateBy("id");
			dto.setUpdateDate(new Date());

			dto.setMonTotalNumber(new Long(i));
			dto.setTueTotalNumber(new Long(i));
			dto.setWedTotalNumber(new Long(i));
			dto.setThuTotalNumber(new Long(i));
			dto.setFriTotalNumber(new Long(i));
			dto.setSatTotalNumber(new Long(i));
			dto.setSunTotalNumber(new Long(i));

			list.add(dto);
		}
		int row = roomTypeStockNormalLogService.batchInsert(list);
		logger.info("row:{}", row);
	}

	@Test
	public void testSelectById() {
		RoomTypeStockNormalLogDto dto = roomTypeStockNormalLogService.selectById(new Long(8));
		logger.info("id:{},date:{}", dto.getId(), dto.getCreateDate());
	}

	@Test
	public void testSaveOrUpdate() {
		RoomTypeStockNormalLogDto dto = roomTypeStockNormalLogService.selectById(new Long(8));
		dto.setIsValid("f");
		int affect = roomTypeStockNormalLogService.saveOrUpdate(dto);
		logger.info("row:{}", affect);
	}
}
