package com.mk.hotel.roomstates;

import com.mk.hotel.roomstates.dto.RoomStatesDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 16/10/9.
 */
public interface IRoomStatesService {

    List<RoomStatesDto> queryStates(Long roomTypeId, Date startDate, Date endDate);

    int updatepriceandstock(Long roomTypeId, Date startDate, Date endDate,
                                            BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice,
                                            BigDecimal totalStock);

    int updatePrice(Long roomTypeId, String startDate, String endDate,
                                    BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice);

    int updateStock(Long roomTypeId, String startDate, String endDate,
                                    BigDecimal totalStock);
}
