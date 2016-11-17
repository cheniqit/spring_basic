package com.mk.hotel.roomtypestock.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 11:19.
 */
public class RoomTypeStockSpecialServiceTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(RoomTypeStockSpecialServiceTest.class);

	@Autowired
	private RoomTypeStockSpecialServiceImpl roomTypeStockSpecialService;

	@Test
	public void testBatchInsert() {
		List<RoomTypeStockSpecialDto> list = new ArrayList<RoomTypeStockSpecialDto>();
		for (int i = 2; i < 10; i++) {
			RoomTypeStockSpecialDto dto = new RoomTypeStockSpecialDto();
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
		int row = roomTypeStockSpecialService.batchInsert(list);
		logger.info("row:{}", row);
	}

	@Test
	public void testSelectById() {
		RoomTypeStockSpecialDto dto = roomTypeStockSpecialService.selectById(new Long(8));
		logger.info("id:{},date:{}", dto.getId(), dto.getCreateDate());
	}

	@Test
	public void testSaveOrUpdate() {
		RoomTypeStockSpecialDto dto = roomTypeStockSpecialService.selectById(new Long(8));
		dto.setIsValid("f");
		int affect = roomTypeStockSpecialService.saveOrUpdate(dto);
		logger.info("row:{}", affect);
	}

	@Test
	public void testSelectByDay() {
		//
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = format.parse("2016-11-01");
			RoomTypeStockSpecialDto dto = roomTypeStockSpecialService.selectByDay(31877l, d);

		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
