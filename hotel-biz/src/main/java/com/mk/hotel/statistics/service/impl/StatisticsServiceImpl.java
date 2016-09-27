package com.mk.hotel.statistics.service.impl;

import com.mk.hotel.hotelinfo.enums.HotelSourceEnum;
import com.mk.hotel.hotelinfo.mapper.HotelMapper;
import com.mk.hotel.remote.ots.CityRemoteService;
import com.mk.hotel.remote.ots.json.ProvinceDto;
import com.mk.hotel.statistics.IStatisticsService;
import com.mk.hotel.statistics.dto.ProvinceCountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangjie on 16/9/20.
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {
    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private CityRemoteService cityRemoteService;

    public Integer queryTotal(Date date) {

        String strDate = null;
        if (null == date) {
            // strDate == null
        } else {
            //format date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            strDate = format.format(date);
        }

        //
        int count = this.hotelMapper.countByCreateDate(strDate);

        return count;
    }

    public List<Map<String, Object>> queryBySourceType() {

        List<Map<String, Object>> result = this.hotelMapper.countBySourceType();

        for (Map<String, Object> objMap : result) {
            Integer key = (Integer) objMap.get("strKey");

            //
            HotelSourceEnum hotelSourceEnum = HotelSourceEnum.getById(key);
            if (null == hotelSourceEnum) {
                continue;
            }

            String name = hotelSourceEnum.getName() + "酒店";
            objMap.put("name", name);
            objMap.put("sourceType", key);
        }
        return result;
    }

    public Map<String, ProvinceCountDto> queryTotalByDateGroupProvCode(Date date) {

        //
        Map<String, ProvinceCountDto> provCodeCountMap = this.getProvCodeCountMap();

        //
        String strDate = null;
        if (null == date) {
            // strDate == null
        } else {
            //format date
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.format(date);
        }
        List<Map<String, Object>> result = this.hotelMapper.countByCreateDateGroupProvCode(strDate);

        for (Map<String, Object> objectMap : result) {
            String code = (String) objectMap.get("strKey");
            Long count = (Long) objectMap.get("countValue");

            //
            ProvinceCountDto countDto = provCodeCountMap.get(code);
            if (null == countDto) {
                continue;
            }
            countDto.setCount(count);
        }

        return provCodeCountMap;
    }

    public Map<String, ProvinceCountDto> queryTotalBySourceTypeGroupProvCode(Integer sourceType) {

        //
        Map<String, ProvinceCountDto> provCodeCountMap = this.getProvCodeCountMap();

        List<Map<String, Object>> result = this.hotelMapper.countBySourceTypeGroupProvCode(sourceType);
        for (Map<String, Object> objectMap : result) {
            String code = (String) objectMap.get("strKey");
            Long count = (Long) objectMap.get("countValue");

            //
            ProvinceCountDto countDto = provCodeCountMap.get(code);
            if (null == countDto) {
                continue;
            }
            countDto.setCount(count);
        }

        return provCodeCountMap;
    }


    private Map<String, ProvinceCountDto> getProvCodeCountMap() {
        Map<String, ProvinceCountDto> provCodeCountMap = new LinkedHashMap<String, ProvinceCountDto>();

        //
        List<ProvinceDto> provinceDtoList = this.cityRemoteService.findProvince();
        for (ProvinceDto provinceDto : provinceDtoList) {

            ProvinceCountDto dto = new ProvinceCountDto();
            dto.setId(provinceDto.getProid());
            dto.setCode(provinceDto.getCode());
            dto.setName(provinceDto.getProname());
            dto.setCount(0l);
            provCodeCountMap.put(provinceDto.getCode(), dto);
        }

        return provCodeCountMap;
    }
}
