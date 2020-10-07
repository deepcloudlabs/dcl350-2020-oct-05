package com.example.crm.dto;

public class RestErrorMessage {
	private String message;
	private int errorCode;
	private String debugId;

	public RestErrorMessage() {
	}

	public RestErrorMessage(String message, int errorCode, String debugId) {
		this.message = message;
		this.errorCode = errorCode;
		this.debugId = debugId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getDebugId() {
		return debugId;
	}

	public void setDebugId(String debugId) {
		this.debugId = debugId;
	}

}
