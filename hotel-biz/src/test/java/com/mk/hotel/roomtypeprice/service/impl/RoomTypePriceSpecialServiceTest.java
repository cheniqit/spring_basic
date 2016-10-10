package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 11:19.
 */
public class RoomTypePriceSpecialServiceTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(RoomTypePriceSpecialServiceTest.class);

	@Autowired
	private RoomTypePriceSpecialServiceImpl roomTypePriceSpecialService;

	@Test
	public void testBatchInsert() {
		List<RoomTypePriceSpecialDto> list = new ArrayList<RoomTypePriceSpecialDto>();
		for (int i = 4; i < 10; i++) {
			RoomTypePriceSpecialDto dto = new RoomTypePriceSpecialDto();
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
		int row = roomTypePriceSpecialService.batchInsert(list);
		logger.info("row:{}", row);
	}

	@Test
	public void testSelectById() {
		RoomTypePriceSpecialDto dto = roomTypePriceSpecialService.selectById(new Long(8));
		logger.info("id:{},date:{}", dto.getId(), dto.getCreateDate());
	}

	@Test
	public void testSaveOrUpdate() {
		RoomTypePriceSpecialDto dto = roomTypePriceSpecialService.selectById(new Long(8));
		dto.setIsValid("f");
		int affect = roomTypePriceSpecialService.saveOrUpdate(dto);
		logger.info("row:{}", affect);
	}

	@Test
	public void testSelcetByDay() throws ParseException {
		Long roomTypeId = new Long(9);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Date day = simpleDateFormat.parse("2016-10-10");
		RoomTypePriceSpecialDto dto = roomTypePriceSpecialService.selectByDay(roomTypeId, day);
		if (null != dto) {
			logger.info("room_type_id:{}", dto.getRoomTypeId());
		}
	}
}
