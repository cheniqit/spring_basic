package com.mk.hotel.roomtype.enums;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public enum RoomTypeStockCacheEnum {
	LockKey(10l,"RoomTypeStockLock"),
	AvailableKey(20l,"RoomTypeStockAvailable"),
	UsingKey(30l,"RoomTypeStockUsing"),
	other(999l,"Other"),
	;
	private final Long id;
	private final String name;

	private RoomTypeStockCacheEnum(Long id, String name){
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
//	public static RoomTypeStockCacheEnum getByName(String name){
//		for (RoomTypeStockCacheEnum temp : RoomTypeStockCacheEnum.values()) {
//			if(temp.getName().equals(name)){
//				return temp;
//			}
//		}
//		return other;
//	}

	public static String getLockKeyName (String hotelId, String roomTypeId, Date day) {

		if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId) || null == day) {
			return "";
		}

		//
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		StringBuilder result = new StringBuilder()
				.append(RoomTypeStockCacheEnum.LockKey.getName())
				.append("_")
				.append(hotelId)
				.append("_")
				.append(roomTypeId)
				.append("_")
				.append(format.format(day));

		return result.toString();
	}

	public static String getAvailableHashName(String hotelId, String roomTypeId) {
		if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId)) {
			return "";
		}

		//
		StringBuilder result = new StringBuilder()
				.append(RoomTypeStockCacheEnum.AvailableKey.getName())
				.append("_")
				.append(hotelId)
				.append("_")
				.append(roomTypeId);

		return result.toString();
	}

	public static String getUsingHashName(String hotelId, String roomTypeId) {
		if (StringUtils.isBlank(hotelId) || StringUtils.isBlank(roomTypeId)) {
			return "";
		}

		//
		StringBuilder result = new StringBuilder()
				.append(RoomTypeStockCacheEnum.UsingKey.getName())
				.append("_")
				.append(hotelId)
				.append("_")
				.append(roomTypeId);

		return result.toString();
	}
}
