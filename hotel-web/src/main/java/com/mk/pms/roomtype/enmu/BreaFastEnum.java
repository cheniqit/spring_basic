package com.mk.pms.roomtype.enmu;

/**
 * Created by kirinli on 16/5/11.
 */
public enum BreaFastEnum {
    None(0,"无早"),
    HasBeadFast(1,"含早"),
    ;
    private final Integer id;
    private final String name;

    private BreaFastEnum(Integer id, String name){
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

    public static BreaFastEnum getByName(String name){
        for (BreaFastEnum temp : BreaFastEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return None;
    }
}
