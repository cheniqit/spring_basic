package com.mk.common;

import java.io.Serializable;
import java.util.List;


public class PageObject<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer count;
	private List<T> list;

	public PageObject(Integer count) {
		this.count = count;
	}

	public PageObject(Integer count, List<T> list) {
		this.count = count;
		this.list = list;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
