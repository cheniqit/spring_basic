package com.mk.hotel.hotelinfo.enums;


public enum HotelTypeEnum {
    DEFAULT_HOTE(-1, "不限"),
    HMSHOTEL(1,"旅馆"),
    THEMEDHOTEL(2,"主题酒店"),
    PLAZAHOTEL(3,"精品酒店"),
    APARTMENTHOTEL(4,"公寓"),
    HOSTELS(5,"招待所"),
    INNER(6,"客栈");

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
}
