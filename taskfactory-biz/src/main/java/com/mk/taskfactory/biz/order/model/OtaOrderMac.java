package com.mk.taskfactory.biz.order.model;


import java.math.BigDecimal;
import java.util.Date;

/**
 * b_otaorder_mac
 */

public class OtaOrderMac {

	private  Long id;
	private  Long  orderID;
	private  String  uuid;
	private  String  sysno;
	private  String deviceimei;
	private  String  simsn;
	private  String  wifimacaddr;
	private  String blmacaddr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSysno() {
		return sysno;
	}

	public void setSysno(String sysno) {
		this.sysno = sysno;
	}

	public String getDeviceimei() {
		return deviceimei;
	}

	public void setDeviceimei(String deviceimei) {
		this.deviceimei = deviceimei;
	}

	public String getSimsn() {
		return simsn;
	}

	public void setSimsn(String simsn) {
		this.simsn = simsn;
	}

	public String getWifimacaddr() {
		return wifimacaddr;
	}

	public void setWifimacaddr(String wifimacaddr) {
		this.wifimacaddr = wifimacaddr;
	}

	public String getBlmacaddr() {
		return blmacaddr;
	}

	public void setBlmacaddr(String blmacaddr) {
		this.blmacaddr = blmacaddr;
	}
}
