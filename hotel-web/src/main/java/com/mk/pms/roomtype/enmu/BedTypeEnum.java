package com.mk.pms.roomtype.enmu;

/**
 * Created by kirinli on 16/5/11.
 */
public enum BedTypeEnum {
    BigBed(1,"大床"),
    DoubleBed(2,"双床"),
    ThreeBed(3,"酒店设施"),
    SingleBed(4,"单人床"),
    SingleWithDouble(5,"一单一双"),
    BunkBed(6,"上下铺"),
    DormintoryBed(7,"通铺"),
    TatamiBed(8,"榻榻米"),
    WaterBed(9,"水床"),
    RoundBed(10,"通铺"),
    CombineBed(11,"拼床"),
    other(12,"其他"),
    ;
    private final Integer id;
    private final String name;

    private BedTypeEnum(Integer id, String name){
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

    public static BedTypeEnum getByName(String name){
        for (BedTypeEnum temp : BedTypeEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return other;
    }
}
