package com.example.hr.dto;

public class FireEmployeeResponse {
	private String status;

	public FireEmployeeResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
