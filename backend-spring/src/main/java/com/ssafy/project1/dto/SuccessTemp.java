package com.ssafy.project1.dto;

public class SuccessTemp {
	public int success;

	public SuccessTemp() {
	}

	public SuccessTemp(int success) {
		this.success = success;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "SuccessTemp [success=" + success + "]";
	}

}
