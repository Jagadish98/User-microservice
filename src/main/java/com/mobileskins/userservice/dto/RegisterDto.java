package com.mobileskins.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

	private String name;
	
	@NotBlank
	private String username;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
}
