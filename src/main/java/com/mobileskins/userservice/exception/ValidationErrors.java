package com.mobileskins.userservice.exception;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationErrors extends ErrorDetails{
	
	private Map<String, String> errors;
	
	public ValidationErrors(LocalDateTime timeStamp, String message, String path, String errorCode, Map<String, String> errors) {
		
		this.timeStamp = timeStamp;
		this.message = message;
		this.path = path;
		this.errorCode = errorCode;
		this.errors = errors;
		
	}

}
