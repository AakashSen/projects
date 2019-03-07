package com.thoughtworks.models;

import java.io.Serializable;

public class MessageOutput implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageOutput [message=" + message + "]";
	}
	
}
