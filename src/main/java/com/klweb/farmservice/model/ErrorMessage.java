package com.klweb.farmservice.model;

import com.google.gson.Gson;

public class ErrorMessage<T> {
	private T message;

	public ErrorMessage(T message){
		this.message = message;
	}
	
	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}
	
	public String toString() {
		return new Gson().toJson(this);
	}
}
