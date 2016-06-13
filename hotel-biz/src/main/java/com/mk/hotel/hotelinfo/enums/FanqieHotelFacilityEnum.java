package com.mk.hotel.hotelinfo.enums;

/**
 * Created by kirinli on 16/5/12.
 */
public enum FanqieHotelFacilityEnum {
    DEFAULT(-1,"无", HotelFacilityEnum.DEFAULT),
    FREE_WIFI(1, "免费 WIFI", HotelFacilityEnum.FREE_WIRED),
    Kitchen(2,"自助厨房", null),
    bar(3, "酒吧", null),
    BICYCLE_RENTAL(4, "自行车租赁", null),
    PUBLIC_COMPUTER(5, "公共电脑", null),
    TRANSFER_SERVICE(6, "接送服务", null),
    TOURISM_CONSULTING(7, "旅游咨询", null),
    PERSONAL_EFFECTS(8, "物品寄存", null),
    LAUNDRY(9, "洗衣房", null),
    FAX_PRINT(10, "传真/打印", null),
    FILM(11, "电影", null),
    CONFERENCE_ROOM(12, "会议室", null),
    LIBRARY(13, "图书室", null),
    TEAHOUSE(14, "茶室", null),
    BILLIARDS(15, "台球", null),
    CAFE(16, "咖啡厅", null),
    FREE_TRAVEL_MAP(17, "免费旅游交通图", null),
    Room_service(18, "送餐服务", null),

    ;

    private Integer id;
    private String name;
    private HotelFacilityEnum hotelFacilityEnuml;

    /**
     *
     * @param id
     * @param name
     */
    private FanqieHotelFacilityEnum(Integer id, String name, HotelFacilityEnum hotelFacilityEnuml) {
        this.id = id;
        this.name = name;
        this.hotelFacilityEnuml = hotelFacilityEnuml;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param id
     * @return
     */
    public static FanqieHotelFacilityEnum getById(Integer id){
        for (FanqieHotelFacilityEnum temp : FanqieHotelFacilityEnum.values()) {
            if(temp.getId() == id){
                return temp;
            }
        }
        return DEFAULT;
    }

    /**
     *
     * @param name
     * @return
     */
    public static FanqieHotelFacilityEnum getByName(String name){
        for (FanqieHotelFacilityEnum temp : FanqieHotelFacilityEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return DEFAULT;
    }
}
