package com.mk.hotel.roomtypestock;

import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:42.
 */
public interface RoomTypeStockSpecialService {

	/**
	 * 批量插入
	 * @param roomTypeStockSpecialDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypeStockSpecialDto> roomTypeStockSpecialDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypeStockSpecialDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypeStockSpecialDto selectById(Long id);

	/**
	 *
	 * @param roomTypeId
	 * @param day
	 * @return
	 */
	RoomTypeStockSpecialDto selectByDay(Long roomTypeId, Date day);
	/**
	 *
	 * @param roomTypeId
	 * @param date
	 * @param totalNumber
	 * @param operator
     * @return
     */
	int updateRoomTypeStockSpecialRule(Long roomTypeId, Date date, BigDecimal totalNumber, String operator);
}
