package com.mk.hotel.common.enums;

public enum HotelMsgTypeEnum {

    HOTEL_NEW(100,"新增酒店"),

    HOTEL_UPDATE(110,"酒店更新"),

    HOTEL_ONLINE(190,"酒店上线"),

    HOTEL_OFFLINE(191,"酒店下线"),

    ROOM_TYPE_NEW(200,"新增房型"),

    ROOM_TYPE_UPDATE(210,"房型更新"),

    ROOM_TYPE_ONLINE(290,"房型上线"),

    ROOM_TYPE_OFFLINE(291,"房型下线"),

    ;

    private int id;
    private String name;

    /**
     *
     * @param id
     * @param name
     */
    private HotelMsgTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
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
    public static HotelMsgTypeEnum getById(Integer id){
        for (HotelMsgTypeEnum temp : HotelMsgTypeEnum.values()) {
            if(temp.getId().compareTo(id) == 0){
                return temp;
            }
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public static HotelMsgTypeEnum getByName(String name){
        for (HotelMsgTypeEnum temp : HotelMsgTypeEnum.values()) {
            if(temp.name().equals(name)){
                return temp;
            }
        }
        return null;
    }

}
