package com.example.crm.dto;

public enum CrmApiErrorCodes {
	MISSING_CUSTOMER(100), BAD_CUSTOMER(110), BAD_PARAMETER(120), BAD_BACKEND(130);

	private int errorId;

	private CrmApiErrorCodes(int errorId) {
		this.errorId = errorId;
	}

	public int getErrorId() {
		return errorId;
	}

}
