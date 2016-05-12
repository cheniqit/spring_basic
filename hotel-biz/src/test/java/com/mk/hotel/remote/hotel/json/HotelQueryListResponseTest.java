package com.mk.hotel.remote.hotel.json;

import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.common.FbbCommonResponse;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chenqi on 16/5/12.
 */
public class HotelQueryListResponseTest {

    @Test
    public void testJson(){
        HotelQueryListResponse hotelQueryListResponse = new HotelQueryListResponse();
        String json = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"citycode\": 120000,\n" +
                "      \"cityname\": \"T 天津市\",\n" +
                "      \"detailaddr\": \"天津市河北区区博爱道君临天下2801室\",\n" +
                "      \"discode\": 120103,\n" +
                "      \"districtname\": \"H 河西区\",\n" +
                "      \"hotelname\": \"上海华谕宾馆\",\n" +
                "      \"hotelpics\": null,\n" +
                "      \"hoteltype\": 1,\n" +
                "      \"id\": 413,\n" +
                "      \"latitude\": 39.132462,\n" +
                "      \"longitude\": 117.197723,\n" +
                "      \"provcode\": 120000,\n" +
                "      \"provincename\": \"T 天津市\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"citycode\": 110000,\n" +
                "      \"cityname\": \"B 北京市\",\n" +
                "      \"detailaddr\": \"北京市西城区宁波路14号脑子里辛苦\",\n" +
                "      \"discode\": 110102,\n" +
                "      \"districtname\": \"X 西城区\",\n" +
                "      \"hotelname\": \"北京之美主题酒店\",\n" +
                "      \"hotelpics\": null,\n" +
                "      \"hoteltype\": 2,\n" +
                "      \"id\": 2807,\n" +
                "      \"latitude\": 30.760473,\n" +
                "      \"longitude\": 108.436281,\n" +
                "      \"provcode\": 110000,\n" +
                "      \"provincename\": \"B 北京市\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"citycode\": 340700,\n" +
                "      \"cityname\": \"T 铜陵市\",\n" +
                "      \"detailaddr\": \"北京\",\n" +
                "      \"discode\": 340711,\n" +
                "      \"districtname\": \"J 郊区\",\n" +
                "      \"hotelname\": \"上轩商务酒店111\",\n" +
                "      \"hotelpics\": null,\n" +
                "      \"hoteltype\": 3,\n" +
                "      \"id\": 399,\n" +
                "      \"latitude\": 31.283322,\n" +
                "      \"longitude\": 121.446101,\n" +
                "      \"provcode\": 340000,\n" +
                "      \"provincename\": \"A 安徽省\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"citycode\": 310000,\n" +
                "      \"cityname\": \"S 上海市\",\n" +
                "      \"detailaddr\": \"灵石路679-3号\",\n" +
                "      \"discode\": 310108,\n" +
                "      \"districtname\": \"Z 闸北区\",\n" +
                "      \"hotelname\": \"上轩商务酒店\",\n" +
                "      \"hotelpics\": null,\n" +
                "      \"hoteltype\": 3,\n" +
                "      \"id\": 2813,\n" +
                "      \"latitude\": 29.58339,\n" +
                "      \"longitude\": 106.497452,\n" +
                "      \"provcode\": 310000,\n" +
                "      \"provincename\": \"S 上海市\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"errorCode\": null,\n" +
                "  \"errorMessage\": null,\n" +
                "  \"result\": \"true\"\n" +
                "}";
        HotelQueryListResponse fbbCommonResponse = JsonUtils.fromJson(json, HotelQueryListResponse.class);
        Assert.assertNotNull(fbbCommonResponse);

    }

}