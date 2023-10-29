package com.mobileskins.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.mobileskins.userservice.entity.Customer;
import com.mobileskins.userservice.reprository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Customer> getAllUsers() {
		return userRepository.findAll();
	}

}
