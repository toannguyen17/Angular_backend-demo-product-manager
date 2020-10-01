package com.codegym.metadata;

import java.util.Date;

public class BaseResponse<T> {
	private Number status = 0;
	private String msg    = "";
	private T      data   = null;
	private Number timestamp;

	public BaseResponse() {
		Date date = new Date();
		timestamp = date.getTime();
	}

	public Number getStatus() {
		return status;
	}

	public void setStatus(Number status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Number getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Number timestamp) {
		this.timestamp = timestamp;
	}
}
