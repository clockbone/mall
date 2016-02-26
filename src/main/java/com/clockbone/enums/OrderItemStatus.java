package com.clockbone.enums;

public enum OrderItemStatus {
	NO((byte)0,"未处理"),
	YES((byte)1," 已处理"),
	ERROR((byte)2,"处理失败");

	
	private byte id;
	private String name;
	private OrderItemStatus(byte id, String name){
		this.id=id;
		this.name=name;
	}
	public byte getId() {
		return id;
	}
	public void setId(byte id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
