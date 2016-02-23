package com.mk.taskfactory.model.crawer;

public class QFacility {
    private Long id;

    private String facName;

    private String facField;

    private Integer facType;

    private String visible;

    private String otsId;

    private String createTime;

    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacName() {
        return facName;
    }

    public void setFacName(String facName) {
        this.facName = facName == null ? null : facName.trim();
    }

    public String getFacField() {
        return facField;
    }

    public void setFacField(String facField) {
        this.facField = facField == null ? null : facField.trim();
    }

    public Integer getFacType() {
        return facType;
    }

    public void setFacType(Integer facType) {
        this.facType = facType;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible == null ? null : visible.trim();
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