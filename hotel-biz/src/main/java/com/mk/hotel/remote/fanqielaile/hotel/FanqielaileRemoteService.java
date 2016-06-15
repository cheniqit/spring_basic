package com.mk.hotel.remote.fanqielaile.hotel;

import com.mk.framework.HttpUtils;
import com.mk.framework.JsonUtils;
import com.mk.framework.UrlUtils;
import com.mk.framework.security.MD5;
import com.mk.hotel.remote.fanqielaile.hotel.json.inn.InnList;
import com.mk.hotel.remote.fanqielaile.hotel.json.proxysalelist.SaleList;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomstatus.RoomList;
import com.mk.hotel.remote.fanqielaile.hotel.json.roomtype.RoomTypeList;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenqi on 16/5/11.
 */
@Service
public class FanqielaileRemoteService {

    private String DOMAIN = UrlUtils.getUrl("fanqielaile.domain");
    private final String HOTEL_LIST_QUERY = "/api/queryProxySaleList";
    private final String HOTEL_DETAIL_QUERY = "/api/queryInn";
    private final String HOTEL_ROOM_TYPE_QUERY = "/api/queryRoomType";

    private final String HOTEL_QUERY_STATUS = "/api/queryRoomStatus";

    public SaleList queryHotelList(){

        String signAndSoOn = this.getSignAndSoOn();
        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(HOTEL_LIST_QUERY)
                .append("?").append(signAndSoOn);

        //
        String remoteResult = HttpUtils.getData(url.toString());

        SaleList response = null;
        if (StringUtils.isNotBlank(remoteResult)) {
            response = JsonUtils.fromJson(remoteResult, SaleList.class);
        }

        return response;
    }

    public InnList queryInn(Long accountId) {

        if (null == accountId) {
            return null;
        }
        String signAndSoOn = this.getSignAndSoOn();
        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(HOTEL_DETAIL_QUERY)
                .append("?")
                .append(signAndSoOn)
                .append("&accountId=")
                .append(accountId);

        //
        String remoteResult = HttpUtils.getData(url.toString());

        InnList response = null;
        if (StringUtils.isNotBlank(remoteResult)) {
            response = JsonUtils.fromJson(remoteResult, InnList.class);
        }

        return response;
    }

    public RoomTypeList queryRoomType(Long accountId){

        if (null == accountId) {
            return null;
        }
        String signAndSoOn = this.getSignAndSoOn();
        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(HOTEL_ROOM_TYPE_QUERY)
                .append("?")
                .append(signAndSoOn)
                .append("&accountId=")
                .append(accountId);

        //
        String remoteResult = HttpUtils.getData(url.toString());
        RoomTypeList response = null;

        if (StringUtils.isNotBlank(remoteResult)) {
            response = JsonUtils.fromJson(remoteResult, RoomTypeList.class);
        }

        return response;
    }


    public RoomList queryRoomStatus(Long accountId, Date from, Date to) {

        if (null == accountId || null == from || null == to) {
            return null;
        }
        //
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strFrom = format.format(from);
        String strTo = format.format(to);
        //
        String signAndSoOn = this.getSignAndSoOn();
        StringBuilder url = new StringBuilder()
                .append(DOMAIN)
                .append(HOTEL_QUERY_STATUS)
                .append("?")
                .append(signAndSoOn)
                .append("&accountId=")
                .append(accountId)
                .append("&from=").append(strFrom)
                .append("&to=").append(strTo);

        //
        String remoteResult = HttpUtils.getData(url.toString());

        RoomList response = null;

        if (StringUtils.isNotBlank(remoteResult)) {
            response = JsonUtils.fromJson(remoteResult, RoomList.class);
        }

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
                .append("otaId=").append(otaId)
                .append("&timestamp=").append(timestamp)
                .append("&signature=").append(sign);

        return result.toString();
    }

    public String getSign(String otaId, String timestamp, String name, String pwd) {

        StringBuilder str = new StringBuilder()
                .append(otaId).append(timestamp).append(name).append(pwd);
        return MD5.MD5Encode(str.toString());
    }


    public static void main(String[] arg) {

        FanqielaileRemoteService service = new FanqielaileRemoteService();

        String signAndSoOn = service.getSignAndSoOn();
        System.out.println(signAndSoOn);

//        SaleList saleList = service.queryHotelList();
//
//        InnList innList = service.queryInn(49955l);
//
//        RoomTypeList roomTypeList = service.queryRoomType(49955l);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, 10);
//        Date nextDate = calendar.getTime();
//
//        RoomList roomList = service.queryRoomStatus(49955l, new Date(), nextDate);
//
//        System.out.println(signAndSoOn);

    }
}
