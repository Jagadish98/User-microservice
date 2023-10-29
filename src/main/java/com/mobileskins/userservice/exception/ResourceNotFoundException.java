package com.mobileskins.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private String resource;
	private String fieldName;
	private Long fieldValue;
	
	public ResourceNotFoundException(String resource, String fieldName, Long fieldValue) {
		super(String.format("%s not found with %s : %d", resource, fieldName, fieldValue));
		
		this.resource = resource;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		
	}

}
