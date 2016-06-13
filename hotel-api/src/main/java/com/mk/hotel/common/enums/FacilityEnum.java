package com.mk.hotel.common.enums;

public enum FacilityEnum {
    BUSINESS_MEETING(1,"商务会议"),
    SPA_RESORT(2,"温泉度假"),
    BEACH(3,"海滨休闲"),
    THEME(4,"主题情趣"),
    ENTERTAINMENT(5,"娱乐休闲"),
    SCENIC(6,"景区酒店"),
    YHA(7,"青年旅舍"),
    PLAZA(8,"精品酒店"),

    APARTMENT(19,"公寓"),
    SHOPPING(20,"购物便捷"),
    INNER(21,"客栈"),
    FARMHOUSE(22,"农家乐"),
    GUEST(23,"招待所"),

    FREE_WIRELESS(24,"免费无线"),
    FREE_CABLE(25,"免费有线"),
    BREAKFAST(26,"含早餐"),
    FAMILY(27,"三人/家庭房"),
    EXTRA_BED(28,"加床"),
    PARKING(29,"停车场"),
    THE_NEW(30,"新开业/新装修"),
    PAID_BREAKFAST(31,"可有偿提供桌早或打包早"),
    BATH_ROOM(32,"独立卫浴"),
    TOILETRIES(33,"免费洗漱用品"),
    LAUNDRY_SERVICE(34,"洗衣服务"),
    CREDIT(35,"可刷卡"),

    TRAVEL_TICKET_SERVICE(36,"旅游票务服务"),
    TOURIST_ATTRACTIONS(37,"旅游景区"),
    AIRPORT_RAILWAY(38,"机场/火车站"),
    METRO(39,"地铁沿线"),
    HOSPITAL(40,"医院"),
    SHOPPING_MALL(41,"购物中心"),
    INDUSTRIAL_PARK(42,"工业园区"),
    SCHOOL(43,"学校"),

    KITCHEN(502,"自助厨房"),
    BAR(503,"酒吧"),
    CYCLE(504,"自行车租赁"),
    PUBLIC_COMPUTER(505,"公共电脑"),
    TRANSFER_SERVICE(506,"接送服务"),
    TOURISM_CONSULTING(507,"旅游咨询"),
    PERSONAL_EFFECTS(508,"物品寄存"),
    LAUNDRY_SELF(509,"洗衣房"),
    FAX_PRINT(510,"传真/打印"),
    FILE(511,"电影"),
    CONFERENCE_ROOM(512,"会议室"),
    LIBRARY(513,"图书室"),
    TEA_HOUSE(514,"茶室"),
    POOL(515,"台球"),
    CAFE(516,"咖啡厅"),
    FREE_TRAVEL_MAP(517,"免费旅游交通图"),

    OTHER(999,"其他"),
    ;

    private int id;
    private String title;

    /**
     *
     * @param id
     * @param title
     */
    private FacilityEnum(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param id
     * @return
     */
    public static FacilityEnum getById(Integer id){
        for (FacilityEnum temp : FacilityEnum.values()) {
            if(temp.getId() == id){
                return temp;
            }
        }
        return OTHER;
    }

    /**
     *
     * @param name
     * @return
     */
    public static FacilityEnum getByName(String name){
        for (FacilityEnum temp : FacilityEnum.values()) {
            if(temp.getTitle().equals(name)){
                return temp;
            }
        }
        return OTHER;
    }


    public static FacilityEnum getByFanqieType (String fanqieType) {

//        免费wifi 1
//        自助厨房 2
//        酒吧 3
//        自行车租赁 4
//        公共电脑 5
//        接送服务 6
//        旅游咨询 7
//        物品寄存 8
//        洗衣房 9
//        传真/打印 10
//        电影 11
//        会议室 12
//        图书室 13
//        茶室 14
//        台球 15
//        咖啡厅 16
//        免费旅游交通图 17
//        送餐服务 18

//        Integer hotelTypeValue = null;
//        try {
//            hotelTypeValue = Integer.parseInt(fanqieHotelTypeValue);
//        } catch (Exception e) {
//            Cat.logError(e);
//        }
//
//        //
//        HotelTypeEnum result = null;
//        switch (hotelTypeValue) {
//            case 1:
//                result = HotelTypeEnum.INNER;
//                break;
//            case 2:
//                result = HotelTypeEnum.YHA;
//                break;
//            case 3:
//                result = HotelTypeEnum.STANDARD;
//                break;
//            case 4:
//                result = HotelTypeEnum.HOTEL_APARTMENT;
//                break;
//            case 5:
//                result = HotelTypeEnum.ECONOMY_HOTEL;
//                break;
//            case 6:
//                result = HotelTypeEnum.PLAZAHOTEL;
//                break;
//            case 7:
//                result = HotelTypeEnum.HOLIDAY_HOTEL;
//                break;
//            case 8:
//                result = HotelTypeEnum.SHORT_RENT_APARTMENT;
//                break;
//            case 9:
//                result = HotelTypeEnum.FARMHOUSE;
//                break;
//            default:
//                result = HMSHOTEL;
//        }
//
//        return result;
        return OTHER;
    }
}
