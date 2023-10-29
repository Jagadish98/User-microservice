package com.mobileskins.userservice.exception;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Component
public class ErrorDetails {

	protected LocalDateTime timeStamp;
	protected String message;
	protected String path;
	protected String errorCode;
	
	public ErrorDetails(LocalDateTime timeStamp, String message, String path, String errorCode) {
		this.timeStamp = timeStamp;
		this.message = message;
		this.path = path;
		this.errorCode = errorCode;
	}
	
}
