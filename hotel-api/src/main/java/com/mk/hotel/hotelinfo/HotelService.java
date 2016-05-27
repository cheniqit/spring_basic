package com.mk.hotel.hotelinfo;


import com.mk.hotel.hotelinfo.dto.HotelDto;

import java.util.List;

public interface HotelService {
    /**
     *
     * @param id
     * @return
     */
    HotelDto findById(Long id);

    HotelDto findByName(String hotelName);

    /**
     *
     * @param fangId
     * @return
     */
    HotelDto findByFangId(Long fangId);

    void mergePmsHotel(int pageNo);

    void saveOrUpdateByFangId(HotelDto hotelDto);

    List<HotelDto> findHotelByName(String hotelName, String cityCode);

    void deleteByFangId(Long id);
}
