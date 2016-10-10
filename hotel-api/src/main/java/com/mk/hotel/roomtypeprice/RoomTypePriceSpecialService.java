package com.mk.hotel.roomtypeprice;

import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialDto;

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
}
