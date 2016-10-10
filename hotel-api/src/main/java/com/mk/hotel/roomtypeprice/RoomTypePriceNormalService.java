package com.mk.hotel.roomtypeprice;

import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalDto;

import java.util.List;

/**
 * Created by yanbaobin on 2016/10/9 15:17.
 */
public interface RoomTypePriceNormalService {

	/**
	 * 批量插入
	 * @param roomTypePriceNormalDtoList
	 * @return
	 */
	int batchInsert(List<RoomTypePriceNormalDto> roomTypePriceNormalDtoList);

	/**
	 * 更新或保存
	 * @param dto
	 * @return
	 */
	int saveOrUpdate(RoomTypePriceNormalDto dto);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	RoomTypePriceNormalDto selectById(Long id);
}
