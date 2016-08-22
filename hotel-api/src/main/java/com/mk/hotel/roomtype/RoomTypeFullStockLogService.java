package com.mk.hotel.roomtype;

import com.mk.hotel.roomtype.dto.RoomTypeFullStockLogDto;

import java.util.Date;
import java.util.List;

public interface RoomTypeFullStockLogService {
    /**
     * 保存日志
     * @param logDto
     */
    int saveLog(RoomTypeFullStockLogDto logDto);

    /**
     * 查询日志
     * @param day
     * @return
     */
    List<RoomTypeFullStockLogDto> queryByDay (Date day);
}
