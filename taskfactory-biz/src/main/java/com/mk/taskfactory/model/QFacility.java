package com.mk.taskfactory.model;

public class QFacility {
    private Long id;

    private String facName;

    private String facField;

    private Integer facType;

    private String visible;

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
}