package com.mk.hotel.roomstates;

import com.mk.hotel.roomstates.dto.RoomStatesDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 16/10/9.
 */
public interface IRoomStatesService {

    List<RoomStatesDto> queryStates(Long roomTypeId, Date startDate, Date endDate, String token);

    int updatePriceAndStock(Long roomTypeId, Date startDate, Date endDate,
                            BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice,
                            BigDecimal totalStock, String optionId, String token);

    int updatePrice(Long roomTypeId, Date startDate, Date endDate,
                                    BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String optionId, String token);

    int updateStock(Long roomTypeId, Date startDate, Date endDate,
                                    BigDecimal totalStock, String optionId, String token);
}
