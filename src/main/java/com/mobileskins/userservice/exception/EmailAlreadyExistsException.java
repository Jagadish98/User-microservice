package com.mobileskins.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class EmailAlreadyExistsException extends RuntimeException{
	
	private String resource;
	private String fieldName;
	private String fieldValue;
	
	public EmailAlreadyExistsException(String resource, String fieldName, String fieldValue) {
		super(String.format("%s already exists with %s : %s", resource, fieldName, fieldValue));
		
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		
	}

}
