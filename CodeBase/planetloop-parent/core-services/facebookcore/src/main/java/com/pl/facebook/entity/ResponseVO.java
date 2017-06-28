package com.pl.facebook.entity;

import java.io.Serializable;

public class ResponseVO implements Serializable {

	private static final long serialVersionUID = 6368700325476037925L;

	private String staus;
	private String message;
	private Object data;

	public String getStaus() {
		return staus;
	}

	public void setStaus(String staus) {
		this.staus = staus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
