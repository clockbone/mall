package com.clockbone.vo;

public class Result<T> {
  
	private boolean hasError=false;
	private int code;
	private String message;
	private T obj;
	
	public boolean isHasError() {
		return hasError;
	}
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.hasError=true;
		this.code = code;
	}
	public void setCode(int code,String mess) {
		this.hasError=true;
		this.code = code;
		this.message = mess;
		
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	 

}
