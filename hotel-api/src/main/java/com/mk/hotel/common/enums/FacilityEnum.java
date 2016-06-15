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


    public static FacilityEnum getByFanqieType (Integer fanqieType) {

        //
        FacilityEnum result = null;
        switch (fanqieType) {
            case 1:
                result = FREE_WIRELESS;
                break;
            case 2:
                result = KITCHEN;
                break;
            case 3:
                result = BAR;
                break;
            case 4:
                result = CYCLE;
                break;
            case 5:
                result = PUBLIC_COMPUTER;
                break;
            case 6:
                result = TRANSFER_SERVICE;
                break;
            case 7:
                result = TOURISM_CONSULTING;
                break;
            case 8:
                result = PERSONAL_EFFECTS;
                break;
            case 9:
                result = LAUNDRY_SELF;
                break;
            case 10:
                result = FAX_PRINT;
                break;
            case 11:
                result = FILE;
                break;
            case 12:
                result = CONFERENCE_ROOM;
                break;
            case 13:
                result = LIBRARY;
                break;
            case 14:
                result = TEA_HOUSE;
                break;
            case 15:
                result = POOL;
                break;
            case 16:
                result = CAFE;
                break;
            case 17:
                result = FREE_TRAVEL_MAP;
                break;
            case 18:
                result = PAID_BREAKFAST;
                break;
            default:
                result = OTHER;
        }

        return result;
    }
}
