package com.mk.hotel.hotelinfo;


import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;

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
    HotelDto findByFangId(Long fangId, HotelSourceEnum hotelSourceEnum);

    void mergePmsHotel(int pageNo);

    void mergePmsHotelByHotelId(Long hotelId);

    void saveOrUpdateByFangId(HotelDto hotelDto, HotelSourceEnum hotelSourceEnum);

    List<HotelDto> findHotelByName(String hotelName, String cityCode);

    void deleteByFangId(Long id, HotelSourceEnum hotelSourceEnum);

    void mergeFanqieHotel ();
}
