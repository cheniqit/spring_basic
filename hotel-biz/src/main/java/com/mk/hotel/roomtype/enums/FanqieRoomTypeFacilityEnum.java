package com.mk.hotel.roomtype.enums;

/**
 * Created by kirinli on 16/5/12.
 */
public enum FanqieRoomTypeFacilityEnum {
    DEFAULT(-1,"无"),
    BREAK_FAST(1, "早餐"),
    AIR_CONDITIONER(2,"空调"),
    TV(3,"电视"),
    BROADBAND(4,"宽带"),
    BATHROOM(5,"卫浴"),
    TEL(6,"电话"),


    ;

    private Integer id;
    private String name;

    /**
     *
     * @param id
     * @param name
     */
    private FanqieRoomTypeFacilityEnum(Integer id, String name) {
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
    public static FanqieRoomTypeFacilityEnum getById(Integer id){
        for (FanqieRoomTypeFacilityEnum temp : FanqieRoomTypeFacilityEnum.values()) {
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
    public static FanqieRoomTypeFacilityEnum getByName(String name){
        for (FanqieRoomTypeFacilityEnum temp : FanqieRoomTypeFacilityEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return DEFAULT;
    }
}
