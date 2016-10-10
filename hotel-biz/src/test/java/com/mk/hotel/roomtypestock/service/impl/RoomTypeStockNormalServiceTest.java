package com.mk.hotel.roomtypestock.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalDto;
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
public class RoomTypeStockNormalServiceTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(RoomTypeStockNormalServiceTest.class);

	@Autowired
	private RoomTypeStockNormalServiceImpl roomTypeStockNormalService;

	@Test
	public void testBatchInsert() {
		List<RoomTypeStockNormalDto> list = new ArrayList<RoomTypeStockNormalDto>();
		for (int i = 2; i < 10; i++) {
			RoomTypeStockNormalDto dto = new RoomTypeStockNormalDto();
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
		int row = roomTypeStockNormalService.batchInsert(list);
		logger.info("row:{}", row);
	}

	@Test
	public void testSelectById() {
		RoomTypeStockNormalDto dto = roomTypeStockNormalService.selectById(new Long(8));
		logger.info("id:{},date:{}", dto.getId(), dto.getCreateDate());
	}

	@Test
	public void testSaveOrUpdate() {
		RoomTypeStockNormalDto dto = roomTypeStockNormalService.selectById(new Long(8));
		dto.setIsValid("f");
		int affect = roomTypeStockNormalService.saveOrUpdate(dto);
		logger.info("row:{}", affect);
	}
}
