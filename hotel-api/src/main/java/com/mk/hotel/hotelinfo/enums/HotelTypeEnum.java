package com.mk.hotel.hotelinfo.enums;


import com.dianping.cat.Cat;

public enum HotelTypeEnum {
    // 1、客栈 2、青年旅社 3、酒店 4、酒店式公寓
    // 5、经济型酒店 6、精品酒店 7、度假酒店 8、短租公寓 9、农家乐

    DEFAULT_HOTE(-1, "不限"),
    HMSHOTEL(1,"旅馆"),
    THEMEDHOTEL(2,"主题酒店"),
    PLAZAHOTEL(3,"精品酒店"),
    APARTMENTHOTEL(4,"公寓"),
    HOSTELS(5,"招待所"),
    INNER(6,"客栈"),

    YHA(10,"青年旅社"),
    STANDARD(20,"酒店"),
    HOTEL_APARTMENT(30,"酒店式公寓"),
    ECONOMY_HOTEL(40,"经济型酒店"),
    HOLIDAY_HOTEL(50,"度假酒店"),
    SHORT_RENT_APARTMENT(60,"短租公寓"),
    FARMHOUSE(70,"农家乐"),
    ;

    private int id;
    private String title;

    /**
     *
     * @param id
     * @param title
     */
    private HotelTypeEnum(Integer id, String title) {
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
    public static HotelTypeEnum getById(Integer id){
        for (HotelTypeEnum temp : HotelTypeEnum.values()) {
            if(temp.getId() == id){
                return temp;
            }
        }
        return HMSHOTEL;
    }

    /**
     *
     * @param name
     * @return
     */
    public static HotelTypeEnum getByName(String name){
        for (HotelTypeEnum temp : HotelTypeEnum.values()) {
            if(temp.getTitle().equals(name)){
                return temp;
            }
        }
        return HMSHOTEL;
    }


    public static HotelTypeEnum getByFanqieType (String fanqieHotelTypeValue) {
        // 1、客栈 2、青年旅社 3、酒店 4、酒店式公寓
        // 5、经济型酒店 6、精品酒店 7、度假酒店 8、短租公寓 9、农家乐

        Integer hotelTypeValue = null;
        try {
            hotelTypeValue = Integer.parseInt(fanqieHotelTypeValue);
        } catch (Exception e) {
            Cat.logError(e);
        }

        //
        HotelTypeEnum result = null;
        switch (hotelTypeValue) {
            case 1:
                result = HotelTypeEnum.INNER;
                break;
            case 2:
                result = HotelTypeEnum.YHA;
                break;
            case 3:
                result = HotelTypeEnum.STANDARD;
                break;
            case 4:
                result = HotelTypeEnum.HOTEL_APARTMENT;
                break;
            case 5:
                result = HotelTypeEnum.ECONOMY_HOTEL;
                break;
            case 6:
                result = HotelTypeEnum.PLAZAHOTEL;
                break;
            case 7:
                result = HotelTypeEnum.HOLIDAY_HOTEL;
                break;
            case 8:
                result = HotelTypeEnum.SHORT_RENT_APARTMENT;
                break;
            case 9:
                result = HotelTypeEnum.FARMHOUSE;
                break;
            default:
                result = HMSHOTEL;
        }

        return result;
    }
}
