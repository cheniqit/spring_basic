package com.mk.hotel.remote.fanqielaile.hotel;

import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.UrlUtils;
import com.mk.framework.security.MD5;
import com.mk.hotel.remote.pms.hotel.json.HotelQueryListResponse;
import org.springframework.stereotype.Service;

/**
 * Created by chenqi on 16/5/11.
 */
@Service
public class FanqielaileRemoteService {

    private final String HOTEL_LIST_QUERY = "/api/queryProxySaleList";
    private final String HOTEL_DETAIL_QUERY = "/api/queryInn";
    private final String HOTEL_ROOM_TYPE_QUERY = "/api/queryRoomType";

    private final String HOTEL_QUERY_STATUS = "/api/queryRoomStatus";

    public HotelQueryListResponse queryHotelList(){

        String domain = UrlUtils.getUrl("fanqielaile.otaid");

        String remoteResult = HttpUtils.getData("");
        HotelQueryListResponse response = JsonUtils.fromJson(remoteResult, HotelQueryListResponse.class);

        return response;
    }

    public String getSignAndSoOn() {

        String otaId = UrlUtils.getUrl("fanqielaile.otaid");
        String timestamp = String.valueOf(System.currentTimeMillis());
        String name = UrlUtils.getUrl("fanqielaile.name");
        String pwd = UrlUtils.getUrl("fanqielaile.pwd");
        String sign = this.getSign(otaId,timestamp,name,pwd);

        //
        StringBuilder result = new StringBuilder()
                .append("otaid=").append(otaId)
                .append("&timestamp=").append(timestamp)
                .append("&signature=").append(sign);

        return result.toString();
    }

    public String getSign(String otaId, String timestamp, String name, String pwd) {

        StringBuilder str = new StringBuilder()
                .append(otaId).append(timestamp).append(name).append(pwd);
        return MD5.MD5Encode(str.toString());
    }


    public static void main(String []arg) {
       String s = new FanqielaileRemoteService().getSignAndSoOn();
        System.out.println(s);
    }
}
