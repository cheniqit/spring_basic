package com.mk.hotel.hotelinfo;


import com.mk.hotel.hotelinfo.dto.HotelDto;

public interface HotelService {
    /**
     *
     * @param id
     * @return
     */
    HotelDto findById(Long id);

    /**
     *
     * @param fangId
     * @return
     */
    HotelDto findByFangId(Long fangId);

    void mergePmsHotel(int pageNo);
}
