package com.mobileskins.userservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex,
															 WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
													LocalDateTime.now(),
													ex.getMessage(),
													request.getDescription(false),
													"USER_NOT_FOUND");
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<?> handleResourceNotFoundException(EmailAlreadyExistsException ex,
															 WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
													LocalDateTime.now(),
													ex.getMessage(),
													request.getDescription(false),
													"EMAIL_ALREADY_EXISTS");
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
																	  HttpHeaders headers, 
																	  HttpStatusCode status, 
																	  WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails(
										LocalDateTime.now(),
										ex.getMessage() + " for " + request.getDescription(false),
										request.getDescription(false),
										"URL_NOT_FOUND");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
															   HttpHeaders headers, 
															   HttpStatusCode status, 
															   WebRequest request){
		Map<String, String> errors = new HashMap<>();
		List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		errorList.forEach ( error -> {
			String FieldName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			errors.put(FieldName, message);
		});
		
		ValidationErrors validationErrors = new ValidationErrors(
												LocalDateTime.now(),
												"Valid email and Password are required.",
												request.getDescription(false),
												"VALIDATION_FAILED",
												errors);
		
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
		
	} 
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(
										LocalDateTime.now(),
										ex.getMessage(),
										request.getDescription(false),
										"INTERNAL_SERVER_ERROR");
		
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
