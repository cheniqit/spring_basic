package com.mk.taskfactory.api.enums;

public enum HotelPicEnum {
    HOTEL_DEF("10","def","门头及招牌",1),
    HOTEL_LOBBY("13","lobby","大堂",1),
    HOTEL_MAIN("16","mainHousing","主力房源",1),

    ROOMTYPE_DEF("20","def","主展图",2),
    ROOMTYPE_BED("23","bed","床",2),
    ROOMTYPE_TOILET("26","toilet","卫生间",2),
    OTHER("-1","other","其他",0);
    private String id;
    private String code;
    private String desc;
    private Integer type;

    HotelPicEnum( String id,String code, String desc,Integer type) {
        this.id = id;
        this.code = code;
        this.desc=desc;
        this.type=type;
    }

    public static HotelPicEnum getByCode(String code,Integer type) {
        if (type==null){
            return OTHER;
        }
        for (HotelPicEnum valueTypeEnum : HotelPicEnum.values()) {
            if (valueTypeEnum.getCode().equals(code)||type==valueTypeEnum.getType()) {
                return valueTypeEnum;
            }
        }
        return OTHER;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
