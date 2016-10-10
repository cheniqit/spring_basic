package com.mk.hotel.roomtypeprice;

import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalLogDto;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:42.
 */
public interface RoomTypePriceNormalLogService {

	/**
	 * 批量插入
	 * @param roomTypePriceNormalLogDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypePriceNormalLogDto> roomTypePriceNormalLogDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypePriceNormalLogDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypePriceNormalLogDto selectById(Long id);
}
