package com.mk.taskfactory.biz.order.model;


import java.math.BigDecimal;
import java.util.Date;

/**
 * p_orderlog
 */
public class PPay {

	private Long id;
	private Long mid;
	private Long orderId;
	private Long userId;
	private BigDecimal orderPrice;
	private BigDecimal leZhu;
	private Date time;
	private String pmsOrderno;
	private Long hotelId;
	private Integer status;
	private String needReturn;
	private Long newOrderId;
	private Integer  paySrc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getLeZhu() {
		return leZhu;
	}

	public void setLeZhu(BigDecimal leZhu) {
		this.leZhu = leZhu;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getPmsOrderno() {
		return pmsOrderno;
	}

	public void setPmsOrderno(String pmsOrderno) {
		this.pmsOrderno = pmsOrderno;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNeedReturn() {
		return needReturn;
	}

	public void setNeedReturn(String needReturn) {
		this.needReturn = needReturn;
	}

	public Long getNewOrderId() {
		return newOrderId;
	}

	public void setNewOrderId(Long newOrderId) {
		this.newOrderId = newOrderId;
	}

	public Integer getPaySrc() {
		return paySrc;
	}

	public void setPaySrc(Integer paySrc) {
		this.paySrc = paySrc;
	}
}