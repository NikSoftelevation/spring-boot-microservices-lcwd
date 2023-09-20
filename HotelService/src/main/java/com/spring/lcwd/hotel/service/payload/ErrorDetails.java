package com.spring.lcwd.hotel.service.payload;

import java.time.LocalDateTime;

public class ErrorDetails {

	public ErrorDetails(String message, String details, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	private String message;
	private String details;
	private LocalDateTime timestamp;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}