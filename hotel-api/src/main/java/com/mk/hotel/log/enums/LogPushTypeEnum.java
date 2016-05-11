package com.mk.hotel.log.enums;

public enum LogPushTypeEnum {
	hotelAll(100l,"所有酒店"),
	hotel(101l,"单个酒店"),
	hotelFacility(200l,"酒店设施"),
	roomType(200l,"房型"),
	roomTypePrice(250l,"房型价格"),
	roomTypeStock(260l,"房型库存"),
	orderStatus(900l,"订单状态"),
	other(999l,"其他证件"),
	;
	private final Long id;
	private final String name;

	private LogPushTypeEnum(Long id, String name){
		this.id=id;
		this.name=name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.valueOf(id) + " " + name;
	}
	
	public static LogPushTypeEnum getByName(String name){
		for (LogPushTypeEnum temp : LogPushTypeEnum.values()) {
			if(temp.getName().equals(name)){
				return temp;
			}
		}
		return other;
	}
}
