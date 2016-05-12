package com.mk.pms.hoteldetail.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mk.framework.Constant;
import com.mk.framework.JsonUtils;
import com.mk.pms.hoteldetail.json.HotelDetail;
import com.mk.pms.hoteldetail.service.HotelDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by kirinli on 16/5/12.
 */
@Service
public class HotelDetailServiceImpl implements HotelDetailService{

    public HotelDetail queryHotelDetail(Long hotelId){
        HotelDetail hotelDetail = null;
        String hotelDetailJson = getHotelDetailJson(hotelId);

        if (StringUtils.isNotBlank(hotelDetailJson)){

            JSONObject respObj = JSONObject.parseObject(hotelDetailJson);

            if (null != respObj){
                JSONObject hotelDetailObj = respObj.getJSONObject("data");

                if (null != hotelDetailObj){
                    hotelDetail = JsonUtils.fromJson(hotelDetailObj.toJSONString(), HotelDetail.class);
                }
            }

        }

        return hotelDetail;

    }
    public static String getHotelDetailJson(Long hotelId){
        String url = String.format("%s%s", Constant.PMS_REMOTE_URL, Constant.HOTEL_DETAIL_QUERY_API);

        JSONObject queryHotelDetailJson = new JSONObject();
        queryHotelDetailJson.put("hotelid", hotelId);

        String resp = null;

        try {
           // resp = HttpUtils.doPost(url, queryHotelDetailJson.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
        resp="{\n" +
                "    \"result\": \"true\",\n" +
                "    \"data\": {\n" +
                "        \"citycode\": 310000,\n" +
                "        \"cityname\": \"s 上海市\",\n" +
                "        \"defaultleavetime\": \"120000\",\n" +
                "        \"detailaddr\": \"上海市闸北区灵石路697-3号\",\n" +
                "        \"discode\": 310108,\n" +
                "        \"districtname\": \"z 闸北区\",\n" +
                "        \"hotelname\": \"上轩商务酒店111\",\n" +
                "        \"hotelpics\": [\n" +
                "            {\n" +
                "                \"name\": \"def\",\n" +
                "                \"pic\": [\n" +
                "                    {\n" +
                "                        \"url\": \"https: //dn-imke-pro.qbox.me/Ful7MwN0e9LNUz_45zcwTff2fVz7\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"lobby\",\n" +
                "                \"pic\": [\n" +
                "                    {\n" +
                "                        \"url\": \"https: //dn-imke-pro.qbox.me/Fj6tS3nnglzqWdLUnum0XWQtf3CE\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"mainHousing\",\n" +
                "                \"pic\": [\n" +
                "                    {\n" +
                "                        \"url\": \"https: //dn-imke-pro.qbox.me/FlHopOEU7BZJNyPJJIyPkrFvQmYY\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"id\": 399,\n" +
                "        \"introduction\": \"性价比高，住宿环境、通风采光较好。\",\n" +
                "        \"latitude\": 31.283322,\n" +
                "        \"longitude\": 121.446101,\n" +
                "        \"pmstype\": null,\n" +
                "        \"provcode\": 310000,\n" +
                "        \"provincename\": \"s 上海市\",\n" +
                "        \"repairtime\": 1456588800000,\n" +
                "        \"retentiontime\": \"180000\",\n" +
                "        \"roomtypes\": [\n" +
                "            {\n" +
                "                \"id\": \"101\",\n" +
                "                \"area\": \"25\",\n" +
                "                \"bedtype\": \"2\",\n" +
                "                \"name\": \"标准间\",\n" +
                "                \"prepay\": \"1\",\n" +
                "                \"breakfast\": \"0\",\n" +
                "                \"roomnum\": \"5\",\n" +
                "                \"roomtypepics\": [\n" +
                "                    {\n" +
                "                        \"name\": \"toilet\",\n" +
                "                        \"pic\": [\n" +
                "                            {\n" +
                "                                \"url\": \"https://dn-imke-pro.qbox.me/FuNUfXg-_ue-BmKiR2s6mt9WdhQG\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\": \"def\",\n" +
                "                        \"pic\": [\n" +
                "                            {\n" +
                "                                \"url\": \"https://dn-imke-pro.qbox.me/FpW6xB_glrLmOMiPrFfI3WDZpH1Y\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\": \"bed\",\n" +
                "                        \"pic\": [\n" +
                "                            {\n" +
                "                                \"url\": \"https://dn-imke-pro.qbox.me/Fo1KDybi3FUb7S08pPDQdwbZbYLy\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"102\",\n" +
                "                \"area\": \"20\",\n" +
                "                \"bedtype\": \"2\",\n" +
                "                \"name\": \"双人间\",\n" +
                "                \"prepay\": \"1\",\n" +
                "                \"breakfast\": \"0\",\n" +
                "                \"roomnum\": \"5\",\n" +
                "                \"roomtypepics\": [\n" +
                "                    {\n" +
                "                        \"name\": \"toilet\",\n" +
                "                        \"pic\": [\n" +
                "                            {\n" +
                "                                \"url\": \"https://dn-imke-pro.qbox.me/FuNUfXg-_ue-BmKiR2s6mt9WdhQG\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\": \"def\",\n" +
                "                        \"pic\": [\n" +
                "                            {\n" +
                "                                \"url\": \"https://dn-imke-pro.qbox.me/FpW6xB_glrLmOMiPrFfI3WDZpH1Y\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\": \"bed\",\n" +
                "                        \"pic\": [\n" +
                "                            {\n" +
                "                                \"url\": \"https://dn-imke-pro.qbox.me/Fo1KDybi3FUb7S08pPDQdwbZbYLy\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        return resp;
    }

    public static void main(String[] args) {
        new HotelDetailServiceImpl().queryHotelDetail(2807l);
    }
}
