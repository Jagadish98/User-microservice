package com.mobileskins.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileskins.userservice.dto.LoginDto;
import com.mobileskins.userservice.dto.RegisterDto;
import com.mobileskins.userservice.entity.Customer;
import com.mobileskins.userservice.service.AuthService;
import com.mobileskins.userservice.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@EnableMethodSecurity
@RestController
@RequestMapping("/api/users/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		List<Customer> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterDto registerDto) {
		String response = authService.register(registerDto);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody @Valid LoginDto loginDto) {
		String response  = authService.login(loginDto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
