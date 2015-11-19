package com.mk.taskfactory.biz.cps.model;

import java.math.BigDecimal;
import java.util.Date;

public class CpsRateConfig {
    private Integer id;

    private Integer channelid;

    private BigDecimal firstcpsrate;

    private BigDecimal firstdeductrate;

    private BigDecimal cpsrate;

    private BigDecimal deductrate;

    private Date paystartdate;

    private Date payenddate;

    private String valid;

    private Date createtime;

    private String createby;

    private Date updatetime;

    private String updateby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public BigDecimal getFirstcpsrate() {
        return firstcpsrate;
    }

    public void setFirstcpsrate(BigDecimal firstcpsrate) {
        this.firstcpsrate = firstcpsrate;
    }

    public BigDecimal getFirstdeductrate() {
        return firstdeductrate;
    }

    public void setFirstdeductrate(BigDecimal firstdeductrate) {
        this.firstdeductrate = firstdeductrate;
    }

    public BigDecimal getCpsrate() {
        return cpsrate;
    }

    public void setCpsrate(BigDecimal cpsrate) {
        this.cpsrate = cpsrate;
    }

    public BigDecimal getDeductrate() {
        return deductrate;
    }

    public void setDeductrate(BigDecimal deductrate) {
        this.deductrate = deductrate;
    }

    public Date getPaystartdate() {
        return paystartdate;
    }

    public void setPaystartdate(Date paystartdate) {
        this.paystartdate = paystartdate;
    }

    public Date getPayenddate() {
        return payenddate;
    }

    public void setPayenddate(Date payenddate) {
        this.payenddate = payenddate;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }
}