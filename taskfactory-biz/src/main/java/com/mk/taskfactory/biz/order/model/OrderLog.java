package com.mk.taskfactory.biz.order.model;


import com.mk.taskfactory.biz.cps.model.CpsOrderList;

import java.math.BigDecimal;
import java.util.Date;

/**
 * p_orderlog
 */
//usercost
public class OrderLog {

	private Long id;
	private Long payid;
	private BigDecimal allcost;
	private BigDecimal realallcost;
	private BigDecimal hotelgive;
	private BigDecimal otagive;
	private BigDecimal usercost;
	private BigDecimal realcost;
	private BigDecimal realotagive;
	private BigDecimal qiekeIncome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPayid() {
		return payid;
	}

	public void setPayid(Long payid) {
		this.payid = payid;
	}

	public BigDecimal getAllcost() {
		return allcost;
	}

	public void setAllcost(BigDecimal allcost) {
		this.allcost = allcost;
	}

	public BigDecimal getRealallcost() {
		return realallcost;
	}

	public void setRealallcost(BigDecimal realallcost) {
		this.realallcost = realallcost;
	}

	public BigDecimal getHotelgive() {
		return hotelgive;
	}

	public void setHotelgive(BigDecimal hotelgive) {
		this.hotelgive = hotelgive;
	}

	public BigDecimal getOtagive() {
		return otagive;
	}

	public void setOtagive(BigDecimal otagive) {
		this.otagive = otagive;
	}

	public BigDecimal getUsercost() {
		return usercost;
	}

	public void setUsercost(BigDecimal usercost) {
		this.usercost = usercost;
	}

	public BigDecimal getRealcost() {
		return realcost;
	}

	public void setRealcost(BigDecimal realcost) {
		this.realcost = realcost;
	}

	public BigDecimal getRealotagive() {
		return realotagive;
	}

	public void setRealotagive(BigDecimal realotagive) {
		this.realotagive = realotagive;
	}

	public BigDecimal getQiekeIncome() {
		return qiekeIncome;
	}

	public void setQiekeIncome(BigDecimal qiekeIncome) {
		this.qiekeIncome = qiekeIncome;
	}
}