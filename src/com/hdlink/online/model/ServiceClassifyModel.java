package com.hdlink.online.model;

import java.io.Serializable;

public class ServiceClassifyModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ServiceClassify [id=" + id + ", name=" + name + "]";
	}
	
}
