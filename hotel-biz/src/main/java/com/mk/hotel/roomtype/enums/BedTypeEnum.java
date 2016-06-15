package com.mk.hotel.roomtype.enums;

import com.dianping.cat.Cat;

import java.net.CacheRequest;

public enum BedTypeEnum {
    DA_CHUANG(1, "大床"),
    SHUANG_CHUANG(2, "双床"),
    SAN_CHUANG(3, "三床"),
    DAN_REN_CHUANG(4,"单人床"),
    YI_DAN_YI_SHUANG(5, "一单一双"),
    SHANG_XIA_PU(6, "上下铺"),
    TONG_PU(7, "通铺"),
    TA_TA_MI(8, "榻榻米"),
    SHUI_CHUANG(9, "水床"),
    YUAN_CHUANG(10, "圆床"),
    PING_CHUANG(11, "拼床"),
    OTHER(12, "其他"),

    DA_SHUANG_CHUANG(20,"大/双床"),
    CHUANG_WEI(30,"床位"),

    ;

    private Integer id;
    private String name;

    /**
     *
     * @param id
     * @param name
     */
    private BedTypeEnum(Integer id, String name) {
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
        return this.name;
    }


    public static BedTypeEnum getById(Integer id){

        for (BedTypeEnum temp : BedTypeEnum.values()) {
            if(temp.getId().equals(id)){
                return temp;
            }
        }
        return OTHER;
    }

    public static BedTypeEnum getByName(String name){
        for (BedTypeEnum temp : BedTypeEnum.values()) {
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return OTHER;
    }

    public static BedTypeEnum getByFanqieType (String fanqieRoomTypeValue) {
        // 大床1,    双床2,     大/双床3,   单人床4,
        // 圆床 5,   三张床 6,  床位  7,    榻榻米  8,

        Integer roomTypeValue = null;
        try {
            roomTypeValue = Integer.parseInt(fanqieRoomTypeValue);
        } catch (Exception e) {
            Cat.logError(e);
        }

        //
        BedTypeEnum result = null;
        switch (roomTypeValue) {
            case 1:
                result = BedTypeEnum.DA_CHUANG;
                break;
            case 2:
                result = BedTypeEnum.SHUANG_CHUANG;
                break;
            case 3:
                result = BedTypeEnum.DA_SHUANG_CHUANG;
                break;
            case 4:
                result = BedTypeEnum.DAN_REN_CHUANG;
                break;
            case 5:
                result = BedTypeEnum.YUAN_CHUANG;
                break;
            case 6:
                result = BedTypeEnum.SAN_CHUANG;
                break;
            case 7:
                result = BedTypeEnum.CHUANG_WEI;
                break;
            case 8:
                result = BedTypeEnum.TA_TA_MI;
                break;
            default:
                result = OTHER;
        }

        return result;
    }
}
