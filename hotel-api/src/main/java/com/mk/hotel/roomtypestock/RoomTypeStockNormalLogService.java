package com.mk.hotel.roomtypestock;

import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalLogDto;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:42.
 */
public interface RoomTypeStockNormalLogService {
	/**
	 * 批量插入
	 * @param roomTypeStockNormalLogDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypeStockNormalLogDto> roomTypeStockNormalLogDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypeStockNormalLogDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypeStockNormalLogDto selectById(Long id);
}
