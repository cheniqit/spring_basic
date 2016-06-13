package com.mk.hotel.roomtype.enums;

/**
 * Created by kirinli on 16/5/12.
 */
public enum HotelFacilityEnum {
    DEFAULT(-1,"无"),
    FREE_WIFI(24, "免费 WIFI"),
    FREE_WIRED(25,"免费有线"),
    HAVE_BREAFAST(26,"含早餐"),
    FAMILY_HOTEL(27,"三人/家庭房"),
    EXTRA_BED(28,"加床"),
    PARKING(29,"停车场"),
    NEWLY_RENOVATED(30,"新装修"),
    EN_SUITE(32,"独立卫浴"),
    FREE_TOILETRIES(33,"免费洗漱用品"),
    CREDIT_CARD(35,"可刷卡"),


    ;

    private Integer id;
    private String name;

    /**
     *
     * @param id
     * @param name
     */
    private HotelFacilityEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
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
    public static HotelFacilityEnum getById(Integer id){
        for (HotelFacilityEnum temp : HotelFacilityEnum.values()) {
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
    public static HotelFacilityEnum getByName(String name){
        for (HotelFacilityEnum temp : HotelFacilityEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return DEFAULT;
    }
}
