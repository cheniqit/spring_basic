package com.mk.hotel.log.enums;

public enum LogPushTypeEnum {
	hotelAll(100l,"所有酒店(房爸爸)"),
	hotel(101l,"单个酒店(房爸爸)"),
	hotelDelete(110l,"删除酒店(房爸爸)"),
	hotelFanqie(120l,"单个酒店(番茄)"),

	hotelFacility(150l,"酒店设施(房爸爸)"),

	roomType(200l,"房型(房爸爸)"),
	roomTypeDelete(210l,"删除房型(房爸爸)"),
	roomTypePrice(250l,"房型价格(房爸爸)"),
	roomTypeStock(260l,"房型库存(房爸爸)"),
	fanqieRoomTypeInfo(310l,"房型库存(房爸爸)"),

	roomTypeStatusFanqie(300l,"房态(番茄)"),

	updateRoomTypePrice(400l,"房价更新"),

	orderStatus(900l,"订单状态(房爸爸)"),
	other(999l,"其他"),
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

	public static LogPushTypeEnum getById(Long id){
		for (LogPushTypeEnum temp : LogPushTypeEnum.values()) {
			if(temp.getId().equals(id)){
				return temp;
			}
		}
		return other;
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
