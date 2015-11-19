package com.mk.taskfactory.biz.order.model;


import java.util.Date;

/**
 * cps_order_list
 */

public class CpsOrderList {

	private  Long id;
	private  Long  orderId;
	private  Integer  channelType;
	private  String  channelCode;
	private  String  channelName;
	private  String  orderPrice;
	private  String  summaryDetailId;
	private  String  mid;
	private  Date createTime;
	private  Date  checkInTime;
	private  Date  checkOutTime;
	private  String  hotelId;
	private  String  isFirst;
	private  String  isNew;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getSummaryDetailId() {
		return summaryDetailId;
	}

	public void setSummaryDetailId(String summaryDetailId) {
		this.summaryDetailId = summaryDetailId;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}

	public Date getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
}
