package com.mk.taskfactory.biz.impl;


import com.mk.framework.proxy.http.HttpUtil;
import com.mk.taskfactory.api.BasePriceService;
import com.mk.taskfactory.api.dtos.TBasePriceDto;
import com.mk.taskfactory.biz.mapper.ots.BasePriceMapper;
import com.mk.taskfactory.biz.utils.HttpUtils;
import com.mk.taskfactory.biz.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasePriceServiceImpl implements BasePriceService {

    @Autowired
    private BasePriceMapper basePriceMapper;
    @Override
    public TBasePriceDto selectByPrimaryKey(Long id) {
        return basePriceMapper.selectByPrimaryKey(id);
    }

    @Override
    public TBasePriceDto findByRoomtypeId(Long roomTypeId) {
        return basePriceMapper.findByRoomtypeId(roomTypeId);
    }

    @Override
    public int saveBasePriceService(TBasePriceDto dto) {
        return basePriceMapper.saveBasePriceDto(dto);
    }

    @Override
    public int updateBasePriceService(TBasePriceDto dto) {
        return basePriceMapper.updateBasePriceDto(dto);
    }
    public int deleteBasePriceByRoomType(Integer roomTypeId){
       return basePriceMapper.deleteBasePriceByRoomType(roomTypeId);
    }
    public static void main(String[] args) {
        String datas = HttpUtil.doGetNoProxy("http://restapi.amap.com/v3/place/text?key=df3cf793df1bd2c257483c11e7176923&keywords=&types=10&city&children=1&offset=20&page=10");
        Map<String,String> dataMap= JsonUtils.jsonToMap(datas);
        String suggestion= dataMap.get("suggestion");
        Map<String,String> suggestionMap= JsonUtils.jsonToMap(suggestion);
        String cities= suggestionMap.get("cities");
        List<Object> citieList=JsonUtils.jsonToList(cities);
        Integer sum=0;
        Map<String,String> resultMap=new HashMap<String, String>();
        for (Object obj:citieList){
            Map<String,String> objMap= JsonUtils.jsonToMap(obj.toString());
            resultMap.put(objMap.get("name"),objMap.get("num"));
            sum+=Integer.valueOf(objMap.get("num"));
        }
        System.out.println("sum="+sum.toString());
        System.out.println(resultMap);
    }
}
