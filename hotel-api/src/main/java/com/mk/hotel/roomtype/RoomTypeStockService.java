package com.mk.hotel.roomtype;

import java.util.Date;

public interface RoomTypeStockService {

    String lock(String hotelId, String roomTypeId, Date day, long maxWaitTimeOut);
}
