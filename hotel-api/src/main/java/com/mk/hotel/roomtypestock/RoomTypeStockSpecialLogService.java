package com.mk.hotel.roomtypestock;

import com.mk.hotel.roomtypestock.dto.RoomTypeStockSpecialLogDto;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/10 9:43.
 */
public interface RoomTypeStockSpecialLogService {

	/**
	 * 批量插入
	 * @param roomTypeStockSpecialLogDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypeStockSpecialLogDto> roomTypeStockSpecialLogDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypeStockSpecialLogDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypeStockSpecialLogDto selectById(Long id);
}
