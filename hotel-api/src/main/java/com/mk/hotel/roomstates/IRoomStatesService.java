package com.mk.hotel.roomstates;

import com.mk.hotel.hotelinfo.dto.UpdatePriceAndStock;
import com.mk.hotel.roomstates.dto.RoomStatesDto;
import com.mk.hotel.roomtypeprice.dto.RoomTypePriceNormalDto;
import com.mk.hotel.roomtypestock.dto.RoomTypeStockNormalDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by huangjie on 16/10/9.
 */
public interface IRoomStatesService {

    List<RoomStatesDto> queryStates(Long roomTypeId, Date startDate, Date endDate);

    int updatePriceAndStock(Long roomTypeId, Date startDate, Date endDate,
                            BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice,
                            Long totalStock, String operatorId);

    int updatePrice(Long roomTypeId, Date startDate, Date endDate,
                                    BigDecimal marketPrice, BigDecimal salePrice, BigDecimal settlePrice, String operatorId);

    int updateStock(Long roomTypeId, Date startDate, Date endDate,
                                    Long totalStock, String operatorId);

    int updateNormalPrice(RoomTypePriceNormalDto dto, String operatorId);

    int updateNormalStock(RoomTypeStockNormalDto dto, String operatorId);

    void updatePriceAndStock(UpdatePriceAndStock updatePriceAndStock);

    boolean checkToken(Long roomTypeId, String token);

}
