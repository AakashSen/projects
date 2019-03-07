package com.thoughtworks.models;

import java.io.Serializable;

public class MessageInput implements Serializable {
	private static final long serialVersionUID = 1L;

	private String encryptedMessage;
    private int key;
    
	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

	
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "MessageInput [encryptedMessage=" + encryptedMessage + ", key=" + key + "]";
	}
}
