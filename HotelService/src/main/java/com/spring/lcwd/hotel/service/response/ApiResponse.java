package com.spring.lcwd.hotel.service.response;

public class ApiResponse {
	
	

	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	private String message;
	private boolean success;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}