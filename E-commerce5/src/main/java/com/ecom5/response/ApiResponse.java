package com.ecom5.response;

public class ApiResponse {

	String message;
	
	Boolean status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ApiResponse() {
		
	}
}
