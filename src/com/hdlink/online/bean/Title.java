package com.hdlink.online.bean;

import java.util.List;

public class Title<T> {
	
	private int id;
	private String name;
	private List<T> sub;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<T> getSub() {
		return sub;
	}
	public void setSub(List<T> sub) {
		this.sub = sub;
	}
	
	
}
