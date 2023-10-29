package com.mobileskins.userservice.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobileskins.userservice.dto.LoginDto;
import com.mobileskins.userservice.dto.RegisterDto;
import com.mobileskins.userservice.entity.Customer;
import com.mobileskins.userservice.entity.Role;
import com.mobileskins.userservice.exception.EmailAlreadyExistsException;
import com.mobileskins.userservice.reprository.RoleRepository;
import com.mobileskins.userservice.reprository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public String register(RegisterDto registerDto) {
		
		if(userRepository.existsByUsername(registerDto.getUsername()))
			throw new EmailAlreadyExistsException("User", "Username", registerDto.getUsername());
		
		if(userRepository.existsByEmail(registerDto.getEmail()))
			throw new EmailAlreadyExistsException("User", "Email", registerDto.getEmail());
		
		Customer customer = new Customer();
		customer.setUsername(registerDto.getUsername());
		customer.setEmail(registerDto.getEmail());
		
		customer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		if(registerDto.getName().isBlank())
		{
			customer.setName(registerDto.getUsername());
		}
		else
		{
			customer.setName(registerDto.getName());
		}
		
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);
		
		customer.setRoles(roles);
		
		System.out.println("customer is " + customer.toString());
		
		userRepository.save(customer);
		
		return "User registered successfully!";
	}
	
	
	
	public String login(LoginDto loginDto) {
		
		Authentication authentication = 
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return "User logged-in successfully!";
		
	}
	
}
