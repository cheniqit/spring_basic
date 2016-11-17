package com.mk.hotel.statistics;

import com.mk.hotel.statistics.dto.ProvinceCountDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huangjie on 16/9/20.
 */
public interface IStatisticsService {
    /**
     *
     * @param date
     * @return
     */
    Integer queryTotal(Date date);

    /**
     *
     * @return
     */
    List<Map<String, Object>> queryBySourceType();

    /**
     *
     * @param date
     * @return
     */
    Map<String, ProvinceCountDto> queryTotalByDateGroupProvCode(Date date);

    /**
     *
     * @param sourceType
     * @return
     */
    Map<String, ProvinceCountDto> queryTotalBySourceTypeGroupProvCode(Integer sourceType);
}
