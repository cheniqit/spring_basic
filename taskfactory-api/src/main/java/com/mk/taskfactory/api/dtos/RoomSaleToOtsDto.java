package com.mk.taskfactory.api.dtos;

public class RoomSaleToOtsDto {
    private String isOnPromo; //�Ƿ���
    private String  promoText ;//�����˵��
    private String promoTextColor;//�������ɫ
    private String promoStartTime;//���ʼʱ��
    private String promoEndTime;//�����ʱ��
    private Integer saleType;//��������
    private String saleName;//��������
    private Double salePrice;//�����۸�
    private String roomNo;//�����
    private Integer roomtypeid;//����Id
    private String useDescribe;//ʹ������

    public String getIsOnPromo() {
        return isOnPromo;
    }

    public void setIsOnPromo(String isOnPromo) {
        this.isOnPromo = isOnPromo;
    }

    public String getPromoText() {
        return promoText;
    }

    public void setPromoText(String promoText) {
        this.promoText = promoText;
    }

    public String getPromoTextColor() {
        return promoTextColor;
    }

    public void setPromoTextColor(String promoTextColor) {
        this.promoTextColor = promoTextColor;
    }

    public String getPromoStartTime() {
        return promoStartTime;
    }

    public void setPromoStartTime(String promoStartTime) {
        this.promoStartTime = promoStartTime;
    }

    public String getPromoEndTime() {
        return promoEndTime;
    }

    public void setPromoEndTime(String promoEndTime) {
        this.promoEndTime = promoEndTime;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Integer getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Integer roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public String getUseDescribe() {
        return useDescribe;
    }

    public void setUseDescribe(String useDescribe) {
        this.useDescribe = useDescribe;
    }
}
