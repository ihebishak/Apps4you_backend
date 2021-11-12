package com.apps4you.entities;

public class MailResponse {
	private String message;
	private boolean status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
