package com.mk.hotel.remote.pms.hotel.json;

import com.mk.framework.json.JsonUtils;
import org.junit.Test;

/**
 * Created by chenqi on 16/5/16.
 */
public class HotelPriceResponseTest {


    @Test
    public void testJson(){
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"roomTypePrices\": [\n" +
                "            {\n" +
                "                \"priceInfos\": [\n" +
                "                    {\n" +
                "                        \"cost\": \"{\\\"channelprice\\\":545.00,\\\"saleprice\\\":545.00}\",\n" +
                "                        \"date\": \"20160512\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"roomtypeid\": 30033\n" +
                "            },\n" +
                "            {\n" +
                "                \"priceInfos\": [\n" +
                "                    {\n" +
                "                        \"cost\": \"{\\\"channelprice\\\":10.00,\\\"saleprice\\\":10.00}\",\n" +
                "                        \"date\": \"20160512\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"roomtypeid\": 30034\n" +
                "            },\n" +
                "            {\n" +
                "                \"priceInfos\": [\n" +
                "                    {\n" +
                "                        \"cost\": \"{\\\"channelprice\\\":10.00,\\\"saleprice\\\":10.00}\",\n" +
                "                        \"date\": \"20160512\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"roomtypeid\": 30004\n" +
                "            },\n" +
                "            {\n" +
                "                \"priceInfos\": [\n" +
                "                    {\n" +
                "                        \"cost\": \"{\\\"channelprice\\\":10.00,\\\"saleprice\\\":10.00}\",\n" +
                "                        \"date\": \"20160512\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"roomtypeid\": 29976\n" +
                "            },\n" +
                "            {\n" +
                "                \"priceInfos\": [\n" +
                "                    {\n" +
                "                        \"cost\": \"{\\\"channelprice\\\":10.00,\\\"saleprice\\\":10.00}\",\n" +
                "                        \"date\": \"20160512\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"roomtypeid\": 29977\n" +
                "            },\n" +
                "            {\n" +
                "                \"priceInfos\": [\n" +
                "                    {\n" +
                "                        \"cost\": \"{\\\"channelprice\\\":10.00,\\\"saleprice\\\":10.00}\",\n" +
                "                        \"date\": \"20160512\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"roomtypeid\": 29967\n" +
                "            },\n" +
                "            {\n" +
                "                \"priceInfos\": [\n" +
                "                    {\n" +
                "                        \"cost\": \"{\\\"channelprice\\\":10.00,\\\"saleprice\\\":10.00}\",\n" +
                "                        \"date\": \"20160512\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"roomtypeid\": 29983\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"errorCode\": null,\n" +
                "    \"errorMessage\": null,\n" +
                "    \"result\": \"true\"\n" +
                "}";
        HotelPriceResponse response = JsonUtils.fromJson(json, HotelPriceResponse.class);
        System.out.print(response);
    }

}