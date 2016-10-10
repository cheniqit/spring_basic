package com.mk.hotel.roomtypeprice;

import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:42.
 */
public interface RoomTypePriceSpecialService {

	/**
	 * 批量插入
	 * @param roomTypePriceSpecialDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypePriceSpecialDto> roomTypePriceSpecialDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypePriceSpecialDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypePriceSpecialDto selectById(Long id);

	/**
	 *
	 * @param roomTypeId
	 * @param day
     * @return
     */
	RoomTypePriceSpecialDto selectByDay(Long roomTypeId, Date day);
	/**
	 *
	 * @param roomTypeId
	 * @param date
	 * @param marketPrice
	 * @param salePrice
	 * @param settlePrice
	 * @param operator
     * @return
     */
	int updateRoomTypePriceSpecialRule(Long roomTypeId, Date date, BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operator);
}
