package com.spring.lcwd.rating.payload;

import java.time.LocalDateTime;

public class ErrorDetails {
	private String message;
	private String details;
	private LocalDateTime timeStamp;

	public ErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorDetails(String message, String details, LocalDateTime timeStamp) {
		super();
		this.message = message;
		this.details = details;
		this.timeStamp = timeStamp;
	}

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
		return timeStamp;
	}

	public void setTimestamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

}