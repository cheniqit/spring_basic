package com.mk.hotel.order.controller.json;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenqi on 16/6/8.
 */
public class PushRoomTypeTest {

    @Test
    public void testXml(){
        PushRoomType pushRoomType = new PushRoomType();


        List<PushRoomType.RoomDetail> roomDetailList = new ArrayList();
        PushRoomType.RoomDetail roomDetail = new PushRoomType.RoomDetail();
        roomDetail.setPriRoomPrice("2.0");
        roomDetail.setRoomDate("2013-01-01");
        roomDetail.setRoomNum("1");
        roomDetail.setRoomPrice("1.0");
        roomDetailList.add(roomDetail);

        List<PushRoomType.RoomDetail> roomDetailList2 = new ArrayList();
        PushRoomType.RoomDetail roomDetail2 = new PushRoomType.RoomDetail();
        roomDetail2.setPriRoomPrice("2.0");
        roomDetail2.setRoomDate("2013-01-01");
        roomDetail2.setRoomNum("1");
        roomDetail2.setRoomPrice("1.0");
        roomDetailList2.add(roomDetail2);

        List<PushRoomType.RoomType> roomTypeList = new ArrayList();
        PushRoomType.RoomType roomType = new PushRoomType.RoomType();
        roomType.setAccountId("2");
        roomType.setRoomTypeId("456");
        roomType.setRoomTypeName("房型1");
        roomTypeList.add(roomType);
        roomType.setRoomDetails(roomDetailList);

        PushRoomType.RoomType roomType1 = new PushRoomType.RoomType();
        roomType1.setAccountId("2");
        roomType1.setRoomTypeId("456");
        roomType1.setRoomTypeName("房型1");

        roomType1.setRoomDetails(roomDetailList2);
        roomTypeList.add(roomType1);




        pushRoomType.setRoomType(roomTypeList);
        XStream xstream = new XStream(new DomDriver());
        xstream.autodetectAnnotations(true);
        // 格式化输出
        String xmltostr = xstream.toXML(pushRoomType);
        System.out.println(xmltostr);

        PushRoomType pushRoomType3 = (PushRoomType) xstream.fromXML(xmltostr, new PushRoomType());
        System.out.println(pushRoomType3);
    }

    @Test
    public void xmlToObject(){
       String xml = "<PushRoomType>\n" +
               "  <list>\n" +
               "    <RoomType>\n" +
               "      <AccountId>2</AccountId>\n" +
               "      <RoomTypeId>456</RoomTypeId>\n" +
               "      <RoomTypeName>房型1</RoomTypeName>\n" +
               "      <RoomDetails>\n" +
               "        <RoomDetail>\n" +
               "          <RoomDate>2013-01-01</RoomDate>\n" +
               "          <RoomPrice>1.0</RoomPrice>\n" +
               "          <PriRoomPrice>2.0</PriRoomPrice>\n" +
               "          <RoomNum>1</RoomNum>\n" +
               "        </RoomDetail>\n" +
               "      </RoomDetails>\n" +
               "    </RoomType>\n" +
               "    <RoomType>\n" +
               "      <AccountId>2</AccountId>\n" +
               "      <RoomTypeId>456</RoomTypeId>\n" +
               "      <RoomTypeName>房型1</RoomTypeName>\n" +
               "      <RoomDetails>\n" +
               "        <RoomDetail>\n" +
               "          <RoomDate>2013-01-01</RoomDate>\n" +
               "          <RoomPrice>1.0</RoomPrice>\n" +
               "          <PriRoomPrice>2.0</PriRoomPrice>\n" +
               "          <RoomNum>1</RoomNum>\n" +
               "        </RoomDetail>\n" +
               "      </RoomDetails>\n" +
               "    </RoomType>\n" +
               "  </list>\n" +
               "</PushRoomType>";
        System.out.println(xml);
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("PushRoomType", PushRoomType.class);
        xstream.autodetectAnnotations(true);
        PushRoomType pushRoomType = (PushRoomType) xstream.fromXML(xml, new PushRoomType());
        System.out.println(pushRoomType);
    }
}