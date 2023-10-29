package com.mobileskins.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileskins.userservice.reprository.RoleRepository;

@Service
public class RoleService {
	
	@SuppressWarnings("unused")
	@Autowired
	private RoleRepository roleRepository;

}
