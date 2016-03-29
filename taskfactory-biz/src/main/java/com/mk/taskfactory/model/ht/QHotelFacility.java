package com.mk.taskfactory.model.ht;

public class QHotelFacility {
    private Integer id;

    private Long hotelId;

    private String sourceId;

    private Long facId;

    private String facField;

    private String facName;

    private String facType;

    private String binding;

    private String facSort;

    private String otsId;

    private String createTime;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public Long getFacId() {
        return facId;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }

    public String getFacField() {
        return facField;
    }

    public void setFacField(String facField) {
        this.facField = facField == null ? null : facField.trim();
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName;
    }

    public String getFacType() {
        return facType;
    }

    public void setFacType(String facType) {
        this.facType = facType;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getFacSort() {
        return facSort;
    }

    public void setFacSort(String facSort) {
        this.facSort = facSort;
    }

    public String getOtsId() {
        return otsId;
    }

    public void setOtsId(String otsId) {
        this.otsId = otsId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}