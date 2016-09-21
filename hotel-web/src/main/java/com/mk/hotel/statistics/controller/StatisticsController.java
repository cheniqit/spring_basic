package com.mk.hotel.statistics.controller;

import com.mk.hotel.statistics.IStatisticsService;
import com.mk.hotel.statistics.dto.ProvinceCountDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;

    @RequestMapping(value = "/total", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> total() {

        //format date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = format.format(new Date());

        //data
        Integer newCount = statisticsService.queryTotal(new Date());

        Integer allCount = statisticsService.queryTotal(null);

        List<Map<String, Object>> sourceTypeList = statisticsService.queryBySourceType();

        //dataList
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

        //
        Map<String, Object> newCountMap = new HashMap<String, Object>();
        newCountMap.put("name", "新上线酒店");
        newCountMap.put("countValue", newCount);
        dataList.add(newCountMap);

        //
        Map<String, Object> allCountMap = new HashMap<String, Object>();
        allCountMap.put("name", "上线酒店总数");
        allCountMap.put("countValue", allCount);
        dataList.add(allCountMap);

        dataList.addAll(sourceTypeList);

        //
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("data", dataList);
        result.put("date", strDate);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }


    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> detail(String isNew, Integer sourceType) {

        Map<String, ProvinceCountDto> countDtoMap = null;
        if ("T".equals(isNew)) {
            countDtoMap = this.statisticsService.queryTotalByDateGroupProvCode(new Date());
        } else {
            if (null == sourceType) {
                countDtoMap = this.statisticsService.queryTotalByDateGroupProvCode(null);
            } else {
                countDtoMap = this.statisticsService.queryTotalBySourceTypeGroupProvCode(sourceType);
            }
        }

        //
        HashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("success", "T");
        result.put("data", countDtoMap);
        return new ResponseEntity<HashMap<String, Object>>(result, HttpStatus.OK);
    }

}

