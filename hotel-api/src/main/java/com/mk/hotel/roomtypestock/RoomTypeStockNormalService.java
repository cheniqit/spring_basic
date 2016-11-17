package com.mk.hotel.roomtypestock;

import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalDto;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/9 15:17.
 */
public interface RoomTypeStockNormalService {

	/**
	 * 批量插入
	 * @param roomTypeStockNormalDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypeStockNormalDto> roomTypeStockNormalDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypeStockNormalDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypeStockNormalDto selectById(Long id);
}
