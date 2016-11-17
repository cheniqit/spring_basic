package com.mk.hotel.roomtypeprice;

import com.mk.hotel.roomtypeprice.dto.RoomTypePriceSpecialLogDto;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:43.
 */
public interface RoomTypePriceSpecialLogService {

	/**
	 * 批量插入
	 * @param roomTypePriceSpecialLogDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypePriceSpecialLogDto> roomTypePriceSpecialLogDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypePriceSpecialLogDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypePriceSpecialLogDto selectById(Long id);
}
