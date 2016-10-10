package com.mk.hotel.roomtypeprice.service.impl;

import com.mk.common.BaseTest;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalLogDto;
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
public class RoomTypePriceNormalLogServiceTest extends BaseTest {
	private final Logger logger = LoggerFactory.getLogger(RoomTypePriceNormalLogServiceTest.class);

	@Autowired
	private RoomTypePriceNormalLogServiceImpl roomTypePriceNormalLogService;

	@Test
	public void testBatchInsert() {
		List<RoomTypePriceNormalLogDto> list = new ArrayList<RoomTypePriceNormalLogDto>();
		for (int i = 0; i < 10; i++) {
			RoomTypePriceNormalLogDto dto = new RoomTypePriceNormalLogDto();
			dto.setId(new Long(i));
			dto.setRoomTypeId(new Long(i));
			dto.setCreateBy("id");
			dto.setCreateDate(new Date());
			dto.setIsValid("t");
			dto.setUpdateBy("id");
			dto.setUpdateDate(new Date());

			dto.setMonMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setMonSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setMonSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			dto.setTueMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setTueSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setTueSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			dto.setWedMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setWedSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setWedSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			dto.setThuMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setThuSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setThuSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			dto.setFriMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setFriSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setFriSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			dto.setSatMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setSatSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setSatSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			dto.setSunMarketPrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setSunSalePrice(BigDecimal.valueOf(Math.random() * 100 + i));
			dto.setSunSettlePrice(BigDecimal.valueOf(Math.random() * 100 + i));

			list.add(dto);
		}
		int row = roomTypePriceNormalLogService.batchInsert(list);
		logger.info("row:{}", row);
	}

	@Test
	public void testSelectById() {
		RoomTypePriceNormalLogDto dto = roomTypePriceNormalLogService.selectById(new Long(8));
		logger.info("id:{},date:{}", dto.getId(), dto.getCreateDate());
	}

	@Test
	public void testSaveOrUpdate() {
		RoomTypePriceNormalLogDto dto = roomTypePriceNormalLogService.selectById(new Long(8));
		dto.setIsValid("f");
		int affect = roomTypePriceNormalLogService.saveOrUpdate(dto);
		logger.info("row:{}", affect);
	}
}
