package com.mk.pms.roomtype.enmu;

/**
 * Created by kirinli on 16/5/11.
 */
public enum RoomTypeStatusEnum {
    Bookable(0,"可定"),
    NonBookable(1,"不可定"),
    ;
    private final Integer id;
    private final String name;

    private RoomTypeStatusEnum(Integer id, String name){
        this.id=id;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + " " + name;
    }

    public static RoomTypeStatusEnum getByName(String name){
        for (RoomTypeStatusEnum temp : RoomTypeStatusEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return Bookable;
    }
}
