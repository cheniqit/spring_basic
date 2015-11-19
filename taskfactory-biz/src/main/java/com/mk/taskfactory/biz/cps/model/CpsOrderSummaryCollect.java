package com.mk.taskfactory.biz.cps.model;

import java.math.BigDecimal;
import java.util.Date;

public class CpsOrderSummaryCollect {
    private Long id;

    private Integer channeltype;

    private String channelcode;

    private String channelname;

    private Integer sumorderfirst;

    private BigDecimal sumorderpricefirst;

    private Integer sumorder;

    private BigDecimal sumorderprice;

    private Double deductratefirst;

    private Double cpsratefirst;

    private Date createtime;

    private Date ruleupdatetime;

    private String ruleupdateby;

    private Double cpsrate;

    private Double deductrate;

    private Date orderdate;

    private BigDecimal sumorderpriceratefirst;

    private BigDecimal sumorderpricerate;

    private BigDecimal totalprice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(Integer channeltype) {
        this.channeltype = channeltype;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode == null ? null : channelcode.trim();
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname == null ? null : channelname.trim();
    }

    public Integer getSumorderfirst() {
        return sumorderfirst;
    }

    public void setSumorderfirst(Integer sumorderfirst) {
        this.sumorderfirst = sumorderfirst;
    }

    public BigDecimal getSumorderpricefirst() {
        return sumorderpricefirst;
    }

    public void setSumorderpricefirst(BigDecimal sumorderpricefirst) {
        this.sumorderpricefirst = sumorderpricefirst;
    }

    public Integer getSumorder() {
        return sumorder;
    }

    public void setSumorder(Integer sumorder) {
        this.sumorder = sumorder;
    }

    public BigDecimal getSumorderprice() {
        return sumorderprice;
    }

    public void setSumorderprice(BigDecimal sumorderprice) {
        this.sumorderprice = sumorderprice;
    }

    public Double getDeductratefirst() {
        return deductratefirst;
    }

    public void setDeductratefirst(Double deductratefirst) {
        this.deductratefirst = deductratefirst;
    }

    public Double getCpsratefirst() {
        return cpsratefirst;
    }

    public void setCpsratefirst(Double cpsratefirst) {
        this.cpsratefirst = cpsratefirst;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getRuleupdatetime() {
        return ruleupdatetime;
    }

    public void setRuleupdatetime(Date ruleupdatetime) {
        this.ruleupdatetime = ruleupdatetime;
    }

    public String getRuleupdateby() {
        return ruleupdateby;
    }

    public void setRuleupdateby(String ruleupdateby) {
        this.ruleupdateby = ruleupdateby == null ? null : ruleupdateby.trim();
    }

    public Double getCpsrate() {
        return cpsrate;
    }

    public void setCpsrate(Double cpsrate) {
        this.cpsrate = cpsrate;
    }

    public Double getDeductrate() {
        return deductrate;
    }

    public void setDeductrate(Double deductrate) {
        this.deductrate = deductrate;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public BigDecimal getSumorderpriceratefirst() {
        return sumorderpriceratefirst;
    }

    public void setSumorderpriceratefirst(BigDecimal sumorderpriceratefirst) {
        this.sumorderpriceratefirst = sumorderpriceratefirst;
    }

    public BigDecimal getSumorderpricerate() {
        return sumorderpricerate;
    }

    public void setSumorderpricerate(BigDecimal sumorderpricerate) {
        this.sumorderpricerate = sumorderpricerate;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }
}