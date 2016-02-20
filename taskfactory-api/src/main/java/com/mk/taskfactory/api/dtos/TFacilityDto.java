package com.mk.taskfactory.api.dtos;

public class TFacilityDto {
    private Long id;
    private String facName;
    private Integer facType;
    private String binding;
    private Integer facSort;
    private String visible;
    private String iconUrl;
    private Integer pageIndex;
    private Integer pageSize;

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
        this.facName = facName;
    }

    public Integer getFacType() {
        return facType;
    }

    public void setFacType(Integer facType) {
        this.facType = facType;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public Integer getFacSort() {
        return facSort;
    }

    public void setFacSort(Integer facSort) {
        this.facSort = facSort;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
