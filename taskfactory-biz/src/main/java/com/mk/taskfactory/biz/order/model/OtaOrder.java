package com.mk.taskfactory.biz.order.model;


import com.mk.taskfactory.biz.cps.model.CpsOrderList;

import java.math.BigDecimal;
import java.util.Date;

/**
 * b_otaorder
 */

public class OtaOrder extends CpsOrderList {

	private  Long id;
	private  Long  hotelId;
	private  String  hotelName;
	private  String  hotelPms;
	private  Integer orderMethod;
	private  Integer  orderType;
	private  Integer  priceType;
	private Date beginTime;
	private  Date  endTime;
	private  Long  mid;
	private  Long  mLevel;
	private  Date  createTime;
	private  String  promotion;
	private  String  coupon;
	private BigDecimal totalPrice;
	private  BigDecimal  price;
	private  Integer  breakfastNum;
	private  String  contacts;
	private  String  contactsPhone;
	private  String  contactsEmail;
	private  String  contactsWeixin;
	private  String  note;
	private  Integer  orderStatus;
	private  Integer  payStatus;
	private  Integer  receipt;
	private  Date  updateTime;
	private  String  canShow;
	private  String  hiddenOrder;
	private  String  ostype;
	private  Integer  version;
	private  Integer  spreadUser;
	private  Integer  dayNumber;
	private  Integer  isScore;
	private  Integer  activeId;
	private  Integer  invalidReason;
	private  Integer  rulecode;
	private  BigDecimal  userLongitude;
	private  BigDecimal  userLatitude;
	private  BigDecimal  availableMoney;
	private  BigDecimal  cashBack;
	private  String  cityCode;
	private  String  isReceiveCashBack;
	private  String  clearingType;
	private  BigDecimal  clearingMoney;
	private  Integer  promoType;
	private  String  roomTicket;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHotelPms() {
		return hotelPms;
	}

	public void setHotelPms(String hotelPms) {
		this.hotelPms = hotelPms;
	}

	public Integer getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(Integer orderMethod) {
		this.orderMethod = orderMethod;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getmLevel() {
		return mLevel;
	}

	public void setmLevel(Long mLevel) {
		this.mLevel = mLevel;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getBreakfastNum() {
		return breakfastNum;
	}

	public void setBreakfastNum(Integer breakfastNum) {
		this.breakfastNum = breakfastNum;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getContactsEmail() {
		return contactsEmail;
	}

	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}

	public String getContactsWeixin() {
		return contactsWeixin;
	}

	public void setContactsWeixin(String contactsWeixin) {
		this.contactsWeixin = contactsWeixin;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getReceipt() {
		return receipt;
	}

	public void setReceipt(Integer receipt) {
		this.receipt = receipt;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCanShow() {
		return canShow;
	}

	public void setCanShow(String canShow) {
		this.canShow = canShow;
	}

	public String getHiddenOrder() {
		return hiddenOrder;
	}

	public void setHiddenOrder(String hiddenOrder) {
		this.hiddenOrder = hiddenOrder;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getSpreadUser() {
		return spreadUser;
	}

	public void setSpreadUser(Integer spreadUser) {
		this.spreadUser = spreadUser;
	}

	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Integer getIsScore() {
		return isScore;
	}

	public void setIsScore(Integer isScore) {
		this.isScore = isScore;
	}

	public Integer getActiveId() {
		return activeId;
	}

	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}

	public Integer getInvalidReason() {
		return invalidReason;
	}

	public void setInvalidReason(Integer invalidReason) {
		this.invalidReason = invalidReason;
	}

	public Integer getRulecode() {
		return rulecode;
	}

	public void setRulecode(Integer rulecode) {
		this.rulecode = rulecode;
	}

	public BigDecimal getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(BigDecimal userLongitude) {
		this.userLongitude = userLongitude;
	}

	public BigDecimal getUserLatitude() {
		return userLatitude;
	}

	public void setUserLatitude(BigDecimal userLatitude) {
		this.userLatitude = userLatitude;
	}

	public BigDecimal getAvailableMoney() {
		return availableMoney;
	}

	public void setAvailableMoney(BigDecimal availableMoney) {
		this.availableMoney = availableMoney;
	}

	public BigDecimal getCashBack() {
		return cashBack;
	}

	public void setCashBack(BigDecimal cashBack) {
		this.cashBack = cashBack;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getIsReceiveCashBack() {
		return isReceiveCashBack;
	}

	public void setIsReceiveCashBack(String isReceiveCashBack) {
		this.isReceiveCashBack = isReceiveCashBack;
	}

	public String getClearingType() {
		return clearingType;
	}

	public void setClearingType(String clearingType) {
		this.clearingType = clearingType;
	}

	public BigDecimal getClearingMoney() {
		return clearingMoney;
	}

	public void setClearingMoney(BigDecimal clearingMoney) {
		this.clearingMoney = clearingMoney;
	}

	public Integer getPromoType() {
		return promoType;
	}

	public void setPromoType(Integer promoType) {
		this.promoType = promoType;
	}

	public String getRoomTicket() {
		return roomTicket;
	}

	public void setRoomTicket(String roomTicket) {
		this.roomTicket = roomTicket;
	}
}
