package com.mk.hotel.hotelinfo.service.impl;

import com.dianping.cat.Cat;
import com.mk.framework.proxy.http.RedisUtil;
import com.mk.hotel.hotelinfo.HotelService;
import com.mk.hotel.hotelinfo.dto.HotelDto;
import com.mk.hotel.hotelinfo.enums.HotelCacheEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.hotelinfo.model.Hotel;
import com.mk.hotel.hotelinfo.model.HotelExample;
import com.mk.hotel.remote.pms.hotel.HotelRemoteService;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListRequest;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import com.mk.hotel.roomtype.dto.RoomTypeDto;
import com.mk.hotel.roomtype.enums.RoomTypeCacheEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by chenqi on 16/5/9.
 */
@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelMapper hotelMapper;
    @Autowired
    private HotelRemoteService hotelRemoteService;
    @Autowired
    private HotelProxyService hotelProxyService;

    @Override
    public HotelDto findById(Long id) {

        Hotel hotel = hotelMapper.selectByPrimaryKey(id);

        HotelDto dto = new HotelDto();
        BeanUtils.copyProperties(hotel, dto);
        return dto;
    }


    public HotelDto findByFangId(Long fangId) {

        HotelExample hotelExample = new HotelExample();
        hotelExample.createCriteria().andFangIdEqualTo(fangId);
        List<Hotel> hotelList = hotelMapper.selectByExample(hotelExample);

        if (hotelList.isEmpty()) {
            return null;
        }

        HotelDto dto = new HotelDto();
        BeanUtils.copyProperties(hotelList.get(0), dto);
        return dto;
    }


    public void mergePmsHotel(){
        //查询pms所有的信息房间列表id
        int pageNo = 1;
        mergePmsHotel(pageNo);
    }

    public void mergePmsHotel(int pageNo){
        HotelQueryListRequest request = new HotelQueryListRequest(pageNo);
        HotelQueryListResponse response = hotelRemoteService.queryHotelList(request);
        if(response.getData() == null || CollectionUtils.isEmpty(response.getData().getHotels())){
            return;
        }
        List<HotelQueryListResponse.HotelInfo> hotelInfoList = response.getData().getHotels();
        hotelProxyService.saveHotel(hotelInfoList);
        pageNo++;
        mergePmsHotel(pageNo);
    }


    public void updateRedisHotel(String hotelId, HotelDto hotelDto) {
        if (StringUtils.isBlank(hotelId) || null == hotelDto || null == hotelDto.getId()) {
            return;
        }

        //
        Jedis jedis = null;
        try {
            //
            jedis = RedisUtil.getJedis();
            String hotelKeyName = HotelCacheEnum.getHotelKeyName(hotelId);
            //TODO add
            jedis.set(hotelKeyName, null);

        } catch (Exception e) {
            e.printStackTrace();
            Cat.logError(e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }




}
