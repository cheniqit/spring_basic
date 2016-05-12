package com.mk.hotel.remote.hotel.json;

import com.mk.framework.JsonUtils;
import com.mk.hotel.remote.common.FbbCommonResponse;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by chenqi on 16/5/11.
 */
public class HotelPriceResponse extends FbbCommonResponse{
    private HotelPrice data;

    public HotelPrice getData() {
        return data;
    }

    public void setData(HotelPrice data) {
        this.data = data;
    }

    class HotelPrice{
        private List<RoomTypePrices> roomTypePrices;

        public List<RoomTypePrices> getRoomTypePrices() {
            return roomTypePrices;
        }

        public void setRoomTypePrices(List<RoomTypePrices> roomTypePrices) {
            this.roomTypePrices = roomTypePrices;
        }
    }

    class RoomTypePrices {

        private List<PriceInfos> priceInfos;
        private int roomtypeid;


        public List<PriceInfos> getPriceInfos() {
            return priceInfos;
        }

        public void setPriceInfos(List<PriceInfos> priceInfos) {
            this.priceInfos = priceInfos;
        }

        public void setRoomtypeid(int roomtypeid) {
            this.roomtypeid = roomtypeid;
        }
        public int getRoomtypeid() {
            return roomtypeid;
        }

    }

    class PriceInfos {

        private String cost;
        private String date;


        public void setCost(String cost) {
            this.cost = cost;
        }
        public String getCost() {
            return cost;
        }


        public void setDate(String date) {
            this.date = date;
        }
        public String getDate() {
            return date;
        }

        public Cost getCostObject(){
            if(StringUtils.isNotBlank(cost)){
                return JsonUtils.fromJson(this.getCost(), Cost.class);
            }
            return null;
        }

    }


    public static void main(String[] args){
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
