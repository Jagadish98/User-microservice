package com.mobileskins.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobileskins.userservice.service.RoleService;

@RestController
@RequestMapping("/api/roles/")
public class RoleController {

	@SuppressWarnings("unused")
	@Autowired
	private RoleService roleService;
	
}
